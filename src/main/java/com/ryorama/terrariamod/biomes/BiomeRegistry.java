package com.ryorama.terrariamod.biomes;

import com.ryorama.terrariamod.TerrariaMod;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class BiomeRegistry {
	
	public static final RegistryKey<Biome> PURITY_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(TerrariaMod.MODID, "purity"));
	public static final RegistryKey<Biome> CORRUPTION_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(TerrariaMod.MODID, "corruption"));
	public static final RegistryKey<Biome> DESERT_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(TerrariaMod.MODID, "desert"));
	public static final RegistryKey<Biome> SNOW_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(TerrariaMod.MODID, "snow"));
	public static final RegistryKey<Biome> JUNGLE_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(TerrariaMod.MODID, "jungle"));

	public static void RegisterBiomes() {
	    Registry.register(BuiltinRegistries.BIOME, PURITY_KEY.getValue(), BiomePurity.PUTITY);
	
	    Registry.register(BuiltinRegistries.BIOME, CORRUPTION_KEY.getValue(), BiomeCorruption.CORRUPTION);
	
	    Registry.register(BuiltinRegistries.BIOME, DESERT_KEY.getValue(), BiomeDesert.DESERT);
	
	    Registry.register(BuiltinRegistries.BIOME, SNOW_KEY.getValue(), BiomeSnow.SNOW);
	
	    Registry.register(BuiltinRegistries.BIOME, JUNGLE_KEY.getValue(), BiomeJungle.JUNGLE);
	
	}
}
