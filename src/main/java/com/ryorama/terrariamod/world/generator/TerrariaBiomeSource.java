package com.ryorama.terrariamod.world.generator;

import java.util.List;

import com.mojang.serialization.Codec;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BuiltinBiomes;
import net.minecraft.world.biome.source.BiomeSource;

public class TerrariaBiomeSource extends BiomeSource {
	
	public TerrariaBiomeSource(List<Biome> biomeRegistry, long seed) {
		super(biomeRegistry);
		
		this.biomeRegistry = biomeRegistry;
		this.seed = seed;
	}
	
	private final List<Biome> biomeRegistry;
	private final long seed;
	
	@Override
	public Biome getBiomeForNoiseGen(int biomeX, int biomeY, int biomeZ) {
		return BuiltinBiomes.PLAINS;
	}

	@Override
	protected Codec<? extends BiomeSource> getCodec() {
		return BiomeSource.CODEC;
	}

	@Override
	public BiomeSource withSeed(long seed) {
		return new TerrariaBiomeSource(this.biomeRegistry, 1);
	}

}