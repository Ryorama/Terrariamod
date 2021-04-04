package com.ryorama.terrariamod;

import java.lang.reflect.Field;
import java.util.OptionalLong;

import com.ryorama.terrariamod.biomes.BiomeRegistry;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.client.TMusicTicker;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.items.ItemGelColor;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.world.EntitySpawner;
import com.ryorama.terrariamod.world.features.TerrariaFeatures;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;
import software.bernie.geckolib3.GeckoLib;

public class TerrariaMod implements ModInitializer, ClientModInitializer {

	public static String modid = "terrariamod";
	
	private static EntityAttribute register_attribute(String id, EntityAttribute attribute) {
		return (EntityAttribute) Registry.register(Registry.ATTRIBUTE, id, attribute);
	}

	public static final EntityAttribute MODIFIED_MAX_HEALTH = register_attribute("trewrite.max_health",
			(new ClampedEntityAttribute("attribute.name.trewrite.max_health", 20.0D, 1.0D, 5000000.0D)).setTracked(true));
	
	
	private static final DimensionType MODIFIED_OVERWORLD = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, 
			-256,
			256, 
			256, 
			HorizontalVoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getId(),
			DimensionType.OVERWORLD_ID, 0.0F);
	
	
	@Override
	public void onInitialize() {
		BiomeRegistry.RegisterBiomes();
		BlocksT.init();
		ItemsT.init();
		TerrariaFeatures.init();
		
		GeckoLib.initialize();
		ModifyWorldHeight();
	}

	@Environment(EnvType.CLIENT)
	@Override
	public void onInitializeClient() {
		EntitiesT.init();

		ColorProviderRegistry.ITEM.register(new ItemGelColor(), ItemsT.GEL);
		onTick();
		addCutouts();
	}

	private static void ModifyWorldHeight() {
		//OVERWORLD: 12
		Field[] dimension_fields = DimensionType.class.getDeclaredFields();
		for (int i = 0; i < dimension_fields.length; i++) {
			try {
				Resources.makeFieldAccessible(dimension_fields[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(dimension_fields[i].getName() + ", " + i);
		}
		int overworld_num = 12;
		Field overworld_field = dimension_fields[overworld_num];
		
		try {
			Resources.makeFieldAccessible(overworld_field);
			overworld_field.set(null, MODIFIED_OVERWORLD);
		}catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * OVERWORLD = create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, -64,
				384, 384, HorizontalVoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getId(),
				OVERWORLD_ID, 0.0F);
		 */
	}	

	public void addCutouts() {
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP, RenderLayer.getTranslucent());

		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MUSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.EMPTY_BOTTLE, RenderLayer.getCutout());
	}
	

	public void onTick() {
		
		TMusicTicker.musicChanged = true;
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
		
			TMusicTicker.onTickUpdate();
			
		});
		
		WorldTickCallback.EVENT.register(world -> {
			
			if (world.getRandom().nextInt(700) <= 10)
			if (world.getPlayers().size() > 0) {
				PlayerEntity player = world.getPlayers().get(world.random.nextInt(world.getPlayers().size()));
				double x = player.getPos().x + world.random.nextInt(80) - 40, y = player.getPos().y + world.random.nextInt(80) - 40, z = player.getPos().z + world.random.nextInt(80) - 40;
				
				for (PlayerEntity p2 : world.getPlayers()) {
						if (p2.getPos().distanceTo(new Vec3d(x, y, z)) >= 5) {
							new Thread () {
								public void run() {
									EntitySpawner.spawnEntities(player, x, y, z);
								}
							}.start();
							
							break;
						}
					}
				}
		});
		
	}
}