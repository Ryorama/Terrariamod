package com.ryorama.terrariamod;

import java.lang.reflect.Field;
import java.util.OptionalLong;

import com.ryorama.terrariamod.biomes.BiomeRegistry;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.items.ItemGelColor;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.world.features.TerrariaFeatures;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.tag.BlockTags;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;

public class TerrariaMod implements ModInitializer {

	public static String modid = "terrariamod";
		
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
		EntitiesT.init();
				
		onTick();
		ModifyWorldHeight();
		ColorProviderRegistry.ITEM.register(new ItemGelColor(), ItemsT.GEL);
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

	public void onTick() {
		/*
		TMusicTicker.musicChanged = true;
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
		
			TMusicTicker.onTickUpdate();
			
		});
		*/
	}
}