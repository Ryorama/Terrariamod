package kmerrill285.trewrite.blocks;

import java.util.Random;

import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class JungleGrassBlock extends DirtBlock {

	public JungleGrassBlock(Properties properties) {
		super(properties.tickRandomly());
	}
	
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			
			if (!worldIn.isAreaLoaded(pos, 3)) return;
			BlockPos pos2 = pos.add(0, 1, 0);
			if (worldIn.isBlockPresent(pos2)) {
				if (worldIn.getBlockState(pos2).isSolid() && worldIn.getBlockState(pos2).getBlock() instanceof BlockAirT == false) {
					worldIn.setBlockState(pos, BlocksT.MUD.getDefaultState());
						return;
					}
				if (worldIn.getBlockState(pos2).getBlock().getDefaultState() == BlocksT.AIR_BLOCK.getDefaultState()) {
					if (random.nextInt(100 * 10) <= 2) {
						worldIn.setBlockState(pos2, BlocksT.FLOWER.getDefaultState().with(BasicPlant.TYPE, random.nextInt(21)));
						if (random.nextInt(15) == 0) {
							worldIn.setBlockState(pos2, BlocksT.MUSHROOM.getDefaultState());
						}
					}
				}
				if (worldIn.getBlockState(pos2).getBlock().getDefaultState() == BlocksT.AIR_BLOCK.getDefaultState() && WorldStateHolder.get(worldIn).hardmode == true && WorldStateHolder.get(worldIn).mechBossesDowned >= 1) {
					if (random.nextInt(5) == 0) {
						System.out.println("life_fruit");
						worldIn.setBlockState(pos2, BlocksT.LIFE_FRUIT.getDefaultState());
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
