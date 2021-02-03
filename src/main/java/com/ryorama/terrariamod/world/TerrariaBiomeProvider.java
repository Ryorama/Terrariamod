package com.ryorama.terrariamod.world;

import java.util.Set;

import com.ryorama.terrariamod.world.biomes.BiomesT;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;

public class TerrariaBiomeProvider extends BiomeProvider {

	public TerrariaBiomeProvider(Set<Biome> biomesIn) {
		super(biomesIn);
	}
	
	public static final Biome[] biomes = new Biome[] {BiomesT.LOWLANDS};
	
	
	public Biome getBiome(int x, int y) {
		
		Biome current = BiomesT.LOWLANDS;
		
		return current;
		
	}
	
	@Override
	public Biome getNoiseBiome(int x, int y, int z) {
		return null;
	}

}
