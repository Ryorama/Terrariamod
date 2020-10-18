package kmerrill285.trewrite.blocks;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class Tree extends BasicPlant {
	
	
	public Tree(Properties properties, float hardness, float difficulty, boolean pick, boolean axe, boolean hammer,
			boolean material, String name, String drop) {
		super(properties, hardness, difficulty, pick, axe, hammer, material, name, drop);
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
	      return !this.isValidPosition(stateIn, worldIn, currentPos) ? breakBlock(worldIn.getWorld(), facingPos, facingState) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	   }
	
	public BlockState breakBlock(World worldIn, BlockPos pos, BlockState state) {
		 if (!worldIn.isRemote)
			 if (state != null)
				if (state.getBlock() instanceof BlockT) {
					if (ItemsT.getItemFromString(((BlockT)state.getBlock()).drop) != null) {
						Item drop = ItemsT.getItemFromString(((BlockT)state.getBlock()).drop);
						EntityItemT.spawnItem(worldIn, pos, new ItemStackT(drop, 1));
						
					}
				}
		 return BlocksT.AIR_BLOCK.getDefaultState();
	 }
	
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
	      BlockPos blockpos = pos.down();
	      BlockState BlockState = worldIn.getBlockState(blockpos);
	      Block block = BlockState.getBlock().getDefaultState().getBlock();
	      
	      if (block instanceof BlockT) {
	    	  boolean a = allowed.contains(((BlockT) block).name);
	    	  return a;
	      }
	      return BlockState.isSolid();
	   }

	
}
