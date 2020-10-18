package kmerrill285.trewrite.world;

import java.util.List;
import java.util.Random;

import kmerrill285.trewrite.blocks.BasicPlant;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.world.biome.BiomeT;
import kmerrill285.trewrite.world.biome.provider.TerrariaBiomeProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.INoiseGenerator;
import net.minecraft.world.gen.NoiseChunkGenerator;
import net.minecraft.world.gen.OctavesNoiseGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.SimplexNoiseGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;

public class TerrariaChunkGenerator extends NoiseChunkGenerator<OverworldGenSettings> {
	   private static final float[] field_222576_h = Util.make(new float[25], (p_222575_0_) -> {
	      for(int i = -2; i <= 2; ++i) {
	         for(int j = -2; j <= 2; ++j) {
	            float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
	            p_222575_0_[i + 2 + (j + 2) * 5] = f;
	         }
	      }

	   });
	   private final OctavesNoiseGenerator depthNoise;
	   private final boolean field_222577_j;
	   
	   private final SharedSeedRandom random;
	   private final SharedSeedRandom randomTemp;
	   private final SharedSeedRandom randomHumidity;
	   private final SharedSeedRandom randomVariation;
	   private final SharedSeedRandom randomCorruption;

	   private final SimplexNoiseGenerator genElevation;
	   private final SimplexNoiseGenerator genTemperature;
	   private final SimplexNoiseGenerator genHumidity;
	   private final SimplexNoiseGenerator genBiomeVariation;
	   private final SimplexNoiseGenerator genCorruption;
	   
	   private final INoiseGenerator surfaceDepthNoise;

	   public TerrariaChunkGenerator(IWorld worldIn, BiomeProvider provider, OverworldGenSettings settingsIn) {
	      super(worldIn, provider, 4, 8, 256, settingsIn, true);
	      this.randomSeed.skip(2620);
	      this.depthNoise = new OctavesNoiseGenerator(this.randomSeed, 16);
	      this.field_222577_j = worldIn.getWorldInfo().getGenerator() == WorldType.AMPLIFIED;
	      
	      this.random = new SharedSeedRandom(worldIn.getWorldInfo().getSeed());
		  this.randomTemp = new SharedSeedRandom(worldIn.getWorldInfo().getSeed() + 1L);
		  this.randomHumidity = new SharedSeedRandom(worldIn.getWorldInfo().getSeed() + 2L);
		  this.randomVariation = new SharedSeedRandom(worldIn.getWorldInfo().getSeed() + 3L);
		  this.randomCorruption = new SharedSeedRandom(worldIn.getWorldInfo().getSeed() + 4L);

	      this.random.skip(17292);
	      this.genElevation = new SimplexNoiseGenerator(this.random);
	      this.genTemperature = new SimplexNoiseGenerator(this.randomTemp);
	      this.genHumidity = new SimplexNoiseGenerator(this.randomHumidity);
	      this.genBiomeVariation = new SimplexNoiseGenerator(this.randomVariation);
	      this.genCorruption = new SimplexNoiseGenerator(this.randomCorruption);
	      boolean usePerlin = true;
	      this.surfaceDepthNoise = (INoiseGenerator)(usePerlin ? new PerlinNoiseGenerator(this.randomSeed, 4) : new OctavesNoiseGenerator(this.randomSeed, 4));

	   }

	   public void spawnMobs(WorldGenRegion region) {
	      int i = region.getMainChunkX();
	      int j = region.getMainChunkZ();
	      Biome biome = region.getChunk(i, j).getBiomes()[0];
	      SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
	      sharedseedrandom.setDecorationSeed(region.getSeed(), i << 4, j << 4);
	      WorldEntitySpawner.performWorldGenSpawning(region, biome, i, j, sharedseedrandom);
	   }
	   
	 
	   
