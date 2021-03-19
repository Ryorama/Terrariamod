package com.ryorama.terrariamod.world.generator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.mojang.serialization.Codec;
import com.ryorama.terrariamod.blocks.BlocksT;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.StructuresConfig;
import net.minecraft.world.gen.chunk.VerticalBlockSample;

public class TerrariaChunkGenerator extends ChunkGenerator {

	private final long seed;
	private final BiomeSource biomeSource;
	
	public TerrariaChunkGenerator(BiomeSource biomeSource, long seed) {
		super(biomeSource, new StructuresConfig(true));

		this.biomeSource = biomeSource;
		this.seed = seed;
	}

	@Override
	public void buildSurface(ChunkRegion region, Chunk chunk) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = -512; y < 492; y++) {
					chunk.setBlockState(new BlockPos(x, y, z), BlocksT.STONE_BLOCK.getDefaultState(), false);
				}
				for (int y = 492; y < 511; y++) {
					chunk.setBlockState(new BlockPos(x, y, z), BlocksT.DIRT_BLOCK.getDefaultState(), false);
				}
				for (int y = 511; y < 512; y++) {
					chunk.setBlockState(new BlockPos(x, y, z), BlocksT.GRASS_BLOCK.getDefaultState(), false);
				}
			}
		}
	}

	@Override
	protected Codec<? extends ChunkGenerator> getCodec() {
		return ChunkGenerator.CODEC;
	}

	@Override
	public int getHeight(int x, int z, Type heightmap, HeightLimitView world) {
		return 100;
	}
	
	@Override
	public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world) {
		BlockState[] array = new BlockState[512];
		
		return new VerticalBlockSample(-512, array);
	}

	@Override
	public CompletableFuture<Chunk> populateNoise(Executor executor, StructureAccessor accessor, Chunk chunk) {
		return new CompletableFuture<Chunk>();
	}

	@Override
	public ChunkGenerator withSeed(long arg0) {
		return new TerrariaChunkGenerator(this.biomeSource.withSeed(1), 1);
	}
}
