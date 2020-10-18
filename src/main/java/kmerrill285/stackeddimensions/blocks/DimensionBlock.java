package kmerrill285.stackeddimensions.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DimensionBlock extends Block {
	
	public DimensionBlock(Properties properties) {
		super(properties.tickRandomly().hardnessAndResistance(0).noDrops());
	}
	
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		Vec3d p = new Vec3d(pos);
		boolean close = false;
		for (PlayerEntity player : worldIn.getPlayers()) {
			if (player == null) break;
			if (Math.sqrt(player.getDistanceSq(p)) <= 2) {
				close = true;
				break;
			}
		}
		if (close == false) {
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
	}
	
	
}