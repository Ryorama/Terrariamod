package kmerrill285.trewrite.items;

import javax.annotation.Nullable;

import kmerrill285.trewrite.blocks.BlockT;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IWorldReader;

public class WallOrFloorBlock extends ItemBlockT {
	public BlockT second;
	public WallOrFloorBlock(BlockT first, BlockT second, String name) {
		super(first, name);
		this.second = second;
		this.maxStack = 99;
		// TODO Auto-generated constructor stub
	}
	

		@Nullable
	   protected BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState blockstate = this.second.getStateForPlacement(context);
	      BlockState blockstate1 = null;
	      IWorldReader iworldreader = context.getWorld();
	      BlockPos blockpos = context.getPos();

	      for(Direction direction : context.getNearestLookingDirections()) {
	         if (direction != Direction.UP) {
	            BlockState blockstate2 = direction == Direction.DOWN ? this.getBlock().getStateForPlacement(context) : blockstate;
	            if (blockstate2 != null && blockstate2.isValidPosition(iworldreader, blockpos)) {
	               blockstate1 = blockstate2;
	               break;
	            }
	         }
	      }

	      return blockstate1 != null && iworldreader.func_217350_a(blockstate1, blockpos, ISelectionContext.dummy()) ? blockstate1 : null;
	   }

}
