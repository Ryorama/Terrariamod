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
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {
	
	@Inject(at = @At("HEAD"), method = "getWorldHeight", cancellable = true)
	public void getWorldHeight(CallbackInfoReturnable<Integer> info) {
		info.setReturnValue(512);
	}
	
	@Inject(at = @At("HEAD"), method = "generateFeatures", cancellable = true)
	public void generateFeatures(ChunkRegion region, StructureAccessor accessor, CallbackInfo info) {
		
		ChunkPos chunkPos = region.getCenterPos();
		BlockPos.Mutable pos = new BlockPos.Mutable();

		/*
		for (int x = chunkPos.getStartX(); x <= chunkPos.getEndX(); x++) {
			for (int z = chunkPos.getStartZ(); z <= chunkPos.getEndZ(); z++) {
				for (int y = region.getBottomY(); y <= region.getTopY(); y++) {
					GeneratePurityGrass(region, x, y, z, pos, placing_pos);
					GeneratePurityTrees(region, x, y, z, pos, placing_pos);
				}
			}
		}*/
				
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
					}
				}
			}
		}
		
		info.cancel();
	}
	
	/*
	private void GeneratePurityTrees(ChunkRegion region, int x, int y, int z, BlockPos.Mutable pos, BlockPos.Mutable placing_pos) {
		pos.set(x, y, z);
		if (region.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK) {
			if (region.getRandom().nextInt(100) == 0) {
				int height = region.getRandom().nextInt(8) + 8;
				BlockPos leaf_pos = pos.up(height);
				BlockPos leaf_pos_cone = pos.up(height - 3);

				boolean bare = region.getRandom().nextInt(50) == 0;
				
				for (int X = -10; X < 10; X++) {
					for (int Y = 0; Y < 24; Y++) {
						for (int Z = -10; Z < 10; Z++) {
							placing_pos.set(x + X, y + Y, z + Z);
							if (region.isChunkLoaded(placing_pos)) {
								
								if (DistanceField.Cylinder(placing_pos, pos, height, 1)) {
									if (region.getRandom().nextInt(10) == 0)
										region.setBlockState(placing_pos, Blocks.OAK_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 2), 0);
								}
								
								if (DistanceField.cone(placing_pos, pos, height, 1.5f)) {
									region.setBlockState(placing_pos, Blocks.OAK_LOG.getDefaultState(), 0);
								}
								
								if (!bare) {
									if (DistanceField.sphere(placing_pos, leaf_pos, 3) &&
											DistanceField.cone(placing_pos, leaf_pos_cone, 7, 4)) {
										if (region.getRandom().nextInt(10) < 9)
										region.setBlockState(placing_pos, Blocks.OAK_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 2), 0);
									}
								} else {
									if (DistanceField.sphere(placing_pos, leaf_pos, 2)) {
										if (region.getRandom().nextInt(10) == 0)
										region.setBlockState(placing_pos, Blocks.OAK_LOG.getDefaultState(), 0);
									}
								}
								
								
							}
						}
					}
				}
			}
		}
	}
	*/
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