	   @Override
		public void generateSurface(IChunk chunkIn) {
		  
//		   super.generateSurface(chunkIn);
		   this.createSurface(chunkIn);
		   
		   for (int x = 0; x < 16; x++) {
			   for (int z = 0; z < 16; z++) {
				   int k1 = x + chunkIn.getPos().x * 16;
				   int l1 = z + chunkIn.getPos().z * 16;
				   Biome biome = this.biomeProvider.getBiome(k1, l1);
				   for (int y = 0; y < 255; y++) {
					   
					   if (biome == BiomeT.TERRACED_MIDLANDS) {
						   double d1 = this.surfaceDepthNoise.func_215460_a((double)k1 * 0.0625D, (double)l1 * 0.0625D, 0.0625D, (double)z * 0.0625D);
			            	d1 = (int)((d1 + 9) / 3) * 6;
			            	if (chunkIn.getBlockState(new BlockPos(x, y, z)) == Blocks.STONE.getDefaultState() && y + 1  < 255 &
						    		chunkIn.getBlockState(new BlockPos(x, y + 1, z)) != Blocks.STONE.getDefaultState()) {
						    	for (int i = 0; i < (int)d1; i++) {
						    		if (i < d1 - 5) {
							    		chunkIn.setBlockState(new BlockPos(x, y + i, z), BlocksT.STONE_BLOCK.getDefaultState(),false);
						    		} else {
						    			if (i < d1 - 1)
						    				chunkIn.setBlockState(new BlockPos(x, y + i, z), BlocksT.DIRT_BLOCK.getDefaultState(),false);
						    			else
						    			{
						    				if (i == d1 - 1) {
						    					chunkIn.setBlockState(new BlockPos(x, y + i, z), BlocksT.HIGHLANDS_GRASS.getDefaultState(),false);
							    				for (int j = i + 1; j < i + 10; j++) {
							    					chunkIn.setBlockState(new BlockPos(x, y + j, z), Blocks.AIR.getDefaultState(),false);
							    				}
						    				}
						    			}
						    		}
						    	}
						    	
						    	
								   
							   }
			            }
					   	
					   
					   
					    double underworldCeiling = this.genElevation.getValue(k1 / 10.0f, l1 / 10.0f) * 5.0f;
					    double underworldFloor = this.genTemperature.getValue(k1 / 100.0f, l1 / 100.0f) * 15.0f + underworldCeiling + 15;
					    //int undernoise = (int)(2.5f * perlin.func_151601_a((double)(x + X * 16) / 10d, (double)(z + Z * 16) / 10d));

					    
					    if (y < kmerrill285.trewrite.util.Util.caveLevel + underworldCeiling &&
					    		y > kmerrill285.trewrite.util.Util.caveLevel + underworldCeiling - 10) {
					    	chunkIn.setBlockState(new BlockPos(x, y, z), BlocksT.ASH_BLOCK.getDefaultState(), false);
					    }
					    
					    if (y == (int)kmerrill285.trewrite.util.Util.caveLevel + underworldCeiling - 8) {
					    	if (world.getRandom().nextInt(25) == 0)
					    		for (int yy = y; yy > kmerrill285.trewrite.util.Util.underworldLevel + 15; yy--)
					    			chunkIn.setBlockState(new BlockPos(x, yy, z), Blocks.LAVA.getDefaultState(), false);
					    }
					    
					    if (y <= kmerrill285.trewrite.util.Util.caveLevel + underworldCeiling - 10) {
					    	chunkIn.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), false);
					    }
					    
					    if (y <= kmerrill285.trewrite.util.Util.underworldLevel + 15) {
					    	chunkIn.setBlockState(new BlockPos(x, y, z), Blocks.LAVA.getDefaultState(), false);
					    }
					    
					    if (y <= kmerrill285.trewrite.util.Util.underworldLevel + underworldFloor) {
					    	chunkIn.setBlockState(new BlockPos(x, y, z), BlocksT.ASH_BLOCK.getDefaultState(), false);
					    }
					   
					   if (chunkIn.getBlockState(new BlockPos(x, y, z)) == Blocks.STONE.getDefaultState()) {
						   chunkIn.setBlockState(new BlockPos(x, y, z), BlocksT.STONE_BLOCK.getDefaultState(),false);
					   }
					   
					   
					   	
				   }
					   variateSurface(chunkIn, x, z);
			   }
		   }
		   
	   }
	   
	   public void createSurface(IChunk chunkIn) {
		   ChunkPos chunkpos = chunkIn.getPos();
		      int i = chunkpos.x;
		      int j = chunkpos.z;
		      SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		      sharedseedrandom.setBaseChunkSeed(i, j);
		      ChunkPos chunkpos1 = chunkIn.getPos();
		      int k = chunkpos1.getXStart();
		      int l = chunkpos1.getZStart();
		      Biome[] abiome = chunkIn.getBiomes();

		      for(int i1 = 0; i1 < 16; ++i1) {
		         for(int j1 = 0; j1 < 16; ++j1) {
		            int k1 = k + i1;
		            int l1 = l + j1;
		            int i2 = chunkIn.getTopBlockY(Heightmap.Type.WORLD_SURFACE_WG, i1, j1) + 1;
		            
		            double d1 = this.surfaceDepthNoise.func_215460_a((double)k1 * 0.0625D, (double)l1 * 0.0625D, 0.0625D, (double)i1 * 0.0625D);
		            
		            abiome[j1 * 16 + i1].buildSurface(sharedseedrandom, chunkIn, k1, l1, i2, d1, this.getSettings().getDefaultBlock(), this.getSettings().getDefaultFluid(), this.getSeaLevel(), this.world.getSeed());
		         }
		      }
		   }
	   
	   public void variateSurface (IChunk chunkIn, int x, int z) {
		   double X = x + chunkIn.getPos().x * 16;
		   double Z = z + chunkIn.getPos().z * 16;
		   
		   double extra = genBiomeVariation.getValue(X / 10.0, Z / 10.0) * 0.2f;
		   
		   double temp = genTemperature.getValue(X / 1000.0, Z / 1000.0) + extra * 0.05f;
		   double humidity = genHumidity.getValue(X / 1000.0, Z / 1000.0) + extra * 0.05f;
		   double elevation = genElevation.getValue(X / 1000.0, Z / 1000.0) + extra * 0.05f;
		   double pvariation = genBiomeVariation.getValue(X / 100.0, Z / 100.0) + extra * 0.05f;
		   double corruption = genCorruption.getValue(X / 500.0, Z / 500.0) + extra * 0.05f;
		   
		   double pathVariation = pvariation + extra * 0.25f;
		   
		   boolean corrupt = corruption > 0.5f;
		   boolean grasspath = pathVariation < 0.1f && pathVariation > -0.1f;
		   
		   int landState = 0;
		   int VERY_LOW = -1, LOWLANDS = 0, HILLS = 1, MOUNTAINS = 2;
		   int humidState = 0;
		   int DRY = 0, NORMAL = 1, HUMID = 2;
		   int tempState = 0;
		   int FREEZING = -1, COLD = -2, WARM = 1, HOT = 2;
		   
		   landState = VERY_LOW;
		   if (elevation > -0.5F) landState = LOWLANDS;
		   if (elevation > 0.0F) landState = HILLS;
		   if (elevation > 0.5F) landState = MOUNTAINS;
		   
		   humidState = DRY;
		   if (humidity > -0.5) humidState = NORMAL;
		   if (humidity > 0.5) humidState = HUMID;
		   
		   tempState = FREEZING;
		   if (temp > -0.5) tempState = COLD;
		   if (temp > 0) tempState = WARM;
		   if (temp > 0.5) tempState = HOT;
		   
		   if (grasspath) {
			   if (tempState == FREEZING) {
				   if (humidState == DRY) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
				   if (humidState == NORMAL) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
				   if (humidState == HUMID) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
			   }
			   if (tempState == COLD) {
				   if (humidState == DRY) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
				   if (humidState == NORMAL) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
				   if (humidState == HUMID) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
			   }
			   if (tempState == WARM) {
				   if (humidState == DRY) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
				   if (humidState == NORMAL) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
				   if (humidState == HUMID) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
			   }
			   if (tempState == HOT) {
				   if (humidState == DRY) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
				   if (humidState == NORMAL) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
				   if (humidState == HUMID) {
					   if (landState == VERY_LOW) {
					   }
					   if (landState == LOWLANDS) {
					   }
					   if (landState == HILLS) {
					   }
					   if (landState == MOUNTAINS) {
					   }
				   }
			   }
		   }
//		   if (overlay != null) {
//			   ConfiguredSurfaceBuilder<?> surface = overlay.getSurfaceBuilder();
//			   
//			   BlockState overlayTop = surface.config.getTop();
//			   BlockState overlayDirt = surface.config.getUnder();
//			   for (int y = 254; y > -1; y--) {
//				   BlockPos pos = new BlockPos(x, y, z);
//				   BlockState block = chunkIn.getBlockState(pos);
//				   if (block != Blocks.WATER.getDefaultState() && block != Blocks.LAVA.getDefaultState())
//				   if (y + 1 < 255) {
//					   if (chunkIn.getBlockState(new BlockPos(x, y + 1, z)) == Blocks.AIR.getDefaultState()) {
//						   if (chunkIn.getBlockState(pos) != Blocks.AIR.getDefaultState()) {
//							   chunkIn.setBlockState(pos, overlayTop, false);
//						   }
//					   } else {
//						   if (block != Blocks.STONE.getDefaultState()) {
//							   chunkIn.setBlockState(pos, overlayDirt, false);
//						   }
//					   }
//				   }
//				   
//			   }
//			   
//		   }
		   for (int y = 0; y < 255; y++) {
			   if (corrupt) {
				   BlockPos pos = new BlockPos(x, y, z);
				   BlockState block = chunkIn.getBlockState(pos);
				   if (block != Blocks.WATER.getDefaultState() && block != Blocks.LAVA.getDefaultState())
				   {
					   if (chunkIn.getBlockState(pos) == BlocksT.STONE_BLOCK.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.EBONSTONE.getDefaultState(), false);
					   }
					   if (chunkIn.getBlockState(pos) == BlocksT.SAVANNA_GRASS.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.CORRUPT_GRASS.getDefaultState(), false);
					   }
					   if (chunkIn.getBlockState(pos) == BlocksT.GRASS_BLOCK.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.CORRUPT_GRASS.getDefaultState(), false);
					   }
					   if (chunkIn.getBlockState(pos) == BlocksT.JUNGLE_GRASS.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.CORRUPT_GRASS.getDefaultState(), false);
					   }
					   if (chunkIn.getBlockState(pos) == BlocksT.HIGHLANDS_GRASS.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.CORRUPT_GRASS.getDefaultState(), false);
					   }
					   if (chunkIn.getBlockState(pos) == BlocksT.SAND.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.EBONSAND.getDefaultState(), false);
					   }
					   if (chunkIn.getBlockState(pos) == BlocksT.ICE.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.PURPLE_ICE.getDefaultState(), false);
					   }
				   }
			   }
			   
			   if (TerrariaBiomeProvider.crimson == true) {
				   BlockPos pos = new BlockPos(x, y, z);
				   BlockState block = chunkIn.getBlockState(pos);
				   if (block != Blocks.WATER.getDefaultState() && block != Blocks.LAVA.getDefaultState())
				   {
					   if (chunkIn.getBlockState(pos) == BlocksT.STONE_BLOCK.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.CRIMSTONE.getDefaultState(), false);
					   }
					   if (chunkIn.getBlockState(pos) == BlocksT.SAVANNA_GRASS.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.CRIMSON_GRASS.getDefaultState(), false);
					   }
					   if (chunkIn.getBlockState(pos) == BlocksT.GRASS_BLOCK.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.CRIMSON_GRASS.getDefaultState(), false);
					   }
					   if (chunkIn.getBlockState(pos) == BlocksT.JUNGLE_GRASS.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.CRIMSON_GRASS.getDefaultState(), false);
					   }
					   if (chunkIn.getBlockState(pos) == BlocksT.HIGHLANDS_GRASS.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.CRIMSON_GRASS.getDefaultState(), false);
					   }
				   }
			   }
			   
			   if (TerrariaBiomeProvider.jungle == true) {
				   BlockPos pos = new BlockPos(x, y, z);
				   BlockState block = chunkIn.getBlockState(pos);
				   if (block != Blocks.WATER.getDefaultState() && block != Blocks.LAVA.getDefaultState())
				   {
					   if (chunkIn.getBlockState(pos) == BlocksT.STONE_BLOCK.getDefaultState()) {
						   chunkIn.setBlockState(pos, BlocksT.MUD.getDefaultState(), false);
					   }
				   }
			   }
			   
			   BlockPos pos = new BlockPos(x, y, z);
			   if (random.nextInt(10) <= 8) {
				   if (chunkIn.getBlockState(pos) == BlocksT.SAVANNA_GRASS.getDefaultState()) {
					   if (pvariation < 0.5F)
						   chunkIn.setBlockState(pos.up(), BlocksT.TALL_SAVANNA_GRASS.getDefaultState(), false);
					   else if (random.nextInt(10) <= 1) {
						   chunkIn.setBlockState(pos.up(), BlocksT.TALL_SAVANNA_GRASS.getDefaultState(), false);
					   }
				   }
				   if (chunkIn.getBlockState(pos) == BlocksT.GRASS_BLOCK.getDefaultState() || chunkIn.getBlockState(pos) == BlocksT.JUNGLE_GRASS.getDefaultState()) {
					   if (pvariation < 0.5F)
					   {
						   if (random.nextInt(10) <= 2)
						   {
						   chunkIn.setBlockState(pos.up(), BlocksT.TALL_GRASS.getDefaultState(), false);
						   }
					   }
					   else if (random.nextInt(10) <= 1) {
						   chunkIn.setBlockState(pos.up(), BlocksT.TALL_GRASS.getDefaultState(), false);
					   }
				   }
				   if (chunkIn.getBlockState(pos) == BlocksT.GRASS_BLOCK.getDefaultState() ||
						   chunkIn.getBlockState(pos) == BlocksT.HIGHLANDS_GRASS.getDefaultState() || 
						   chunkIn.getBlockState(pos) == BlocksT.JUNGLE_GRASS.getDefaultState()) {
					   if (random.nextInt(10) <= 1) {
						   chunkIn.setBlockState(pos.up(), BlocksT.FLOWER.getDefaultState().with(BasicPlant.TYPE, random.nextInt(21)), false);
							if (random.nextInt(15) == 0) {
								chunkIn.setBlockState(pos.up(), BlocksT.MUSHROOM.getDefaultState(), false);
							}
					   }
				   }
				   
				   if (chunkIn.getBlockState(pos) == BlocksT.CORRUPT_GRASS.getDefaultState()) {
					   if (random.nextInt(10) <= 1) {
						   chunkIn.setBlockState(pos.up(), BlocksT.CORRUPTION_PLANTS.getDefaultState().with(BasicPlant.TYPE, random.nextInt(17)), false);
							if (random.nextInt(15) == 0) {
								chunkIn.setBlockState(pos.up(), BlocksT.VILE_MUSHROOM.getDefaultState(), false);
							}
					   }
				   }
			   }
			   
			   
			   
		   }
		   
		   
		   
	   }

	   protected void func_222548_a(double[] p_222548_1_, int p_222548_2_, int p_222548_3_) {
	      this.func_222546_a(p_222548_1_, p_222548_2_, p_222548_3_, (double)684.412F, (double)684.412F, 8.555149841308594D, 4.277574920654297D, 3, -10);
	   }

	   protected double func_222545_a(double p_222545_1_, double p_222545_3_, int p_222545_5_) {
	      double d1 = ((double)p_222545_5_ - (8.5D + p_222545_1_ * 8.5D / 8.0D * 4.0D)) * 12.0D * 128.0D / 256.0D / p_222545_3_;
	      if (d1 < 0.0D) {
	         d1 *= 4.0D;
	      }

	      return d1;
	   }

	   protected double[] func_222549_a(int p_222549_1_, int p_222549_2_) {
	      double[] adouble = new double[2];
	      float f = 0.0F;
	      float f1 = 0.0F;
	      float f2 = 0.0F;
	      float f3 = this.biomeProvider.func_222366_b(p_222549_1_, p_222549_2_).getDepth();

	      for(int j = -2; j <= 2; ++j) {
	         for(int k = -2; k <= 2; ++k) {
	            Biome biome = this.biomeProvider.func_222366_b(p_222549_1_ + j, p_222549_2_ + k);
	            float f4 = biome.getDepth();
	            float f5 = biome.getScale();
	            if (this.field_222577_j && f4 > 0.0F) {
	               f4 = 1.0F + f4 * 2.0F;
	               f5 = 1.0F + f5 * 4.0F;
	            }

	            float f6 = field_222576_h[j + 2 + (k + 2) * 5] / (f4 + 2.0F);
	            if (biome.getDepth() > f3) {
	               f6 /= 2.0F;
	            }

	            f += f5 * f6;
	            f1 += f4 * f6;
	            f2 += f6;
	         }
	      }

	      f = f / f2;
	      f1 = f1 / f2;
	      f = f * 0.9F + 0.1F;
	      f1 = (f1 * 4.0F - 1.0F) / 8.0F;
	      adouble[0] = (double)f1 + this.func_222574_c(p_222549_1_, p_222549_2_);
	      adouble[1] = (double)f;
//	      adouble[0] += 10;
//	      adouble[1] += 10;
	      adouble[0] += 1.4;
	      
	      return adouble;
	   }

	   private double func_222574_c(int p_222574_1_, int p_222574_2_) {
	      double d0 = this.depthNoise.func_215462_a((double)(p_222574_1_ * 200), 10.0D, (double)(p_222574_2_ * 200), 1.0D, 0.0D, true) / 8000.0D;
	      if (d0 < 0.0D) {
	         d0 = -d0 * 0.3D;
	      }

	      d0 = d0 * 3.0D - 2.0D;
	      if (d0 < 0.0D) {
	         d0 = d0 / 28.0D;
	      } else {
	         if (d0 > 1.0D) {
	            d0 = 1.0D;
	         }

	         d0 = d0 / 40.0D;
	      }

	      return d0;
	   }

	   public List<Biome.SpawnListEntry> getPossibleCreatures(EntityClassification creatureType, BlockPos pos) {
//	      if (Feature.SWAMP_HUT.func_202383_b(this.world, pos)) {
//	         if (creatureType == EntityClassification.MONSTER) {
//	            return Feature.SWAMP_HUT.getSpawnList();
//	         }
//
//	         if (creatureType == EntityClassification.CREATURE) {
//	            return Feature.SWAMP_HUT.getCreatureSpawnList();
//	         }
//	      } else if (creatureType == EntityClassification.MONSTER) {
//	         if (Feature.PILLAGER_OUTPOST.isPositionInStructure(this.world, pos)) {
//	            return Feature.PILLAGER_OUTPOST.getSpawnList();
//	         }
//
//	         if (Feature.OCEAN_MONUMENT.isPositionInStructure(this.world, pos)) {
//	            return Feature.OCEAN_MONUMENT.getSpawnList();
//	         }
//	      }

	      return super.getPossibleCreatures(creatureType, pos);
	   }

	   public void spawnMobs(ServerWorld worldIn, boolean spawnHostileMobs, boolean spawnPeacefulMobs) {
	      
	   }

	   public int getGroundHeight() {
	      return this.world.getSeaLevel() + 1;
	   }

	   public int getSeaLevel() {
	      return 63 + 30;
	   }
	   
