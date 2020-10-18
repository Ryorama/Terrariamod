package kmerrill285.trewrite.world.biome.features.mushroom;

import java.awt.Color;
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

public class MassiveMushroomFeature extends Feature<NoFeatureConfig> {
		
	   public MassiveMushroomFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
	      super(configFactoryIn);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		   if (rand.nextInt(100) <= 15)
		   for(int i = 0; i < rand.nextInt(5) + 5; ++i) {
	         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
	         if (worldIn.getBlockState(blockpos.up()) == BlocksT.AIR_BLOCK.getDefaultState())
	         if (worldIn.getBlockState(blockpos) == BlocksT.MUSHROOM_GRASS.getDefaultState()) {
	        	 int rad = 2;
	        	 tree(pos, worldIn, rand);
	        	 break;
	         }
	      }

	      return true;
	   }
	   
	   
	   Color[][][] stuff = new Color[80][80][80];
	   
	   public void tree(BlockPos pos, IWorld worldIn, Random r) {
		   
			double stemRad = r.nextInt(3);
			double dirX = r.nextDouble() * 2 - 1;
			double dirZ = r.nextDouble() * 2 - 1;
			int height = r.nextInt(10) + 15;
			int capDepth = r.nextInt(5) + 5;
			double capRad = 2;
			double capSpread = r.nextDouble();
			double capSpread2 = r.nextDouble() * 2 - 1;
			
			dirX *= 0.5;
			dirZ *= 0.5;
			
			if (capSpread2 < -capSpread) capSpread2 = -capSpread;
			BlockPos newPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
			int SR = (int)stemRad;
			for (int y = -5; y < height; y++) {
				for (int x = -SR; x < SR; x++) {
					for (int z = -SR; z < SR; z++) {
						newPos = new BlockPos(pos.getX() + x + dirX * y, pos.getY() + y, pos.getZ() + z + dirZ * y);
						double dist = Math.sqrt(x * x + z * z);
						if (dist <= stemRad) 
						{
							if (worldIn.isAreaLoaded(newPos, 1)) {
								worldIn.setBlockState(newPos, BlocksT.MUSHROOM_STEM_BLOCK.getDefaultState(), 2);
							}
						}
					}
				}
				if (stemRad > 1) stemRad *= 0.98f;
			}
			
			for (int y = 0; y < capDepth; y++) {
				for (int x = -20; x < 20; x++) {
					for (int z = -20; z < 20; z++) {
						double dist = Math.sqrt(x * x + z * z);
						if (dist <= capRad && dist >= capRad - 2) {
							BlockPos pos2 = new BlockPos(newPos.getX() + x - dirX * y, newPos.getY() - y, newPos.getZ() + z - dirZ * y);
							if (worldIn.isAreaLoaded(pos2, 1)) {
								worldIn.setBlockState(pos2, BlocksT.MUSHROOM_BLOCK.getDefaultState(), 2);
							}
						}
					}
				}
				if (y < capDepth / 2) {
					capRad += capSpread;
				} else {
					capRad += capSpread2;
				}
			}
		}
		
		
	   
	   
	}
