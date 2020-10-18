package kmerrill285.trewrite.blocks;

import java.util.Random;

import kmerrill285.trewrite.blocks.CrossedBlock.Shape;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BasicPlant extends BasicBlock {
	
	public static VoxelShape MUSHROOM_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D);
	public static final IntegerProperty TYPE = BlockStateProperties.AGE_0_25;
	
	public Shape shape;
	
	public BasicPlant(Properties properties, float hardness, float difficulty, boolean pick, boolean axe,
			boolean hammer, boolean material, String name, String drop) {
		super(properties, hardness, difficulty, pick, axe, hammer, material, name, drop);
		this.setDefaultState(this.stateContainer.getBaseState().with(TYPE, Integer.valueOf(0)));
	}
	
	public BasicPlant(Properties properties, float hardness, float difficulty, boolean pick, boolean axe,
			boolean hammer, boolean material, String name, int health, int mana, boolean consumable, String drop) {
		super(properties, hardness, difficulty, pick, axe, hammer, material, name, health, mana, consumable, drop);
		this.setDefaultState(this.stateContainer.getBaseState().with(TYPE, Integer.valueOf(0)));
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !stateIn.isValidPosition(worldIn, currentPos) ? BlocksT.AIR_BLOCK.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	
	@OnlyIn(value=Dist.CLIENT)
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		
		
		
		if (shape != null)
	      switch (shape) {
	      	case MUSHROOM:
	      		return MUSHROOM_SHAPE;
	      	case BLOCK:
	      		return super.getShape(state, worldIn, pos, context);
	      }
	      return super.getShape(state, worldIn, pos, context);
	}
	
	public boolean isSolid(BlockState state) {
		return false;
	}
	
	public BasicPlant setShape(Shape shape) {
		this.shape = shape;
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

   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
      BlockPos blockpos = pos.down();
      BlockState BlockState = worldIn.getBlockState(blockpos);
      Block block = BlockState.getBlock();
      
      if (block == BlocksT.AIR_BLOCK) return false;
      
      if (block instanceof BlockT) {
    	  return allowed.contains(((BlockT)block).name);
      }
      return BlockState.isSolid();
   }
   
   /**
    * Get the OffsetType for this Block. Determines if the model is rendered slightly offset.
    */
   public OffsetType getOffsetType() {
      return OffsetType.XZ;
   }

   /**
    * Return a random long to be passed to {@link IBakedModel#getQuads}, used for random model rotations
    */
   @OnlyIn(Dist.CLIENT)
   public long getPositionRandom(BlockState state, BlockPos pos) {
      return MathHelper.getCoordinateRandom(pos.getX(), pos.getY(), pos.getZ());
   }
   
   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	   super.fillStateContainer(builder);
	      builder.add(TYPE);
	   }
}
