package com.ryorama.terrariamod.world;

import com.ryorama.terrariamod.blocks.BlocksT;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.gen.INoiseGenerator;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.WorldGenRegion;

public class TerrariaChunkGenerator extends NoiseChunkGenerator<OverworldGenSettings> {

	public INoiseGenerator sdn;
	
	public TerrariaChunkGenerator(World worldIn, BiomeProvider biomeProviderIn, OverworldGenSettings settings) {
		super(worldIn, biomeProviderIn, 4, 8, 256, settings, true);
	}	

	@Override
	public void generateSurface(WorldGenRegion genRegion, IChunk chunk) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; y < 60; y++) {
					chunk.setBlockState(new BlockPos(x, y, z), BlocksT.STONE_BLOCK.getDefaultState(), false);
				}
				for (int y = 60; y < 70; y++) {
					chunk.setBlockState(new BlockPos(x, y, z), BlocksT.DIRT_BLOCK.getDefaultState(), false);
				}
				for (int y = 70; y < 71; y++) {
					chunk.setBlockState(new BlockPos(x, y, z), BlocksT.GRASS_BLOCK.getDefaultState(), false);
				}
			}
		}
	}

	@Override
	public int getGroundHeight() {
		return 100;
	}

	@Override
	public void makeBase(IWorld worldIn, IChunk chunkIn) {
		
	}

	@Override
	public int func_222529_a(int p_222529_1_, int p_222529_2_, Type heightmapType) {
		return 0;
	}

	@Override
	protected double[] getBiomeNoiseColumn(int noiseX, int noiseZ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected double func_222545_a(double p_222545_1_, double p_222545_3_, int p_222545_5_) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void fillNoiseColumn(double[] noiseColumn, int noiseX, int noiseZ) {
		// TODO Auto-generated method stub
		
	}


}
