package kmerrill285.trewrite.world.biome.features;

import java.util.Random;

import com.google.common.base.Function;
import com.mojang.datafixers.Dynamic;

import kmerrill285.trewrite.blocks.BasicPlant;
import kmerrill285.trewrite.blocks.BlockT;
import kmerrill285.trewrite.blocks.BlocksT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class PalmTreeFeature extends Feature<NoFeatureConfig> {
	
	   public PalmTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
	      super(configFactoryIn);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
	      for(int i = 0; i < 10; ++i) {
	         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
	         if (worldIn.isAirBlock(blockpos)) {
	            int j = 1 + rand.nextInt(5) + 8;
	            
	            if (tree(blockpos, worldIn, BlocksT.PALM_TREE, rand, j, BlocksT.SAND)) return true;
	            
	         }
	      }

	      return true;
	   }
	   
	   public boolean tree(BlockPos blockpos, IWorld worldIn, BlockT tree, Random rand, int j, BlockT...growsOn) {
		   for (int g = 0; g < growsOn.length; g++)
		   if (worldIn.getBlockState(blockpos.down()) == growsOn[g].getDefaultState()) {
           	for(int k = 0; k < j - 1; ++k) {
           	   if (k == 0) {
           		   worldIn.setBlockState(blockpos.up(k), tree.getDefaultState().with(BasicPlant.TYPE, 0), 2);
           	   } else {
           		   if (rand.nextInt(j) <= 4) {
	            		   worldIn.setBlockState(blockpos.up(k), tree.getDefaultState().with(BasicPlant.TYPE, rand.nextInt(4) + 2), 2);
           		   } else {
           			   worldIn.setBlockState(blockpos.up(k), tree.getDefaultState().with(BasicPlant.TYPE, 1), 2);
           		   }
           	   }
           	   if (k == j - 2) {
           		   worldIn.setBlockState(blockpos.up(k), tree.getDefaultState().with(BasicPlant.TYPE, 6), 2);
           	   }
                 
              }
           	return true;
           }
		   return false;
	   }
	}