//	   public void decorate(WorldGenRegion region) {
////		   Random random = new Random();
////		      for (int x = 0; x < 16; x++) {
////		    	  for (int y = 0; y < 16; y++) {
////		    		  for (int z = 0; z < 16; z++) {
////		    			  BlockPos pos = new BlockPos(x, y, z);	
////		    			  genForestFeatures(region, pos, x, y, z, random);
////		    		  }
////		    	  }
////		      }
//	   }
	   
	   public void genForestFeatures(WorldGenRegion region, BlockPos pos, int x, int y, int z, Random random) {
		   if (region.getBlockState(pos) == BlocksT.GRASS_BLOCK.getDefaultState()) {
				  if (random.nextInt(100) <= 10) {
					region.setBlockState(pos.up(), BlocksT.FLOWER.getDefaultState().with(BasicPlant.TYPE, random.nextInt(21)), 2);
					if (random.nextInt(15) == 0) {
						region.setBlockState(pos.up(), BlocksT.MUSHROOM.getDefaultState(), 2);
					}
				}
				  if (randomSeed.nextInt(250) <= 1 && y + 1 < 255) {
						int tree = randomSeed.nextInt(5) + 5;
						region.setBlockState(pos.up(), BlocksT.FOREST_TREE.getDefaultState().with(BasicPlant.TYPE, 0), 2);
						for (int i = 2; i < tree; i++) {
							
							if (y + i < 255) {
								if (randomSeed.nextInt(tree) <= 1) {
									region.setBlockState(pos.up(i), BlocksT.FOREST_TREE.getDefaultState().with(BasicPlant.TYPE, randomSeed.nextInt(4) + 1), 2);
								} else {
									region.setBlockState(pos.up(i), BlocksT.FOREST_TREE.getDefaultState().with(BasicPlant.TYPE, 1), 2);
								}
							}
						}
						if (y + tree < 255 && randomSeed.nextInt(10) <= 8)
							region.setBlockState(pos.up(tree), BlocksT.FOREST_TREE.getDefaultState().with(BasicPlant.TYPE, 6), 2);

					}
			  }
	   }
	}