package kmerrill285.trewrite.blocks;

import java.util.Random;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FallingBlock extends BasicBlock {
	
	public FallingBlock(Properties properties, float hardness, float difficulty, boolean pick, boolean axe,
			boolean hammer, boolean material, String name, String drop) {
		super(properties, hardness, difficulty, pick, axe, hammer, material, name, drop);
	}

	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
	      if (!worldIn.isRemote) {
	         this.checkFallable(worldIn, pos);
	      }
//	      this.onPlayerDestroy(worldIn, pos, state);
	   }
	
	public void onPlayerDestroy (IWorld worldIn, BlockPos pos, BlockState state) {
		if (!worldIn.isRemote())
			
			if (state.getBlock() instanceof BlockT) {
				if (ItemsT.getItemFromString(((BlockT)state.getBlock()).drop) != null) {
					Item drop = ItemsT.getItemFromString(((BlockT)state.getBlock()).drop);
					EntityItemT.spawnItem(worldIn.getWorld(), pos, new ItemStackT(drop, 1));
				}
				
			}
	}
	
	public BlockState breakBlock(World worldIn, BlockPos pos, BlockState state) {
//		 if (!worldIn.isRemote)
//				if (state.getBlock() instanceof BlockT) {
//					if (ItemsT.getItemFromString(((BlockT)state.getBlock()).drop) != null) {
//						ItemT drop = ItemsT.getItemFromString(((BlockT)state.getBlock()).drop);
//						EntityItemT.spawnItem(worldIn, pos, new ItemStackT(drop, 1));
//					}
//				}
		 return BlocksT.AIR_BLOCK.getDefaultState();
	 }
	
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
//		if (newState != Blocks.AIR.getDefaultState()) return;
//		if (!isMoving)
//		if (!worldIn.isRemote)
//			
//			if (state.getBlock() instanceof BlockT) {
//				if (ItemsT.getItemFromString(((BlockT)state.getBlock()).drop) != null) {
//					ItemT drop = ItemsT.getItemFromString(((BlockT)state.getBlock()).drop);
//					EntityItemT.spawnItem(worldIn, pos, new ItemStackT(drop, 1));
//				}
//				
//			}
	}
	
	public int tickRate(IWorldReader worldIn) {
	      return 2;
	   }
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
	      worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, this.tickRate(worldIn));
	      return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	   }
	
	public void onBlockAdded(BlockState p_220082_1_, World worldIn, BlockPos pos, BlockState p_220082_4_, boolean p_220082_5_) {
	      worldIn.getPendingBlockTicks().scheduleTick(pos, this, this.tickRate(worldIn));
	   }
	
	public static boolean canFallThrough(BlockState state) {
	      Block block = state.getBlock();
	      Material material = state.getMaterial();
	      return state.isAir() || block == Blocks.FIRE || material.isLiquid() || material.isReplaceable();
	   }
	
	private void checkFallable(World worldIn, BlockPos pos) {
	      if (worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down())) && pos.getY() >= 0) {
	         if (!worldIn.isRemote) {
	            FallingBlockEntity fallingblockentity = new FallingBlockEntity(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
	            worldIn.addEntity(fallingblockentity);
	         }
	      }
	   }
	
	@OnlyIn(Dist.CLIENT)
	   public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	      if (rand.nextInt(16) == 0) {
	         BlockPos blockpos = pos.down();
	         if (worldIn.isAirBlock(blockpos) || canFallThrough(worldIn.getBlockState(blockpos))) {
	            double d0 = (double)((float)pos.getX() + rand.nextFloat());
	            double d1 = (double)pos.getY() - 0.05D;
	            double d2 = (double)((float)pos.getZ() + rand.nextFloat());
	            worldIn.addParticle(new BlockParticleData(ParticleTypes.FALLING_DUST, stateIn), d0, d1, d2, 0.0D, 0.0D, 0.0D);
	         }
	      }

	   }
	

}
