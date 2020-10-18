package kmerrill285.trewrite.blocks;

import java.util.Random;

import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PreHallowTree extends Tree {

	public PreHallowTree(Properties properties, float hardness, float difficulty, boolean pick, boolean axe, boolean hammer, boolean material, String name, String drop) {
		super(properties.tickRandomly(), hardness, difficulty, pick, axe, hammer, material, name, drop);
	}

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
	      if (WorldStateHolder.get(worldIn).hardmode == true) {
	         worldIn.setBlockState(pos, BlocksT.HALLOWED_TREE.getDefaultState());
	      }

	   }
}
