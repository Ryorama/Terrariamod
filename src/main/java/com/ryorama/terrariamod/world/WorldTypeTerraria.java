package com.ryorama.terrariamod.world;

import com.ryorama.terrariamod.world.biomes.BiomesT;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class WorldTypeTerraria extends WorldType {

	public WorldTypeTerraria(String name) {
		super(name);
	}

	public float getCloudHeight() {
		return 180;
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator(World world) {
		if (world.getDimension().getType() == DimensionType.OVERWORLD) {
			System.out.println("overwrld");
			OverworldGenSettings ogs = new OverworldGenSettings();
			
			SingleBiomeProviderSettings biomeProvider = new SingleBiomeProviderSettings(world.getWorldInfo());
			biomeProvider.setBiome(BiomesT.LOWLANDS);
			
			return new TerrariaChunkGenerator(world, new SingleBiomeProvider(biomeProvider), ogs);
		}
		
		else {
			return super.createChunkGenerator(world);
		}
	}
}
