package com.ryorama.terrariamod.biomes;

import java.awt.Color;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.Precipitation;
import net.minecraft.world.biome.SpawnSettings.Builder;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;

public class BiomeRegistry {
	
	public static Biome PURITY;
	public static Biome DESERT;
	public static Biome SNOW;
	public static Biome JUNGLE;
	public static Biome CORRUPTION;
	
	public static void RegisterBiomes() {
		PURITY = createPurityFeatures(0.75F, 1.0F, 0.1F);
		DESERT = createPurityFeatures(0.75F, 1.0F, 0f);
		DESERT = createPurityFeatures(0.75F, 1.0F, 1f);
		JUNGLE = createPurityFeatures(0.75F, 1.0F, 1f);
		CORRUPTION = createPurityFeatures(0.75F, 1.0F, 0f);
		
	}
	
	private static Biome createPurityFeatures(float depth, float scale, float downfall) {
		Builder builder = new Builder();
		builder.playerSpawnFriendly();
		
		net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder())
				.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
		
		DefaultBiomeFeatures.addDefaultGrass(builder2);
		
		return (new net.minecraft.world.biome.Biome.Builder()).precipitation(Precipitation.RAIN)
				.category(Category.PLAINS).depth(depth).scale(scale).temperature(0.95F).downfall(downfall)
				.effects(
						(new net.minecraft.world.biome.BiomeEffects.Builder()).grassColor(new Color(89, 255, 44).getRGB()).waterColor(new Color(103, 149, 244).getRGB()).waterFogColor(new Color(34, 67, 162).getRGB())
								.fogColor(12638463).foliageColor(new Color(82, 230, 59).getRGB()).skyColor(new Color(34, 143, 212).getRGB()).moodSound(BiomeMoodSound.CAVE).build())
				.spawnSettings(builder.build()).generationSettings(builder2.build()).build();
	}
}
