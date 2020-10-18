package kmerrill285.trewrite.blocks;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Nullable;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Bed extends BlockT {

	   public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	   public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	   protected static final VoxelShape AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D);

	
	public Bed(float hardness, float difficulty, String name) {
		super(Properties.create(Material.EARTH).sound(SoundType.WOOD), hardness, difficulty, name);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(HALF, DoubleBlockHalf.LOWER));
		this.setLocation(name);
		this.pick = true;
	}
	

	   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      return AABB;
	   }

	   /**
	    * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
	    * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
	    * returns its solidified counterpart.
	    * Note that this method should ideally consider only the specific face passed in.
	    */
	   public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
	      if (isValidPosition(stateIn, worldIn, currentPos)) {
			   return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	      } else {
	    	  if (!worldIn.getWorld().isRemote()) {
		    	  HashMap<String, BlockPos> spawns = WorldStateHolder.get(worldIn.getWorld().getServer().getWorld(DimensionType.OVERWORLD)).spawnPositions;
				  ArrayList<String> removed = new ArrayList<String>();
		    	  for (String player : spawns.keySet()) {
					  BlockPos spawn = spawns.get(player);
					  if (spawn.equals(currentPos)) {
						  removed.add(player);
					  }
				  }
		    	  for (String str : removed) spawns.remove(str);
	    	  }
	    	  return BlocksT.AIR_BLOCK.getDefaultState();
	      }
		   
	   }

	   /**
	    * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
	    * Block.removedByPlayer
	    */
	   public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
	      super.harvestBlock(worldIn, player, pos, BlocksT.AIR_BLOCK.getDefaultState(), te, stack);
	   }

	   public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
			if (state.get(HALF) == DoubleBlockHalf.LOWER) {
				super.onReplaced(state, worldIn, pos, newState, isMoving);
			}
		}
	   
	   public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		   if (!worldIn.isRemote) {
			   HashMap<String, BlockPos> spawns = WorldStateHolder.get(worldIn.getServer().getWorld(DimensionType.OVERWORLD)).spawnPositions;
			   BlockPos spawn = spawns.get(player.getScoreboardName());
			   
			   if (spawn != null && spawn.equals(pos)) {
				   spawns.put(player.getScoreboardName(), null);
				   player.setSpawnDimenion(DimensionType.OVERWORLD);
	//			   player.wakeUp();
				   if (worldIn instanceof ServerWorld)
				   worldIn.getServer().getPlayerList().sendMessage(new StringTextComponent("Spawnpoint removed!").applyTextStyle(TextFormatting.BOLD));
			   } else {
				   spawns.put(player.getScoreboardName(), pos);
				   player.setSpawnDimenion(worldIn.getDimension().getType());
				   if (worldIn instanceof ServerWorld) {
						worldIn.getServer().getPlayerList().sendMessage(new StringTextComponent("Spawnpoint set!").applyTextStyle(TextFormatting.BOLD));
				   }
			   }
		   }
		   
		   return true;
	   }
	   
	   /**
	    * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
	    * this block
	    */
	   public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
	      DoubleBlockHalf doubleblockhalf = state.get(HALF);
	      BlockPos blockpos = doubleblockhalf == DoubleBlockHalf.LOWER ? pos.up() : pos.down();
	      BlockState blockstate = worldIn.getBlockState(blockpos);
	      if (blockstate.getBlock() == this && blockstate.get(HALF) != doubleblockhalf) {
	         worldIn.setBlockState(blockpos, BlocksT.AIR_BLOCK.getDefaultState(), 35);
	         worldIn.playEvent(player, 2001, blockpos, Block.getStateId(blockstate));
	         ItemStack itemstack = player.getHeldItemMainhand();
	         if (!worldIn.isRemote && !player.isCreative()) {
//	            Block.spawnDrops(state, worldIn, pos, (TileEntity)null, player, itemstack);
//	            Block.spawnDrops(blockstate, worldIn, blockpos, (TileEntity)null, player, itemstack);
	        	 if (state.getBlock() instanceof BlockT) {
						if (ItemsT.getItemFromString(((BlockT)state.getBlock()).drop) != null) {
							Item drop = ItemsT.getItemFromString(((BlockT)state.getBlock()).drop);
							EntityItemT.spawnItem(worldIn, pos, new ItemStackT(drop, 1));
						}
						
					}
	         }
	      }

	      super.onBlockHarvested(worldIn, pos, state, player);
	   }

	   public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
	      return false;
	   }

	   @Nullable
	   public BlockState getStateForPlacement(BlockItemUseContext context) {
	      BlockPos blockpos = context.getPos();
	      if (blockpos.getY() < 255 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context)) {
	         World world = context.getWorld();
	         BlockState state = this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()).with(HALF, DoubleBlockHalf.LOWER);
	         BlockPos pos2 = blockpos.add(context.getPlacementHorizontalFacing().getXOffset(), 0, context.getPlacementHorizontalFacing().getZOffset());
	         if (world.getBlockState(pos2).getMaterial().isReplaceable())
	        	 world.setBlockState(pos2, state.with(HALF, DoubleBlockHalf.UPPER), 3);
	         else return null;
	         return state;
	      } else {
	         return null;
	      }
	   }

	   /**
	    * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	    */
	   public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		   
	   }


	  
	   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
	      Direction facing = state.get(FACING);
	      int x = facing.getXOffset();
	      int z = facing.getZOffset();
	      if (state.get(HALF) == DoubleBlockHalf.LOWER) {
	         return worldIn.getBlockState(pos.add(x, 0, z)).getBlock() == this;
	      } else {
	         return worldIn.getBlockState(pos.add(-x, 0, -z)).getBlock() == this;
	      }
	   }
	   /**
	    * @deprecated call via {@link IBlockState#getMobilityFlag()} whenever possible. Implementing/overriding is fine.
	    */
	   public PushReaction getPushReaction(BlockState state) {
	      return PushReaction.DESTROY;
	   }

	   /**
	    * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
	    * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
	    */
	   public BlockRenderLayer getRenderLayer() {
	      return BlockRenderLayer.CUTOUT;
	   }

	   /**
	    * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
	    * blockstate.
	    * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever possible. Implementing/overriding is
	    * fine.
	    */
	   public BlockState rotate(BlockState state, Rotation rot) {
	      return state.with(FACING, rot.rotate(state.get(FACING)));
	   }

	   /**
	    * Return a random long to be passed to {@link IBakedModel#getQuads}, used for random model rotations
	    */
	   @OnlyIn(Dist.CLIENT)
	   public long getPositionRandom(BlockState state, BlockPos pos) {
	      return MathHelper.getCoordinateRandom(pos.getX(), pos.down(state.get(HALF) == DoubleBlockHalf.LOWER ? 0 : 1).getY(), pos.getZ());
	   }

	   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		   super.fillStateContainer(builder);
	      builder.add(HALF, FACING);
	   }
		   
}
