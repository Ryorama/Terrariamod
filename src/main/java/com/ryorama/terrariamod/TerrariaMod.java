package com.ryorama.terrariamod;

import java.io.IOException;
import java.util.function.Function;

import com.ryorama.terrariamod.biomes.BiomeRegistry;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.core.client.ParticleRegistry;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.fluid.HoneyFluid;
import com.ryorama.terrariamod.items.ItemGelColor;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.ui.TerrariaUIRenderer;

import com.ryorama.terrariamod.world.WorldDataT;
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
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import software.bernie.geckolib3.GeckoLib;

public class TerrariaMod implements ModInitializer {

	public static final String MODID = "terrariamod";
	
	//private static final DimensionType MODIFIED_OVERWORLD = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, -256, 512, 512, BlockTags.INFINIBURN_OVERWORLD, DimensionType.OVERWORLD_ID, 0.0F);

	public static FlowableFluid STILL_HONEY;
	public static FlowableFluid FLOWING_HONEY;
	public static Block HONEY;

	public static boolean DEBUG = false;
	public static final Identifier MANA = new Identifier(MODID, "mana");
	public static final Identifier MAX_MANA = new Identifier(MODID, "max_mana");
	public static final Identifier IRON_SKIN = new Identifier(MODID, "iron_skin");
	public static final Identifier POTION_SICKNESS = new Identifier(MODID, "potion_sickness");
	public static final Identifier HAPPY = new Identifier(MODID, "happy");
	public static final Identifier COZY_FIRE = new Identifier(MODID, "cozy_fire");

	@Override
	public void onInitialize() {
		//BiomeRegistry.RegisterBiomes();
		//ParticleRegistry.init();
		BlocksT.init();
		ItemsT.init();
		EntitiesT.init();
		
		//ModifyWorldHeight();
		GeckoLib.initialize();
		TAudio.registerAudio();

		STILL_HONEY = Registry.register(Registry.FLUID, new Identifier(MODID, "still_honey"), new HoneyFluid.Still());
		FLOWING_HONEY = Registry.register(Registry.FLUID, new Identifier(MODID, "flowing_honey"), new HoneyFluid.Flowing());
		HONEY = Registry.register(Registry.BLOCK, new Identifier(MODID, "honey"), new FluidBlock(STILL_HONEY, FabricBlockSettings.copy(Blocks.WATER)){});

		Registry.register(Registry.CUSTOM_STAT, "mana", MANA);
		Stats.CUSTOM.getOrCreateStat(MANA, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "max_mana", MAX_MANA);
		Stats.CUSTOM.getOrCreateStat(MAX_MANA, StatFormatter.DEFAULT);

		//Buffs
		Registry.register(Registry.CUSTOM_STAT, "iron_skin", IRON_SKIN);
		Stats.CUSTOM.getOrCreateStat(IRON_SKIN, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "happy", HAPPY);
		Stats.CUSTOM.getOrCreateStat(HAPPY, StatFormatter.DEFAULT);
		Registry.register(Registry.CUSTOM_STAT, "cozy_fire", COZY_FIRE);
		Stats.CUSTOM.getOrCreateStat(COZY_FIRE, StatFormatter.DEFAULT);

		//DeBuffs
		Registry.register(Registry.CUSTOM_STAT, "potion_sickness", POTION_SICKNESS);
		Stats.CUSTOM.getOrCreateStat(POTION_SICKNESS, StatFormatter.DEFAULT);
	}

	private static void ModifyWorldHeight() {
		/*
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
		}catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
}
