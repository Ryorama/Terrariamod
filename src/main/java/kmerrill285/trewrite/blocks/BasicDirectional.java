package kmerrill285.trewrite.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;

public class BasicDirectional extends BasicBlock {

	public static final DirectionProperty FACING = DirectionalBlock.FACING;
	
	
	public BasicDirectional(Properties properties, float hardness, float difficulty, boolean pick, boolean axe,
			boolean hammer, boolean material, String name, String drop) {
		super(properties, hardness, difficulty, pick, axe, hammer, material, name, drop);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
	}
	
	   public BlockState getStateForPlacement(BlockItemUseContext context) {
		   Direction direction = context.getNearestLookingDirection().getOpposite();
	      return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	   }
	   
	   public BlockState rotate(BlockState state, Rotation rot) {
	      return state.with(FACING, rot.rotate(state.get(FACING)));
	   }

	   /**
	    * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
	    * blockstate.
	    * @deprecated call via {@link IBlockState#withMirror(Mirror)} whenever possible. Implementing/overriding is fine.
	    */
	   public BlockState mirror(BlockState state, Mirror mirrorIn) {
	      return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	   }
	  


	   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		   super.fillStateContainer(builder);
	      builder.add(FACING);
	   }

}
