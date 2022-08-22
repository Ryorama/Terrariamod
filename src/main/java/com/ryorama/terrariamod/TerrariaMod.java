package com.ryorama.terrariamod;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.OptionalLong;
import java.util.function.Function;

import com.ryorama.terrariamod.biomes.BiomeRegistry;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.core.client.ParticleRegistry;
import com.ryorama.terrariamod.crafting.Recipes;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.fluid.HoneyFluid;
import com.ryorama.terrariamod.gui.crafting.CraftingGuiDescription;
import com.ryorama.terrariamod.items.ItemGelColor;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.ui.TerrariaUIRenderer;

import com.ryorama.terrariamod.world.WorldDataT;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.dimension.DimensionType;
import software.bernie.geckolib3.GeckoLib;

public class TerrariaMod implements ModInitializer {

	public static final String MODID = "terrariamod";
	
	private static final DimensionType MODIFIED_OVERWORLD = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, -256, 512, 512, BlockTags.INFINIBURN_OVERWORLD, DimensionType.OVERWORLD_ID, 0.0F);

	public static FlowableFluid STILL_HONEY;
	public static FlowableFluid FLOWING_HONEY;
	public static Block HONEY;

	public static Item HONEY_BUCKET;

	public static boolean DEBUG = false;
	public static final Identifier MANA = new Identifier(MODID, "mana");
	public static final Identifier MAX_MANA = new Identifier(MODID, "max_mana");
	public static final Identifier ROYAL_GEL_EQ = new Identifier(MODID, "royal_gel_eq");

	//Buffs & Debuffs
	public static final Identifier IRON_SKIN = new Identifier(MODID, "iron_skin");
	public static final Identifier POTION_SICKNESS = new Identifier(MODID, "potion_sickness");
	public static final Identifier HAPPY = new Identifier(MODID, "happy");
	public static final Identifier COZY_FIRE = new Identifier(MODID, "cozy_fire");
	public static final Identifier REGENERATION = new Identifier(MODID, "regeneration");
	public static final Identifier POISONED = new Identifier(MODID, "poisoned");

	public static TerrariaModConfig CONFIG = new TerrariaModConfig();

	public static ScreenHandlerType CRAFTING_TYPE;

	@Override
	public void onInitialize() {
		AutoConfig.register(TerrariaModConfig.class, GsonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(TerrariaModConfig.class).getConfig();
		ParticleRegistry.init();
		BlocksT.init();
		ItemsT.init();
		EntitiesT.init();
		Recipes.addAllRecipes();
		ModifyWorldHeight();
		GeckoLib.initialize();
		TAudio.registerAudio();
		TAudio.registerMusic();

		CRAFTING_TYPE = Registry.register(Registry.SCREEN_HANDLER, new Identifier(MODID, "crafting_screen"), new ScreenHandlerType<>((syncId, inventory) -> new CraftingGuiDescription(syncId, inventory, ScreenHandlerContext.EMPTY)));

		STILL_HONEY = Registry.register(Registry.FLUID, new Identifier(MODID, "still_honey"), new HoneyFluid.Still());
		FLOWING_HONEY = Registry.register(Registry.FLUID, new Identifier(MODID, "flowing_honey"), new HoneyFluid.Flowing());
		HONEY = Registry.register(Registry.BLOCK, new Identifier(MODID, "honey"), new FluidBlock(STILL_HONEY, FabricBlockSettings.copy(Blocks.WATER)){});

		HONEY_BUCKET = Registry.register(Registry.ITEM, new Identifier(TerrariaMod.MODID, "honey_bucket"),
				new BucketItem(TerrariaMod.STILL_HONEY, new FabricItemSettings().group(ItemGroup.MISC).recipeRemainder(Items.BUCKET).maxCount(1)));


		Registry.register(Registry.CUSTOM_STAT, "mana", MANA);
		Stats.CUSTOM.getOrCreateStat(MANA, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "max_mana", MAX_MANA);
		Stats.CUSTOM.getOrCreateStat(MAX_MANA, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "royal_gel_eq", ROYAL_GEL_EQ);
		Stats.CUSTOM.getOrCreateStat(ROYAL_GEL_EQ, StatFormatter.DEFAULT);

		//Buffs
		Registry.register(Registry.CUSTOM_STAT, "iron_skin", IRON_SKIN);
		Stats.CUSTOM.getOrCreateStat(IRON_SKIN, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "happy", HAPPY);
		Stats.CUSTOM.getOrCreateStat(HAPPY, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "cozy_fire", COZY_FIRE);
		Stats.CUSTOM.getOrCreateStat(COZY_FIRE, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "regeneration", REGENERATION);
		Stats.CUSTOM.getOrCreateStat(REGENERATION, StatFormatter.DEFAULT);

		//DeBuffs
		Registry.register(Registry.CUSTOM_STAT, "potion_sickness", POTION_SICKNESS);
		Stats.CUSTOM.getOrCreateStat(POTION_SICKNESS, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "poisoned", POISONED);
		Stats.CUSTOM.getOrCreateStat(POISONED, StatFormatter.DEFAULT);
	}

	private static void ModifyWorldHeight() {
		Field[] dimension_fields = DimensionType.class.getDeclaredFields();
		for (int i = 0; i < dimension_fields.length; i++) {
			try {
				Resources.makeFieldAccessible(dimension_fields[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(dimension_fields[i].getName() + ", " + i);
		}
		int overworld_num = 14;
		Field overworld_field = dimension_fields[overworld_num];

		try {
			Resources.makeFieldAccessible(overworld_field);
			overworld_field.set(null, MODIFIED_OVERWORLD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
