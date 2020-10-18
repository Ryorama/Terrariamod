package kmerrill285.trewrite.world.biome.features.icespikes;

import java.util.Random;

import com.google.common.base.Function;
import com.mojang.datafixers.Dynamic;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.world.biome.features.LSystem.LSystemPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class IceSpikesFeature extends Feature<NoFeatureConfig> {
	
	
	
	   public IceSpikesFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
	      super(configFactoryIn);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		   if (rand.nextInt(50) <= 5)
		   for(int i = 0; i < rand.nextInt(5) + 5; ++i) {
		         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
		         if (worldIn.getBlockState(blockpos) == BlocksT.ICE.getDefaultState() && worldIn.getBlockState(blockpos.down()) == BlocksT.STONE_BLOCK.getDefaultState()) {
		        	 int iterations = rand.nextInt(50) + 15;
		        	 double rad = rand.nextInt(5) + 5;
		        	 double radDown = 0.95;
		        	 
		        	 double dirX = rand.nextDouble() * 2 - 1;
		        	 double dirZ = rand.nextDouble() * 2 - 1;
		        	 
		        	 LSystemPos lpos = new LSystemPos(blockpos.getX(), blockpos.getY(), blockpos.getZ());
		        	 while (iterations > 0) {
		        		 for (int x = (int)-rad; x < rad; x++) {
		        			 for (int z = (int)-rad; z < rad; z++) {
		        				 double dist = Math.sqrt(x * x + z * z);
		        				 if (dist <= rad) {
		        					 BlockPos pos2 = new BlockPos(lpos.getX() + x, lpos.getY(), lpos.getZ() + z);
		        					 if (worldIn.isAreaLoaded(pos2, 1))
		        						 worldIn.setBlockState(pos2, BlocksT.ICE.getDefaultState(), 2);
		        				 }
		        			 }
		        		 }
		        		 if (rad > 0) {
		        			 lpos.x += dirX;
		        			 lpos.z += dirZ;
		        			 lpos.y ++;
		        			 rad *= radDown;
		        		 }
		        		 iterations--;
		        	 }
		        	 break;
		         }
		      }

		      return true;
	   }
}
