package kmerrill285.trewrite.world.biome.features;

import java.util.Random;

import com.google.common.base.Function;
import com.mojang.datafixers.Dynamic;

import kmerrill285.trewrite.blocks.BlocksT;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class PotFeature extends Feature<NoFeatureConfig> {
	
	   public PotFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
	      super(configFactoryIn);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
	      
		   for(int i = 0; i < 10; ++i) {
	         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
	         if (worldIn.isAirBlock(blockpos)) {
	        	 if (worldIn.getBlockState(new BlockPos(blockpos.getX(), blockpos.getY() - 1, blockpos.getZ())).getBlock() == BlocksT.ASH_BLOCK) {
	        		 worldIn.setBlockState(blockpos, BlocksT.OBSIDIAN_POT.getDefaultState(), 0);
	        	 }
	            if (worldIn.getBlockState(new BlockPos(blockpos.getX(), blockpos.getY() - 1, blockpos.getZ())).getBlock() == BlocksT.STONE_BLOCK||
	            		worldIn.getBlockState(new BlockPos(blockpos.getX(), blockpos.getY() - 1, blockpos.getZ())).getBlock() == Blocks.STONE) {
	            	if (rand.nextInt(100) <= 25) {
	            		if (worldIn.getDimension().getType().getRegistryName().toString().contains("under") ||
	            				blockpos.getY() <= 105) {
		            		worldIn.setBlockState(blockpos, BlocksT.POT.getDefaultState(), 0);
	            		}
	            	}
	            }
	         }
	      }

	      return true;
	   }
	}
