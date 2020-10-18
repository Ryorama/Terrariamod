package kmerrill285.trewrite.blocks;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class MushroomGrassBlock extends DirtBlock {

	public MushroomGrassBlock(Properties properties) {
		super(properties.tickRandomly());
	}
	
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			
			if (!worldIn.isAreaLoaded(pos, 3)) return;
			BlockPos pos2 = pos.add(0, 1, 0);
			if (worldIn.isBlockPresent(pos2)) {
				if (worldIn.getBlockState(pos2).isSolid()) {
					worldIn.setBlockState(pos, BlocksT.MUD.getDefaultState());
						return;
					}
				if (worldIn.getBlockState(pos2).getBlock().getDefaultState() == BlocksT.AIR_BLOCK.getDefaultState()) {
					if (random.nextInt(100 * 10) <= 2) {
						worldIn.setBlockState(pos2, BlocksT.GLOWING_MUSHROOM.getDefaultState());
						if (random.nextInt(50) == 0) {
							tree(pos2, worldIn, BlocksT.LARGE_MUSHROOM, random, random.nextInt(5) + 5, BlocksT.MUSHROOM_GRASS);
						}
					}
				}
			}
			for (int i = 0; i < 4; ++i) {
				BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
				if (blockpos.getY() == pos.getY() - 1) {
					return;
				}
				if (!worldIn.isBlockPresent(blockpos)) return;
				if (worldIn.getBlockState(blockpos).getBlock() == BlocksT.MUD) {
					worldIn.setBlockState(blockpos, this.getDefaultState());
				}
			}
		}
	}
	
	public boolean tree(BlockPos blockpos, IWorld worldIn, BlockT tree, Random rand, int j, BlockT...growsOn) {
		   for (int g = 0; g < growsOn.length; g++)
		   if (worldIn.getBlockState(blockpos.down()) == growsOn[g].getDefaultState()) {
        	for(int k = 0; k < j - 1; ++k) {
        	   if (k == 0) {
        		   worldIn.setBlockState(blockpos.up(k), tree.getDefaultState().with(BasicPlant.TYPE, 0), 2);
        	   } else {
        		   if (rand.nextInt(j) <= 4) {
	            		   worldIn.setBlockState(blockpos.up(k), tree.getDefaultState().with(BasicPlant.TYPE, rand.nextInt(4) + 2), 2);
        		   } else {
        			   worldIn.setBlockState(blockpos.up(k), tree.getDefaultState().with(BasicPlant.TYPE, 1), 2);
        		   }
        	   }
        	   if (k == j - 2) {
        		   worldIn.setBlockState(blockpos.up(k), tree.getDefaultState().with(BasicPlant.TYPE, 6), 2);
        	   }
              
           }
        	return true;
        }
		   return false;
	   }
	
	public int tickRate (IWorldReader world) {
		return 0;
	}
	
	public boolean getTickRandomly(BlockState state) {
		return true;
	}
	
//	public IItemProvider getItemDropped(BlockState state, World worldIn, BlockPos pos, int fortune) {
//	      return BlocksT.DIRT_BLOCK;
//	}

	
//	public void tick(IBlockState state, World worldIn, BlockPos pos, Random random) {
//	      if (!worldIn.isRemote) {
//	         if (!worldIn.isAreaLoaded(pos, 3)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light and spreading
//	         if (!func_196383_a(worldIn, pos)) {
//	            worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState());
//	         } else {
//	            if (worldIn.getLight(pos.up()) >= 9) {
//	               for(int i = 0; i < 4; ++i) {
//	                  BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
//	                  if (!worldIn.isBlockPresent(blockpos)) {
//	                     return;
//	                  }
//
//	                  if (worldIn.getBlockState(blockpos).getBlock() == Blocks.DIRT && func_196384_b(worldIn, blockpos)) {
//	                     worldIn.setBlockState(blockpos, this.getDefaultState());
//	                  }
//	               }
//	            }
//
//	         }
//	      }
//	   }
}
