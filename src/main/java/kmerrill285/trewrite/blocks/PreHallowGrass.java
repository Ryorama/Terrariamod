package kmerrill285.trewrite.blocks;

import java.util.Random;

import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PreHallowGrass extends DirtBlock {

	public PreHallowGrass(Properties properties) {
		super(properties.tickRandomly());
	}

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {

	   if (WorldStateHolder.get(worldIn).hardmode == true) {
	        worldIn.setBlockState(pos, BlocksT.HALLOW_GRASS.getDefaultState());
	   }

	}

	public boolean getTickRandomly(BlockState state) {
		return true;
	}
}
