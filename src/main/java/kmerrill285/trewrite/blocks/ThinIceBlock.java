package kmerrill285.trewrite.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BubbleColumnBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ThinIceBlock extends BlockT {

	
	public ThinIceBlock(Properties properties) {
		super(properties, BlocksT.GROUND_HARDNESS, 10.0f, "thin_ice");
		this.pick = true;
	}

	public boolean canSupport(BlockState state) {
		return false;
	}

	public BlockState breakBlock(World worldIn, BlockPos pos, BlockState state) {
		 return BlocksT.AIR_BLOCK.getDefaultState();
	 }
	
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {

	}
	
   public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
	   entityIn.fall(fallDistance, 0.0F);
	   if (entityIn instanceof PlayerEntity) {
		   PlayerEntity player = (PlayerEntity)entityIn;
	       if (!worldIn.isRemote()) {
	    	   System.out.println(entityIn.getMotion().y);
			   if (entityIn.getMotion().y < 0.08f) {
				   	  worldIn.playSound(player, pos, this.soundType.getBreakSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
			    	  worldIn.setBlockState(pos, BlocksT.AIR_BLOCK.getDefaultState());
			      }
		   }
	   }
       
   }
   
   public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
	      BubbleColumnBlock.placeBubbleColumn(worldIn, pos.up(), false);
	   }

	   public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
	      worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
	   }

	   public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
	      return true;
	   }

	   /**
	    * How many world ticks before ticking
	    */
	   public int tickRate(IWorldReader worldIn) {
	      return 20;
	   }

	   public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
	      worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
	   }

	   public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
	      return false;
	   }

	   public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
	      return true;
	   }
	
}
