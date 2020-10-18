package kmerrill285.trewrite.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockAirT extends BlockT implements IWaterLoggable {

	public static BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public BlockAirT() {
		super(Properties.create(Material.AIR).doesNotBlockMovement(), 0, 0, "none");
	    this.setDefaultState(this.stateContainer.getBaseState().with(BlockT.light, 0).with(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		super.fillStateContainer(builder);
        builder.add(WATERLOGGED);
    }
	
	public BlockRenderType getRenderType(BlockState state) {
	      return BlockRenderType.INVISIBLE;
	   }

   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
      return VoxelShapes.empty();
   }

   public boolean isAir(BlockState state) {
      return true;
   }

   public IFluidState getFluidState(BlockState state) {
	      return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	   }
   
   public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		worldIn.setBlockState(pos, BlocksT.AIR_BLOCK.getDefaultState());
	}
}
