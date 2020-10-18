package kmerrill285.trewrite.blocks.loot_blocks;

import java.util.Random;

import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SkyBlock extends BlockT {

	public SkyBlock(Properties properties, float hardness, float difficulty, String drop) {
		super(properties, hardness, difficulty, drop);
		this.pick = true;
	}

	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		
		Random rand = worldIn.rand;
		
		EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.BAND_OF_REGENERATION, 1));
		
		if (rand.nextInt(3) == 0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.LUCKY_HORSESHOE, 1));
		}
		if (rand.nextInt(5) == 0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.ENCHANTED_BOOMERANG, 1));
		}
		if (rand.nextInt(3) == 0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.BAND_OF_STARPOWER, 1));
		}
		if (rand.nextInt(4) == 0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.CLOUD_IN_A_BOTTLE, 1));
		}
	}
}
