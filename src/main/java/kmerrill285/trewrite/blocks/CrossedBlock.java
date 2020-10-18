package kmerrill285.trewrite.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class CrossedBlock extends BasicBlock {
	
	public static VoxelShape MUSHROOM_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
	public static VoxelShape BLOCK_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	public static VoxelShape SMALL_GRASS_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

	public Shape shape = Shape.BLOCK;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	public CrossedBlock(Properties properties, float hardness, float difficulty, boolean pick, boolean axe,
			boolean hammer, boolean material, String name, String drop) {
		super(properties, hardness, difficulty, pick, axe, hammer, material, name, drop);
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	public CrossedBlock(Properties properties, float hardness, float difficulty, boolean pick, boolean axe,
			boolean hammer, boolean material, String name, int health, int mana, boolean consumable, String drop) {
		super(properties, hardness, difficulty, pick, axe, hammer, material, name, health, mana, consumable, drop);
		this.setDefaultState(this.getDefaultState().with(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		 if (shape == Shape.BLOCK) return BLOCK_SHAPE;
		 if (shape == Shape.SMALL_GRASS) return SMALL_GRASS_SHAPE;
		 if (shape == Shape.MUSHROOM) return MUSHROOM_SHAPE; 
	      return super.getShape(state, worldIn, pos, context);
	   }
	
	public boolean isSolid(BlockState state) {
		return false;
	}
	
	public CrossedBlock setShape(Shape block) {
		this.shape = block;
		return this;
	}
	
   public boolean isFullCube(BlockState state) {
	   return false;
   }

   public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
      return 0;
   }
	
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
   public enum Shape {
	   BLOCK, MUSHROOM, SMALL_GRASS
   }
   
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
	      return !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	   }
	   

	   public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
	      if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
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
		      return !this.isValidPosition(stateIn, worldIn, currentPos) ? breakBlock(worldIn.getWorld(), facingPos, facingState) : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
		   }
	   
	   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		   super.fillStateContainer(builder);
		      builder.add(WATERLOGGED);
		   }
	   
	   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		      BlockPos blockpos = pos.down();
		      BlockState BlockState = worldIn.getBlockState(blockpos);
		      Block block = BlockState.getBlock();
		      
		      if (block == BlocksT.AIR_BLOCK) return false;
		      
		      return BlockState.isSolid();
		   }
}
