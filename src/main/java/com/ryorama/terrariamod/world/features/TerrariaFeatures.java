package com.ryorama.terrariamod.world.features;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class TerrariaFeatures {
	
	public static void init() {
		RegistryKey<ConfiguredFeature<?, ?>> copperOre = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
			new Identifier("terrariamod", "copper_ore"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, copperOre.getValue(), CopperOreFeature.COPPER_ORE_FEATURE);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, copperOre);
			    
			    
			    
		RegistryKey<ConfiguredFeature<?, ?>> copperOreSurface = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
			new Identifier("terrariamod", "copper_ore_surface"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, copperOreSurface.getValue(), CopperOreFeatureSurface.COPPER_ORE_FEATURE);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, copperOreSurface);
	
		RegistryKey<ConfiguredFeature<?, ?>> copperOreTwo = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
			new Identifier("terrariamod", "copper_ore_two"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, copperOreTwo.getValue(), CopperOreFeature.COPPER_ORE_FEATURE_TWO);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, copperOreTwo);
				    
				    
				    
		RegistryKey<ConfiguredFeature<?, ?>> copperOreSurfaceTwo = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
			new Identifier("terrariamod", "copper_ore_surface_two"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, copperOreSurfaceTwo.getValue(), CopperOreFeatureSurface.COPPER_ORE_FEATURE_TWO);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, copperOreSurfaceTwo);

	
	
	}
}
