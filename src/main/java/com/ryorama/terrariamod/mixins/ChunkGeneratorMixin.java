package com.ryorama.terrariamod.mixins;

import java.util.BitSet;
import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ryorama.terrariamod.blocks.BlocksT;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
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
						
						
						//Ores
						if (y <= 80) {
							if (region.getRandom().nextInt(90) == 0) {
								if (region.getBlockState(pos) == BlocksT.GRASS_BLOCK.getDefaultState()) {
									placeOre(region, region.getRandom(), pos, region.getRandom().nextInt(12) + 3, BlocksT.GRASS_BLOCK.getDefaultState(), BlocksT.COPPER_ORE.getDefaultState());
								} else if (region.getBlockState(pos) == BlocksT.STONE_BLOCK.getDefaultState()) {
									placeOre(region, region.getRandom(), pos, region.getRandom().nextInt(12) + 3, BlocksT.STONE_BLOCK.getDefaultState(), BlocksT.COPPER_ORE.getDefaultState());
								} else if (region.getBlockState(pos) == Blocks.GRASS_BLOCK.getDefaultState()) {
									placeOre(region, region.getRandom(), pos, region.getRandom().nextInt(12) + 3, Blocks.GRASS_BLOCK.getDefaultState(), BlocksT.COPPER_ORE.getDefaultState());
								} else if (region.getBlockState(pos) == Blocks.STONE.getDefaultState()) {
									placeOre(region, region.getRandom(), pos, region.getRandom().nextInt(12) + 3, Blocks.STONE.getDefaultState(), BlocksT.COPPER_ORE.getDefaultState());
								}
							}
						}
						if (y <= 60) {
							if (region.getRandom().nextInt(90) == 0) {
								if (region.getBlockState(pos) == BlocksT.GRASS_BLOCK.getDefaultState()) {
									placeOre(region, region.getRandom(), pos, region.getRandom().nextInt(12) + 3, BlocksT.GRASS_BLOCK.getDefaultState(), BlocksT.IRON_ORE.getDefaultState());
								} else if (region.getBlockState(pos) == BlocksT.STONE_BLOCK.getDefaultState()) {
									placeOre(region, region.getRandom(), pos, region.getRandom().nextInt(12) + 3, BlocksT.STONE_BLOCK.getDefaultState(), BlocksT.IRON_ORE.getDefaultState());
								} else if (region.getBlockState(pos) == Blocks.GRASS_BLOCK.getDefaultState()) {
									placeOre(region, region.getRandom(), pos, region.getRandom().nextInt(12) + 3, Blocks.GRASS_BLOCK.getDefaultState(), BlocksT.IRON_ORE.getDefaultState());
								} else if (region.getBlockState(pos) == Blocks.STONE.getDefaultState()) {
									placeOre(region, region.getRandom(), pos, region.getRandom().nextInt(12) + 3, Blocks.STONE.getDefaultState(), BlocksT.IRON_ORE.getDefaultState());
								}
							}
						}
						if (y <= 10) {
							if (region.getRandom().nextInt(90) == 0) {
								if (region.getBlockState(pos) == BlocksT.STONE_BLOCK.getDefaultState()) {
									placeOre(region, region.getRandom(), pos, region.getRandom().nextInt(12) + 3, BlocksT.STONE_BLOCK.getDefaultState(), BlocksT.GOLD_ORE.getDefaultState());
								} else if (region.getBlockState(pos) == Blocks.STONE.getDefaultState()) {
									placeOre(region, region.getRandom(), pos, region.getRandom().nextInt(12) + 3, Blocks.STONE.getDefaultState(), BlocksT.GOLD_ORE.getDefaultState());
								}
							}
						}
						if (y <= -150) {
							if (region.getRandom().nextInt(90) == 0) {
								if (region.getBlockState(pos) == BlocksT.ASH.getDefaultState()) {
									placeOre(region, region.getRandom(), pos, region.getRandom().nextInt(12) + 3, BlocksT.ASH.getDefaultState(), BlocksT.HELLSTONE_ORE.getDefaultState());
								}
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
	
	
	
	public boolean placeOre(ChunkRegion worldIn, Random rand, BlockPos pos, int size, BlockState target, BlockState state) {
		
		if (worldIn.getBlockState(pos) != Blocks.WATER.getDefaultState() && worldIn.getBlockState(pos) != Blocks.LAVA.getDefaultState()) {
		  float f = rand.nextFloat() * (float)Math.PI;
	      float f1 = (float)size / 8.0F;
	      int i = MathHelper.ceil(((float)size / 16.0F * 2.0F + 1.0F) / 2.0F);
	      double d0 = (double)((float)pos.getX() + MathHelper.sin(f) * f1);
	      double d1 = (double)((float)pos.getX() - MathHelper.sin(f) * f1);
	      double d2 = (double)((float)pos.getZ() + MathHelper.cos(f) * f1);
	      double d3 = (double)((float)pos.getZ() - MathHelper.cos(f) * f1);
	      int j = 2;
	      double d4 = (double)(pos.getY() + rand.nextInt(3) - 2);
	      double d5 = (double)(pos.getY() + rand.nextInt(3) - 2);
	      int k = pos.getX() - MathHelper.ceil(f1) - i;
	      int l = pos.getY() - 2 - i;
	      int i1 = pos.getZ() - MathHelper.ceil(f1) - i;
	      int j1 = 2 * (MathHelper.ceil(f1) + i);
	      int k1 = 2 * (2 + i);

	      for(int l1 = k; l1 <= k + j1; ++l1) {
	         for(int i2 = i1; i2 <= i1 + j1; ++i2) {
	         	return func_207803_a(worldIn, rand, target, state, d0, d1, d2, d3, d4, d5, k, l, i1, j1, k1, size);
	         }
	      }
		}
	    return false;
	}
	
	protected boolean func_207803_a(ChunkRegion worldIn, Random random, BlockState target, BlockState state, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_, int size) {
	      int i = 0;
	      BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
	      BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
	      double[] adouble = new double[size * 4];

	      for(int j = 0; j < size; ++j) {
	         float f = (float)j / (float)size;
	         double d0 = MathHelper.lerp((double)f, p_207803_4_, p_207803_6_);
	         double d2 = MathHelper.lerp((double)f, p_207803_12_, p_207803_14_);
	         double d4 = MathHelper.lerp((double)f, p_207803_8_, p_207803_10_);
	         double d6 = random.nextDouble() * (double)size / 16.0D;
	         double d7 = ((double)(MathHelper.sin((float)Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
	         adouble[j * 4 + 0] = d0;
	         adouble[j * 4 + 1] = d2;
	         adouble[j * 4 + 2] = d4;
	         adouble[j * 4 + 3] = d7;
	      }

	      for(int l2 = 0; l2 < size - 1; ++l2) {
	         if (!(adouble[l2 * 4 + 3] <= 0.0D)) {
	            for(int j3 = l2 + 1; j3 < size; ++j3) {
	               if (!(adouble[j3 * 4 + 3] <= 0.0D)) {
	                  double d12 = adouble[l2 * 4 + 0] - adouble[j3 * 4 + 0];
	                  double d13 = adouble[l2 * 4 + 1] - adouble[j3 * 4 + 1];
	                  double d14 = adouble[l2 * 4 + 2] - adouble[j3 * 4 + 2];
	                  double d15 = adouble[l2 * 4 + 3] - adouble[j3 * 4 + 3];
	                  if (d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) {
	                     if (d15 > 0.0D) {
	                        adouble[j3 * 4 + 3] = -1.0D;
	                     } else {
	                        adouble[l2 * 4 + 3] = -1.0D;
	                     }
	                  }
	               }
	            }
	         }
	      }

	      for(int i3 = 0; i3 < size; ++i3) {
	         double d11 = adouble[i3 * 4 + 3];
	         if (!(d11 < 0.0D)) {
	            double d1 = adouble[i3 * 4 + 0];
	            double d3 = adouble[i3 * 4 + 1];
	            double d5 = adouble[i3 * 4 + 2];
	            int k = Math.max(MathHelper.floor(d1 - d11), p_207803_16_);
	            int k3 = Math.max(MathHelper.floor(d3 - d11), p_207803_17_);
	            int l = Math.max(MathHelper.floor(d5 - d11), p_207803_18_);
	            int i1 = Math.max(MathHelper.floor(d1 + d11), k);
	            int j1 = Math.max(MathHelper.floor(d3 + d11), k3);
	            int k1 = Math.max(MathHelper.floor(d5 + d11), l);

	            for(int l1 = k; l1 <= i1; ++l1) {
	               double d8 = ((double)l1 + 0.5D - d1) / d11;
	               if (d8 * d8 < 1.0D) {
	                  for(int i2 = k3; i2 <= j1; ++i2) {
	                     double d9 = ((double)i2 + 0.5D - d3) / d11;
	                     if (d8 * d8 + d9 * d9 < 1.0D) {
	                        for(int j2 = l; j2 <= k1; ++j2) {
	                           double d10 = ((double)j2 + 0.5D - d5) / d11;
	                           if (d8 * d8 + d9 * d9 + d10 * d10 < 1.0D) {
	                              int k2 = l1 - p_207803_16_ + (i2 - p_207803_17_) * p_207803_19_ + (j2 - p_207803_18_) * p_207803_19_ * p_207803_20_;
	                              if (!bitset.get(k2)) {
	                                 bitset.set(k2);
	                                 blockpos$mutableblockpos.set(l1, i2, j2);
	                                 if (target == worldIn.getBlockState(blockpos$mutableblockpos)) {
	                                    worldIn.setBlockState(blockpos$mutableblockpos, state, 2);
	                                    ++i;
	                                 }
	                              }
	                           }
	                        }
	                     }
	                  }
	               }
	            }
	         }
	      }

	      return i > 0;
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
	
public boolean placeOre2(ChunkRegion worldIn, Random rand, BlockPos pos, int size, BlockState target, BlockState state) {
		
		if (target == worldIn.getBlockState(pos) && worldIn.getBlockState(pos) != Blocks.WATER.getDefaultState() && worldIn.getBlockState(pos) != Blocks.LAVA.getDefaultState()) {
			int maxLineLength = 4;
			int pY = pos.getY();
			int currentPlacement = 0;
			for (int s = 0; s <= size; s++) {
				if (currentPlacement < maxLineLength) {
					currentPlacement = s;
				}
				worldIn.setBlockState(new BlockPos(pos.getX() + currentPlacement, pY, pos.getZ()), state, 0);
				
				if (currentPlacement >= maxLineLength) {
					currentPlacement = 0;
					pY -= 1;
				}
			}
		} else {
			return false;
		}
		
		return true;
	}
}