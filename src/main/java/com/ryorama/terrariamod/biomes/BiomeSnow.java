package com.ryorama.terrariamod.biomes;

import com.ryorama.terrariamod.blocks.BlocksT;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class BiomeSnow {
	
	public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> SNOW_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
		    .withConfig(new TernarySurfaceConfig(
		      BlocksT.SNOW.getDefaultState(),
		      BlocksT.SNOW.getDefaultState(),
		      BlocksT.SNOW.getDefaultState()));
		 
	public static final Biome SNOW = createPurity();
	
	public static Biome createPurity() {
	    // We specify what entities spawn and what features generate in the biome.
	    // Aside from some structures, trees, rocks, plants and
	    //   custom entities, these are mostly the same for each biome.
	    // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.
	 
	    SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
	   
	    GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
	    generationSettings.surfaceBuilder(SNOW_SURFACE_BUILDER);
	    DefaultBiomeFeatures.addLandCarvers(generationSettings);
	    DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
	 
	    return (new Biome.Builder())
	      .precipitation(Biome.Precipitation.SNOW)
	      .category(Biome.Category.NONE)
	      .depth(0.125F)
	      .scale(0.05F)
	      .temperature(0.1F)
	      .downfall(1F)
	      .effects((new BiomeEffects.Builder())
	        .waterColor(0x3f76e4)
	        .waterFogColor(0x050533)
	        .fogColor(0xc0d8ff)
	        .skyColor(0x77adff)
	        .build())
	      .spawnSettings(spawnSettings.build())
	      .generationSettings(generationSettings.build())
	      .build();
	  }

}
