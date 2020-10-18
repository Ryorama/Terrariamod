package kmerrill285.trewrite.world.biome.features.savanna;

import java.util.Random;

import com.google.common.base.Function;
import com.mojang.datafixers.Dynamic;

import kmerrill285.trewrite.blocks.BlocksT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class TermiteMoundFeature extends Feature<NoFeatureConfig> {
	
	   public TermiteMoundFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
	      super(configFactoryIn);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
	       if (rand.nextInt(25) <= 3)
		   for(int i = 0; i < rand.nextInt(5) + 5; ++i) {
	         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
	         if (worldIn.getBlockState(blockpos) == BlocksT.SAVANNA_GRASS.getDefaultState()) {
	        	 int rad = rand.nextInt(5) + 4;
	            pillar(worldIn, rand, pos.add(rad, -1, rad), rad);
	            carve(worldIn, rand, pos.add(rad, 1, rad), rad);
	         }
	      }

	      return true;
	   }
	   
	   public void pillar (IWorld worldIn, Random rand, BlockPos pos, int rad) {
		   double r = (double)rad;
		   int height = rand.nextInt(10) + 8;
		   for (int y = -2; y < height; y++) {
			   r -= 1.0 / (double)height;
			   for (int x = -rad; x < rad; x++) {
				   for (int z = -rad; z < rad; z++) {
					   
					   
					   
					   double dist = Math.sqrt(x * x + z * z);
					   
					   
					   if (y < 0 && dist <= r)
					   {
						   BlockPos pos2 = pos.add(x, y, z);
						   for (int Y = 0; Y < rand.nextInt(5) + 1; Y++)
						   if (worldIn.isAreaLoaded(pos2, 1)) {
							   if (rand.nextInt(10) <= 7)
							   worldIn.setBlockState(pos2.add(0, Y, 0), BlocksT.DIRT_BLOCK.getDefaultState(), 2);
						   }
					   }
					   if (dist <= r && dist >= r - 1)
					   {
						   BlockPos pos2 = pos.add(x, y, z);
						   if (worldIn.isAreaLoaded(pos2, 1)) {
							   if (rand.nextInt(10) <= 7)
							   worldIn.setBlockState(pos2, BlocksT.DIRT_BLOCK.getDefaultState(), 2);
						   }
					   }
				   }
			   }
		   }
	   }
	   
	   public void carve(IWorld worldIn, Random rand, BlockPos pos, int rad) {
		   double r = (double)rad;
		   int height = rand.nextInt(10) + 15;
		   
		   double dir = rand.nextDouble() - 0.5;
		   
		   double xd = rand.nextDouble() - 0.5;
		   double zd = rand.nextDouble() - 0.5;
		   
		   int X = (int) (xd * height);
		   int Z = (int) (zd * height);
		   
		   
		   double R = 10;
		   
		   int Y = (int) (height - R);
		   
//		   for (int x = (int)-R * 2; x < R * 2; x++) {
//			   for (int y = (int)-R * 2; y < R * 2; y++) {
//				   for (int z = (int)-R * 2; z < R * 2; z++) {
//					   double dist = Math.sqrt(x * x + y * y + z * z);
//					   BlockPos pos2 = pos.add(x + X, -Y + y, z + Z);
//					   if (worldIn.isAreaLoaded(pos2, 1))
//					   {
//						   if (dist <= R) {
//							   worldIn.setBlockState(pos2, BlocksT.AIR_BLOCK.getDefaultState(), 2);
//						   }
//						   if (dist >= R - 2 && dist <= R) {
//							   if (rand.nextInt(10) <= 7)
//								   worldIn.setBlockState(pos2, BlocksT.DIRT_BLOCK.getDefaultState(), 2);
//						   }
//					   }
//				   }
//			   }
//		   }
		   
		   for (int y = 0; y < height; y++) {
			   r -= 1.0 / (double)height;
			   
			   double sine = Math.sin(Math.toRadians(y * 10 * dir));
			   double cos = Math.cos(Math.toRadians(y * 10 * dir));
			   r += (rand.nextDouble() - 0.5) * 2;
			   for (int x = -rad; x < rad; x++) {
				   for (int z = -rad; z < rad; z++) {
					   double dist = Math.sqrt(x * x + z * z);
					   
					   if (dist <= r - 1) {
						   BlockPos pos2 = pos.add(x + (int)(xd * y), -y, z + (int)(zd * y));
						   if (worldIn.isAreaLoaded(pos2, 1))
							   worldIn.setBlockState(pos2, BlocksT.AIR_BLOCK.getDefaultState(), 2);
					   }
					   
					   if (dist <= r && dist >= r - 1)
					   {
						   BlockPos pos2 = pos.add(x + (int)(xd * y), -y, z + (int)(zd * y));
						   if (worldIn.isAreaLoaded(pos2, 1)) {
							   if (rand.nextInt(10) <= 7)
							   worldIn.setBlockState(pos2, BlocksT.DIRT_BLOCK.getDefaultState(), 2);
						   }
					   }
					   
						   
				   }
			   }
		   }
		   
		   
		   
	   }
	   
	}
