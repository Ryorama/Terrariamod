package kmerrill285.trewrite.world.biome.features.underworld;

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

public class StalagmiteFeature extends Feature<NoFeatureConfig> {
		
	   public StalagmiteFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
	      super(configFactoryIn);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		   if (rand.nextInt(100) <= 10)
		   for(int i = 0; i < rand.nextInt(5) + 5; ++i) {
	         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
	         if (rand.nextBoolean()) {
	        	 if (blockpos.getY() >= 180)
	        	 if (worldIn.getBlockState(blockpos.down()) == BlocksT.AIR_BLOCK.getDefaultState())
	    	         if (worldIn.getBlockState(blockpos) == BlocksT.ASH_BLOCK.getDefaultState()) {
	    	        	 generate(blockpos.add(0, 8, 0), worldIn, rand, true);
	    	        	 break;
	    	         }
	         } else {
	        	 if (worldIn.getBlockState(blockpos.up()) == Blocks.LAVA.getDefaultState())
	    	         if (blockpos.down().getY() < 0) {
	    	        	 generate(blockpos, worldIn, rand, false);
	    	        	 break;
	    	         }
	         }
	         
	      }

	      return true;
	   }
	   
	   public void generate(BlockPos pos, IWorld worldIn, Random rand, boolean top) {
		   //2
		   if (top == false && rand.nextBoolean()) {
				   int r1 = 10 + rand.nextInt(10);
				   double r2 = 5;
				   BlockPos.MutableBlockPos bpos = new BlockPos.MutableBlockPos(0, 0, 0);
				   double dx = 0;
				   double dy = 0;
				   
				   int dir = rand.nextInt(2) * 2 - 1;
				   
				   for (int y = 0; y < 190; y++) {
					   dx += (rand.nextDouble() - 0.5) * 0.1;
					   dy += (rand.nextDouble() - 0.5) * 0.1;
					   
					   r2 += rand.nextDouble() - 0.5;
					   
					   int rad = (int)(r1 + r2);
					   double xx = Math.cos(Math.PI * ((y + dx * 25) / 180.0)) * dir * 5;
					   double zz = Math.sin(Math.PI * ((y + dy * 25) / 180.0)) * dir * 5;
					   
					   for (int x = -rad; x < rad; x++) {
						   for (int z = -rad; z < rad; z++) {
							   double dist = Math.sqrt(x * x + z * z);
							   if (dist <= rad) {
								   bpos.setPos(pos.getX() + x + xx, y, pos.getZ() + z + zz);
								   worldIn.setBlockState(bpos, BlocksT.ASH_BLOCK.getDefaultState(), 2);
							   }
						   }
					   }
				   }
			   
		   }
		   else {
			   double rad = rand.nextInt(25) + 25;
			   double dec = rand.nextDouble();
			   if (top == false) rad += 8;
			   int i = 0;
			   BlockPos.MutableBlockPos bpos = new BlockPos.MutableBlockPos(0, 0, 0);
			   double ox = 0;
			   double oz = 0;
			   while (rad > 1) {
				   ox += (rand.nextDouble() - 0.5) * 0.5;
				   oz += (rand.nextDouble() - 0.5) * 0.5;
				   for (int x = (int)-rad; x < rad; x++) {
					   for (int z = (int)-rad; z < rad; z++) {
						   double dist = Math.sqrt(x * x + z * z);
						   if (dist <= rad) {
							   if (top) 
								   bpos.setPos(pos.getX() + x, pos.getY() - i, pos.getZ() + z);
							   else bpos.setPos(pos.getX() + x, pos.getY() + i, pos.getZ() + z);
							  bpos.setPos(bpos.getX() + (int)ox, bpos.getY(), bpos.getZ() + (int)oz);
							  if (bpos.getY() > 0)
							   worldIn.setBlockState(bpos, BlocksT.ASH_BLOCK.getDefaultState(), 2);
						   }
					   }
				   }
				   i++;
				   rad *= (0.95 + dec + 2) / 4.0;
			   }
		   }
	   }
	   
	   
	}
