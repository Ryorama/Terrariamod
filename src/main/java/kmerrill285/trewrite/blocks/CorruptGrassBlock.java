package kmerrill285.trewrite.blocks;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class CorruptGrassBlock extends DirtBlock {

	public CorruptGrassBlock(Properties properties) {
		super(properties.tickRandomly());
	}
	
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			
			if (!worldIn.isAreaLoaded(pos, 3)) return;
			BlockPos pos2 = pos.add(0, 1, 0);
			if (worldIn.isBlockPresent(pos2)) {
				if (worldIn.getBlockState(pos2).isSolid()) {
					worldIn.setBlockState(pos, BlocksT.DIRT_BLOCK.getDefaultState());
						return;
					}
				if (worldIn.getBlockState(pos2).getBlock().getDefaultState() == BlocksT.AIR_BLOCK.getDefaultState()) {
					if (random.nextInt(100 * 5) <= 2) {
						worldIn.setBlockState(pos2, BlocksT.CORRUPTION_PLANTS.getDefaultState().with(BasicPlant.TYPE, random.nextInt(17)));
						if (random.nextInt(15) == 0) {
							worldIn.setBlockState(pos2, BlocksT.VILE_MUSHROOM.getDefaultState());
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
				if (worldIn.getBlockState(blockpos.up()).getMaterial() == Material.AIR)
				if (worldIn.getBlockState(blockpos).getBlock() == BlocksT.DIRT_BLOCK) {
					if (worldIn.isBlockPresent(blockpos.up())) {
						if (!(worldIn.getBlockState(blockpos).getBlock() == BlocksT.SUNFLOWER)) {
							worldIn.setBlockState(blockpos, this.getDefaultState());
						}
					}
					
				}
			}
		}
	}
	
	public int tickRate (IWorldReader world) {
		return 10;
	}
	
	public boolean getTickRandomly(BlockState state) {
		return true;
	}
}
