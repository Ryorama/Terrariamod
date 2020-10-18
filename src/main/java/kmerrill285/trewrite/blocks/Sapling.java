package kmerrill285.trewrite.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import kmerrill285.trewrite.world.biome.features.TerrariaFeatures;
import kmerrill285.trewrite.world.biome.features.TreeFeature;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class Sapling extends CrossedBlock {
	
	public Tree tree;
	
	public Sapling(String name, Tree tree) {
		super(Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement(), 0, 0, true, true, true, false, name, 0, 0, false, "");
		this.tree = tree;
		
	}
	
	public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
//		if (random.nextInt(10) <= 2)
		if (!worldIn.isRemote) {
			int j = 1 + random.nextInt(5) + 8;
			if (worldIn.getBlockState(pos.down()).getBlock() instanceof BlockT)
			TerrariaFeatures.TREES.tree(pos, worldIn, tree, random, j, (BlockT)worldIn.getBlockState(pos.down()).getBlock());
	        for (double i = 0; i < j; i += 0.25)
			worldIn.addParticle(ParticleTypes.CLOUD, pos.getX(), pos.getY() + i, pos.getZ(), random.nextDouble() - 0.5f, random.nextDouble() - 0.5f, random.nextDouble() - 0.5f);
		}
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		BlockPos blockpos = currentPos;
	      BlockPos down = new BlockPos(blockpos.getX(), blockpos.getY() - 1, blockpos.getZ());
	      World world = worldIn.getWorld();
	      Block b = world.getBlockState(down).getBlock();
	      if (b == BlocksT.GRASS_BLOCK) {
	    	  return BlocksT.FOREST_SAPLING.getDefaultState();
	      }
	      if (b == BlocksT.CORRUPT_GRASS) {
	    	  return BlocksT.CORRUPT_SAPLING.getDefaultState();
	      }
	      if (b == BlocksT.SAND) {
	    	  return BlocksT.PALM_SAPLING.getDefaultState();
	      }
	      if (b == BlocksT.SNOW) {
	    	  return BlocksT.BOREAL_SAPLING.getDefaultState();
	      }
	      if (b == BlocksT.JUNGLE_GRASS) {
	    	  return BlocksT.JUNGLE_SAPLING.getDefaultState();
	      }
	      return BlocksT.FOREST_SAPLING.getDefaultState();
	}
	
	@Nullable
	   public BlockState getStateForPlacement(BlockItemUseContext context) {
	      BlockPos blockpos = context.getPos();
	      BlockPos down = new BlockPos(blockpos.getX(), blockpos.getY() - 1, blockpos.getZ());
	      World world = context.getWorld();
	      Block b = world.getBlockState(down).getBlock();
	      if (b == BlocksT.GRASS_BLOCK) {
	    	  return BlocksT.FOREST_SAPLING.getDefaultState();
	      }
	      if (b == BlocksT.CORRUPT_GRASS) {
	    	  return BlocksT.CORRUPT_SAPLING.getDefaultState();
	      }
	      if (b == BlocksT.SAND) {
	    	  return BlocksT.PALM_SAPLING.getDefaultState();
	      }
	      if (b == BlocksT.SNOW) {
	    	  return BlocksT.BOREAL_SAPLING.getDefaultState();
	      }
	      if (b == BlocksT.JUNGLE_GRASS) {
	    	  return BlocksT.JUNGLE_SAPLING.getDefaultState();
	      }
	      return BlocksT.FOREST_SAPLING.getDefaultState();
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
	
	public boolean isFullCube(BlockState state) {
	      return false;
	   }

	   public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
	      return 0;
	   }
		
		public BlockRenderLayer getRenderLayer() {
			return BlockRenderLayer.CUTOUT;
		}

}
