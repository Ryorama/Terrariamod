package kmerrill285.trewrite.world.dimension;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import kmerrill285.trewrite.blocks.BasicPlant;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.world.biome.BiomeT;
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

public class TerrariaUnderworldChunkGenerator extends NoiseChunkGenerator<OverworldGenSettings> {
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
	   
	   private final VoronoiTest voronoi;

	   public TerrariaUnderworldChunkGenerator(IWorld worldIn, BiomeProvider provider, OverworldGenSettings settingsIn) {
	      super(worldIn, provider, 4, 8, 127, settingsIn, true);
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
	      this.voronoi = new VoronoiTest();
	      
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
		   BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(0, 0, 0);
		   Random random = new Random();
		   
		   for (int x = 0; x < 16; x++) {
			   for (int z = 0; z < 16; z++) {
				   int k1 = x + chunkIn.getPos().x * 16;
				   int l1 = z + chunkIn.getPos().z * 16;
				   Biome biome = this.biomeProvider.getBiome(k1, l1);
				   for (int y = 0; y < 156; y++) {
					   
					   
					    pos.setPos(x, y, z);
					    double underworldCeiling = this.genElevation.getValue(k1 / 10.0f, l1 / 10.0f) * 5.0f;
					    double underworldFloor = this.genTemperature.getValue(k1 / 100.0f, l1 / 100.0f) * 15.0f + underworldCeiling + 15;
					    //int undernoise = (int)(2.5f * perlin.func_151601_a((double)(x + X * 16) / 10d, (double)(z + Z * 16) / 10d));
					    chunkIn.setBlockState(pos, BlocksT.STONE_BLOCK.getDefaultState(), false);
					    
					    int top = 200;
					    
					    if (y < top + underworldCeiling &&
					    		y > top + underworldCeiling - 25) {
					    	chunkIn.setBlockState(pos, BlocksT.ASH_BLOCK.getDefaultState(), false);
					    }
					    
					    if (y == (int)(top + underworldCeiling - 10)) {
					    	if (world.getRandom().nextInt(10000) == 0)
					    		for (int yy = y; yy > 0; yy--)
					    			chunkIn.setBlockState(pos.setPos(x, yy, z), Blocks.LAVA.getDefaultState(), false);
					    }
					    
					    if (y <= top + underworldCeiling - 10) {
					    	chunkIn.setBlockState(pos.setPos(x, y, z), BlocksT.AIR_BLOCK.getDefaultState(), false);
					    }
					    
					    if (y <= 15) {
					    	chunkIn.setBlockState(pos.setPos(x, y, z), Blocks.LAVA.getDefaultState(), false);
					    }
					    
					    //hillFeature
					    if (y <= underworldFloor * 1.5 - 8) {
					    	chunkIn.setBlockState(pos.setPos(x, y, z), BlocksT.ASH_BLOCK.getDefaultState(), false);
					    }
					    
					    
					   
					   if (chunkIn.getBlockState(pos.setPos(x, y, z)) == Blocks.STONE.getDefaultState()) {
						   chunkIn.setBlockState(pos.setPos(x, y, z), BlocksT.STONE_BLOCK.getDefaultState(),false);
					   }
					   
					   
					   	
				   }
			   }
		   }
		   
		   
		   for (int x = 0; x < 16; x++) {
			   for (int z = 0; z < 16; z++) {
				   int k1 = x + chunkIn.getPos().x * 16;
				   int l1 = z + chunkIn.getPos().z * 16;
				   Biome biome = this.biomeProvider.getBiome(k1, l1);
				   
				   
				   double dist = Integer.MAX_VALUE;
				   int height = (int)this.voronoi.getVoronoiAt(k1, l1, this.voronoi.voronoiSize, 5f);

				   double height2 = this.voronoi.getVoronoiAt(k1, l1, this.voronoi.voronoiSize / 2, 50.0f) - 25;
				   
				   Point p = this.voronoi.getPointAt(k1, l1, this.voronoi.voronoiSize / 2, 50.0f);
				   
				   
				   int B = 25;
				   for (int y = 0; y < 50; y++) {
					   pos.setPos(k1, y, l1);
					   
					   if (height2 > -10) {
						   
						   for (int i = 0; i < B; i++)
						   if (y <= height * 4) {
							   
							   boolean lessn10 = false;
								int count = 0;
								A:
								for (int xx = -1; xx < 2; xx++) {
									for (int yy = -1; yy < 2; yy++) {
										double C = this.voronoi.getVoronoiAt(k1 + xx * (i * 2), l1 + yy * (i * 2), this.voronoi.voronoiSize / 2, 50.0f) - 25;
										if (C <= -10) {
											count++;
											if (count > 3) {
												lessn10 = true;
												break A;
											}
										}
									}
								}
								
								if (!lessn10)
								{
								   int xx = k1 - p.x;
								   int zz = l1 - p.y;
								   
								   chunkIn.setBlockState(pos.setPos(xx + p.x, y - i, zz + p.y), BlocksT.ASH_BLOCK.getDefaultState(), false);
								   
								}
								
						   }
						   
						   for (int i = 0; i < B; i++)
							   if (y <= height * 4) {
								   
								   boolean lessn10 = false;
									int count = 0;
									A:
									for (int xx = -1; xx < 2; xx++) {
										for (int yy = -1; yy < 2; yy++) {
											double C = this.voronoi.getVoronoiAt(k1 + xx * (i * 3), l1 + yy * (i * 3), this.voronoi.voronoiSize / 2, 50.0f) - 25;
											if (C <= -10) {
												count++;
												if (count > 3) {
													lessn10 = true;
													break A;
												}
											}
										}
									}
									
									if (!lessn10)
									{
									   int xx = k1 - p.x;
									   int zz = l1 - p.y;									   
									   chunkIn.setBlockState(pos.setPos(xx + p.x, y + i, zz + p.y), BlocksT.ASH_BLOCK.getDefaultState(), false);
									   pos.setPos(x, y, z);
									   if (chunkIn.getBlockState(pos).getBlock() == BlocksT.ASH_BLOCK) {
										   if (random.nextInt(10000) <= 2) {
											  int rad = random.nextInt(5) + 5;
											  for (int X = -rad; X < rad; X++) {
												  for (int Y = -rad; Y < rad; Y++) {
													  for (int Z = -rad; Z < rad; Z++) {
														  double dist1 = Math.sqrt(X * X + Y * Y + Z * Z);
														  if (dist1 <= rad) {
															  pos.setPos(x + X, y + Y, z + Z);
															  if (chunkIn.getBlockState(pos).getMaterial().blocksMovement() || chunkIn.getBlockState(pos).getBlock() == Blocks.LAVA) {
																  pos.setPos(x + X, y + Y - 1, z + Z);
																  if (chunkIn.getBlockState(pos).getMaterial().blocksMovement() || chunkIn.getBlockState(pos).getBlock() == Blocks.LAVA) {
																	  pos.setPos(x + X, y + Y, z + Z);
																	  chunkIn.setBlockState(pos, Blocks.LAVA.getDefaultState(), false);
																  }
															  }
															  
														  }
													  }
												  }
											  }
										   }
									   }
									}
									
							   }
						   
//						   if (y == 64 + height * 10) {
//							   int xx = k1 - p.x;
//							   int zz = l1 - p.y;
//							   BlockPos pos2 = new BlockPos(xx + p.x, y + 1, zz + p.y);
//							   
//							   chunkIn.setBlockState(pos2, BlocksT.GRASS_BLOCK.getDefaultState(), false);
//						   }
					   }
					   
					   
					   
						   
				   }
				  
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
		      double d0 = 0.0625D;
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
	   }

	   protected void func_222548_a(double[] p_222548_1_, int p_222548_2_, int p_222548_3_) {
	      double d0 = (double)684.412F;
	      double d1 = (double)684.412F;
	      double d2 = 8.555149841308594D;
	      double d3 = 4.277574920654297D;
	      int i = -10;
	      int j = 3;
	      this.func_222546_a(p_222548_1_, p_222548_2_, p_222548_3_, (double)684.412F, (double)684.412F, 8.555149841308594D, 4.277574920654297D, 3, -10);
	   }

	   protected double func_222545_a(double p_222545_1_, double p_222545_3_, int p_222545_5_) {
	      double d0 = 8.5D;
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
	      int i = 2;
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
	   
	   public void genForestFeatures(WorldGenRegion region, BlockPos pos, int x, int y, int z, Random random) {
		   
	   }
	}