package kmerrill285.trewrite.world.biome.features.purity;

import java.awt.Color;
import java.util.Random;

import com.google.common.base.Function;
import com.mojang.datafixers.Dynamic;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.world.biome.features.LSystem.LSystem;
import kmerrill285.trewrite.world.biome.features.LSystem.LSystemPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class LivingwoodForestTreeFeature extends Feature<NoFeatureConfig> {
	
	
	   public LivingwoodForestTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
	      super(configFactoryIn);
	   }

	   public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		   if (rand.nextInt(10) <= 3)
		   for(int i = 0; i < rand.nextInt(5) + 5; ++i) {
	         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
	         if (worldIn.getBlockState(blockpos) == BlocksT.GRASS_BLOCK.getDefaultState() || worldIn.getBlockState(blockpos) == BlocksT.PODZOL.getDefaultState() || worldIn.getBlockState(blockpos) == BlocksT.HIGHLANDS_GRASS.getDefaultState()) {
	        	 int rad = 2;
	        	 tree(pos, worldIn, rand);
	        	 break;
	         }
	      }

	      return true;
	   }
	   
	   
	   Color[][][] stuff = new Color[80][80][80];
	   
	   public void tree(BlockPos pos, IWorld worldIn, Random r) {
		   stuff = new Color[80][80][80];
			Random rand = new Random(r.nextLong());
			LSystemPos lpos = new LSystemPos(32, -3, 32);
			
			String[] tree = {"place","place<75","rotrand<50", "splitrand<75","place<75"};
	   	 	
	   	 	LSystem stem = new LSystem(lpos, 10, 4, 7.0, 2,tree);
		 	stem.run(rand, 10);
		 	
		 	boolean swap = rand.nextBoolean();
		 	stuff = stem.stuff;
		 	for (int x = 0; x < 80; x++) {
		 		for (int y = 0; y < 80; y++) {
		 			for (int z = 0; z < 80; z++) {
		 				int X = x;
		 				int Z = z;
		 				if (swap)
		 				{
		 					X = z;
		 					Z = x;
		 				}
		 				BlockPos pos1 = new BlockPos(pos.getX() + X - 32, pos.getY() + y - 2, pos.getZ() + Z - 32);
		 				if (worldIn.isAreaLoaded(pos1, 1))
		 					if (worldIn.getBlockState(pos1) == BlocksT.HIGHLANDS_GRASS.getDefaultState()||
		 							worldIn.getBlockState(pos1) == BlocksT.JUNGLE_GRASS.getDefaultState()||
		 							worldIn.getBlockState(pos1) == BlocksT.SAVANNA_GRASS.getDefaultState()) {
		 						for(int i = 0; i < 64; i++) {
		 							if (stuff[x][i][z] != null) {
		 								worldIn.setBlockState(pos1, BlocksT.PODZOL.getDefaultState(), 2);
		 								break;
		 							}
		 						}
		 					}
		 				
			 			if (stuff[x][y][z] == Color.BLACK) {
			 				BlockPos pos2 = new BlockPos(pos.getX() + X - 32, pos.getY() + y - 2, pos.getZ() + Z - 32);
			 				if (worldIn.isAreaLoaded(pos2, 1))
			 				worldIn.setBlockState(pos2, BlocksT.LIVING_WOOD.getDefaultState(), 2);
			 			}
			 			if (stuff[x][y][z] == Color.GREEN) {
			 				
			 				BlockPos pos2 = new BlockPos(pos.getX() + X - 32, pos.getY() + y - 2, pos.getZ() + Z - 32);
			 				if (worldIn.isAreaLoaded(pos2, 1))
			 				worldIn.setBlockState(pos2, BlocksT.LEAVES.getDefaultState(), 2);
			 			}
		 			}
		 		}
		 	}
		}
		
	   
	}
