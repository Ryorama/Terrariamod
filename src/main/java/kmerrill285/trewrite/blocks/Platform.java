package kmerrill285.trewrite.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.SlabType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class Platform extends BasicBlock {
	 public static final EnumProperty<SlabType> TYPE = BlockStateProperties.SLAB_TYPE;
	   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	   public static final VoxelShape BOTTOM_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
	   public static final VoxelShape TOP_SHAPE = Block.makeCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	public Platform(Properties properties, boolean material, String name) {
		super(properties, 15, 15, true, true, true, material, name, name);
		this.setDefaultState(this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	 public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
	      return worldIn.getMaxLightLevel();
	   }

	   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		   super.fillStateContainer(builder);
	      builder.add(TYPE, WATERLOGGED);
	   }
	
//		public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      SlabType slabtype = state.get(TYPE);
	      switch(slabtype) {
	      case DOUBLE:
	         return VoxelShapes.fullCube();
	      case TOP:
	         return TOP_SHAPE;
	      default:
	         return BOTTOM_SHAPE;
	      }
	   }
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	public boolean isTopSolid(BlockState state) {
	      return state.get(TYPE) == SlabType.DOUBLE || state.get(TYPE) == SlabType.TOP;
	   }
	
//	public BlockFaceShape getBlockFaceShape(IBlockReader worldIn, BlockState state, BlockPos pos, Direction face) {
//	      SlabType slabtype = state.get(TYPE);
//	      if (slabtype == SlabType.DOUBLE) {
//	         return BlockFaceShape.SOLID;
//	      } else if (face == Direction.UP && slabtype == SlabType.TOP) {
//	         return BlockFaceShape.SOLID;
//	      } else {
//	         return face == Direction.DOWN && slabtype == SlabType.BOTTOM ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
//	      }
//	   }
	
	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state) {
	      if (state.get(WATERLOGGED)) {
	         worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(false)), 3);
	         return Fluids.WATER;
	      } else {
	         return Fluids.EMPTY;
	      }
	   }
	
	 public IFluidState getFluidState(BlockState state) {
	      return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	   }

	   public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
	      return state.get(TYPE) != SlabType.DOUBLE && !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	   }
	   

	   public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
	      if (state.get(TYPE) != SlabType.DOUBLE && !state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
	         if (!worldIn.isRemote()) {
	            worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
	            worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
	         }

	         return true;
	      } else {
	         return false;
	      }
	   }
	   
	   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		      if (stateIn.get(WATERLOGGED)) {
		         worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		      }

		      return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		   }

		   public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		      switch(type) {
		      case LAND:
		         return state.get(TYPE) == SlabType.BOTTOM;
		      case WATER:
		         return worldIn.getFluidState(pos).isTagged(FluidTags.WATER);
		      case AIR:
		         return false;
		      default:
		         return false;
		      }
		   }

}
