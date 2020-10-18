package kmerrill285.trewrite.blocks;

import javax.annotation.Nullable;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DoubleBlock extends BlockT {

	   
	   public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	  

	   public DoubleBlock(Properties properties, String name, String drop) {
			super(properties.doesNotBlockMovement(), 0, 0, name);
			this.setDefaultState(this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER));
			this.setLocation(name);
			this.pick = true;
			this.drop = drop;
		}
	   
	public DoubleBlock(String name, String drop) {
		super(Properties.create(Material.EARTH).sound(SoundType.STONE).doesNotBlockMovement(), 0, 0, name);
		this.setDefaultState(this.stateContainer.getBaseState().with(HALF, DoubleBlockHalf.LOWER));
		this.setLocation(name);
		this.pick = true;
		this.drop = drop;
	}

	   /**
	    * Spawns the block's drops in the world. By the time this is called the Block has possibly been set to air via
	    * Block.removedByPlayer
	    */
	   public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		   if (state.get(HALF) == DoubleBlockHalf.LOWER)
	      super.harvestBlock(worldIn, player, pos, BlocksT.AIR_BLOCK.getDefaultState(), te, stack);
	   }

	   public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
//		   if (state.get(HALF) == DoubleBlockHalf.LOWER)
//			super.onReplaced(state, worldIn, pos, newState, isMoving);
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
	      return true;
	   }

	   @Nullable
	   public BlockState getStateForPlacement(BlockItemUseContext context) {
	      BlockPos blockpos = context.getPos();
	      if (blockpos.getY() < 255 && context.getWorld().getBlockState(blockpos.up()).isReplaceable(context)) {
	         World world = context.getWorld();
	         boolean flag = world.isBlockPowered(blockpos) || world.isBlockPowered(blockpos.up());
	         BlockState state = this.getDefaultState().with(HALF, DoubleBlockHalf.LOWER);
		      world.setBlockState(blockpos.up(), state.with(HALF, DoubleBlockHalf.UPPER), 3);
		      world.setBlockState(blockpos, state.with(HALF, DoubleBlockHalf.LOWER), 3);
	         return state;
	      } else {
	         return null;
	      }
	   }

	   /**
	    * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	    */
	   public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
	      worldIn.setBlockState(pos.up(), state.with(HALF, DoubleBlockHalf.UPPER), 3);
	      worldIn.setBlockState(pos, state.with(HALF, DoubleBlockHalf.LOWER), 3);
	   }

	   /**
	    * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
	    * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
	    */
	   public BlockRenderLayer getRenderLayer() {
	      return BlockRenderLayer.CUTOUT;
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
	      builder.add(HALF);
	   }
		   
}
