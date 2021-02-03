package com.ryorama.terrariamod.world.biomes;

import java.util.ArrayList;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class BiomesT {

	public static Biome LOWLANDS;
	
	public static IForgeRegistry biomeRegistry;
	
	@SubscribeEvent
	public static void onRegisterBiomes(RegistryEvent.Register<Biome> event) {
		if (biomeRegistry == null) {
			biomeRegistry = event.getRegistry();
		}
		
		registerBiome(LOWLANDS = new LowlandsBiome(), "lowlands", 10, BiomeType.WARM, Type.PLAINS);
	}
	
	public static void registerBiome(Biome biome, String name, int weight, BiomeType type, Type... types) {
		if (biomeRegistry == null) {
			throw new NullPointerException("Biome Registry not set");
		}
		
		biomeRegistry.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(type, new BiomeEntry(biome, weight));
		BiomeManager.addSpawnBiome(biome);
	}
	
}
