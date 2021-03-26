package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ryorama.terrariamod.blocks.BlocksT;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.gen.SimpleRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {
	
	public DoublePerlinNoiseSampler terrainNoise;
	
	@Inject(at = @At("HEAD"), method = "getWorldHeight", cancellable = true)
	public void getWorldHeight(CallbackInfoReturnable<Integer> info) {
		info.setReturnValue(512);
	}
	
	@Inject(at = @At("HEAD"), method = "generateFeatures", cancellable = true)
	public void generateFeatures(ChunkRegion region, StructureAccessor accessor, CallbackInfo info) {
		
		 if (terrainNoise == null) {
	            terrainNoise = DoublePerlinNoiseSampler.create(new SimpleRandom(region.getRandom().nextLong()), -8,
	                    new double[]{1.0D});
	        }
		
		ChunkPos chunkPos = region.getCenterPos();
		BlockPos.Mutable pos = new BlockPos.Mutable();

		for (int x = chunkPos.getStartX(); x <= chunkPos.getEndX(); x++) {
			for (int z = chunkPos.getStartZ(); z <= chunkPos.getEndZ(); z++) {
				for (int y = region.getBottomY(); y <= region.getTopY(); y++) {
					pos.set(x, y, z);
										
					if (region.isChunkLoaded(pos)) {
						if (region.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK) {
							region.setBlockState(pos, BlocksT.GRASS_BLOCK.getDefaultState(), 0);
						}
						if (region.getBlockState(pos).getBlock() == Blocks.DIRT) {
							region.setBlockState(pos, BlocksT.DIRT_BLOCK.getDefaultState(), 0);
						}
						if (region.getBlockState(pos).getBlock() == Blocks.STONE) {
							region.setBlockState(pos, BlocksT.STONE_BLOCK.getDefaultState(), 0);
						}
						if (region.getBlockState(pos).getBlock() == Blocks.DEEPSLATE) {
							region.setBlockState(pos, BlocksT.STONE_BLOCK.getDefaultState(), 0);
						}
						
						GeneratePurityTrees(region, x, y, z, pos, pos);
						
						//Underworld Stuff
						if (y < -150 + terrainNoise.sample(x * 10, y * 0.01f, z * 10) * 25) {
	                        if (region.getBlockState(pos).getBlock() == Blocks.AIR ||
	                                region.getBlockState(pos).getBlock() == Blocks.CAVE_AIR) {
	                            if (y < -230) {
	                                region.setBlockState(pos, Blocks.LAVA.getDefaultState(), 0);
	                            }
	                        } else {
	                            region.setBlockState(pos, BlocksT.ASH.getDefaultState(), 0);
	                        }
	                    }
						
						if (y < -120) {
							if (region.getBlockState(pos).getBlock() == Blocks.WATER) {
								region.setBlockState(pos, Blocks.LAVA.getDefaultState(), 0);
							}
						}
					}
				}
			}
		}
		
		info.cancel();
	}
	
	private void GeneratePurityTrees(ChunkRegion region, int x, int y, int z, BlockPos.Mutable pos, BlockPos.Mutable placing_pos) {
		pos.set(x, y, z);
		if (region.getBlockState(pos).getBlock() == BlocksT.GRASS_BLOCK || region.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK) {
			if (region.getRandom().nextInt(80) == 0) {
				int height = region.getRandom().nextInt(8) + 4;
				region.setBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), BlocksT.FOREST_STUMP.getDefaultState(), 0);
				for (int h = 2; h <= height; h++) {
					region.setBlockState(new BlockPos(pos.getX(), pos.getY() + h, pos.getZ()), BlocksT.FOREST_STEM.getDefaultState(), 0);
				}
				region.setBlockState(new BlockPos(pos.getX(), pos.getY() + height + 1, pos.getZ()), BlocksT.FOREST_TOP.getDefaultState(), 0);
			}
		}
	}
	
	/*
	private void GeneratePurityGrass(ChunkRegion region, int x, int y, int z, BlockPos.Mutable pos, BlockPos.Mutable placing_pos) {
		pos.set(x, y, z);
		if (region.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK) {
			if (region.getRandom().nextInt(10) == 0) {
				placing_pos.set(x, y + 1, z);
				if (region.isChunkLoaded(placing_pos))
				region.setBlockState(placing_pos, Blocks.GRASS.getDefaultState(), 0);
			}
		}
	}
	*/
}