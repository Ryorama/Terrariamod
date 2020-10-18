package kmerrill285.trewrite.blocks.loot_blocks;

import java.util.Random;

import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SurfaceBlock extends BlockT {

	public SurfaceBlock(Properties properties, float hardness, float difficulty, String drop) {
		super(properties, hardness, difficulty, drop);
		this.pick = true;
	}

	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		
		Random rand = worldIn.rand;
		
		EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.SILVER_COIN, 5 + rand.nextInt(15)));
		
		if (rand.nextInt(4) == 0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.BAND_OF_REGENERATION, 1));
		}
		if (rand.nextInt(5) == 0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.ENCHANTED_BOOMERANG, 1));
		}
		if (rand.nextInt(4) == 0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.RECALL_POTION, 1 + rand.nextInt(11)));
		}
		if (rand.nextInt(5) == 0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.LESSER_HEALING_POTION, 3 + rand.nextInt(5)));
		}
		if (rand.nextInt(10) == 0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.IRONSKIN_POTION, 1 + rand.nextInt(2)));
		}
		if (rand.nextInt(10) == 0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.SWIFTNESS_POTION, 1 + rand.nextInt(2)));
		}
		if (rand.nextInt(10) == 0) {
			EntityItemT.spawnItem(worldIn, pos, new ItemStackT(ItemsT.REGENERATION_POTION, 1 + rand.nextInt(2)));
		}
	}
}
