package kmerrill285.trewrite.world.biome.provider;

import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import kmerrill285.trewrite.util.Util;
import kmerrill285.trewrite.world.biome.BiomeT;
import net.minecraft.block.BlockState;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.gen.SimplexNoiseGenerator;
import net.minecraft.world.gen.feature.structure.Structure;

public class TerrariaBiomeProvider extends BiomeProvider {
	   public static final Biome[] biomes = new Biome[]{BiomeT.LOWLANDS, BiomeT.HIGHLANDS, BiomeT.CORRUPT_LOWLANDS, BiomeT.CORRUPT_HIGHLANDS};
	   
	   public static boolean crimson = false;
	   public static boolean jungle = false;
	   public static boolean snow = false;
	   
	   private final SharedSeedRandom randomTemp;
	   private final SharedSeedRandom randomHumidity;
	   private final SharedSeedRandom randomVariation;
	   private final SharedSeedRandom randomCorruption;

	   private final SimplexNoiseGenerator genElevation;
	   private final SimplexNoiseGenerator genTemperature;
	   private final SimplexNoiseGenerator genHumidity;
	   private final SimplexNoiseGenerator genBiomeVariation;
	   private final SimplexNoiseGenerator genCorruption;
	   private final SharedSeedRandom random;
	   private final OverworldBiomeProviderSettings settingsProvider;
	   public TerrariaBiomeProvider(OverworldBiomeProviderSettings settingsProvider) {
			  this.random = new SharedSeedRandom(settingsProvider.getWorldInfo().getSeed());
			  this.randomTemp = new SharedSeedRandom(settingsProvider.getWorldInfo().getSeed() + 1L);
			  this.randomHumidity = new SharedSeedRandom(settingsProvider.getWorldInfo().getSeed() + 2L);
			  this.randomVariation = new SharedSeedRandom(settingsProvider.getWorldInfo().getSeed() + 3L);
			  this.randomCorruption = new SharedSeedRandom(settingsProvider.getWorldInfo().getSeed() + 4L);

		      this.random.skip(17292);
		      this.genElevation = new SimplexNoiseGenerator(this.random);
		      this.genTemperature = new SimplexNoiseGenerator(this.randomTemp);
		      this.genHumidity = new SimplexNoiseGenerator(this.randomHumidity);
		      this.genBiomeVariation = new SimplexNoiseGenerator(this.randomVariation);
		      this.genCorruption = new SimplexNoiseGenerator(this.randomCorruption);
		      this.settingsProvider = settingsProvider;
	   }

	   /**
	    * Gets the biome from the provided coordinates
	    */
	   public Biome getBiome(int x, int y) {
		   
		   double X = x;
		   double Y = y;
		   
		   
		   
		   double extra = genBiomeVariation.getValue(X / 10.0, Y / 10.0) * 0.2f;
		   
		   double temp = genTemperature.getValue(X / 2500.0, Y / 2500.0) + extra * 0.05f;
		   double humidity = genHumidity.getValue(X / 2500.0, Y / 2500.0) + extra * 0.05f;
		   double elevation = genElevation.getValue(X / 2500.0, Y / 2500.0) + extra * 0.05f; 
		   double landmass = genElevation.getValue(X / 5000.0, Y / 5000.0) + extra * 0.05f;
		   double variation = genBiomeVariation.getValue(X / 100.0, Y / 100.0) + extra * 0.05f;
		   double corruption = genCorruption.getValue(X / 500.0, Y / 500.0) + extra * 0.05f;
		   
		   
		   boolean corrupted = corruption > 0.5f;
		  		   
		   double distance = Math.sqrt(X * X + Y * Y);
		   
		   Biome current = BiomeT.LOWLANDS;
		   
		   int landState = 0;
		   int OCEAN = -2, VERY_LOW = -1, LOWLANDS = 0, HILLS = 1, MOUNTAINS = 2;
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
		   
		   if (x <= -1900 && x >= 1900 && y <= 50) {
			   current = BiomeT.UNDERWORLD;
		   }
		   
		  if (x >= 0 && x <= 500) {
			  current = BiomeT.LOWLANDS;
		  }
		  if (x >= 500 && x <= 700) {
			  current = BiomeT.CORRUPT_LOWLANDS;
		  }
		  if (x >= 700 && x <= 1000) {
			  current = BiomeT.DESERT;
		  }
		  if (x >= 1000 && x <= 1300) {
			  current = BiomeT.FROZEN_PLAINS;
			  snow = true;
		  } else {
			  snow = false;
		  }
		  if (x >= 1300 && x <= 1500) {
			  jungle = true;
			  current = BiomeT.JUNGLE;
		  }
		  else {
			  jungle = false;
		  }
		  if (x >= 1500 && x <= 1800) {
			  current = BiomeT.LOWLANDS;
		  }
		  if (x >= 1800 && x <= 1900) {
			  current = BiomeT.BEACH;
		  }
		  if (x >= 1900) {
			  current = BiomeT.DEEP_WARM_OCEAN;
		  }
		  
		  if (x <= 0 && x >= -500) {
			  current = BiomeT.HALLOW;
		  }
		  
		  if (x <= -500 && x >= -700) {
			  current = BiomeT.CORRUPT_LOWLANDS;
		  }
		  if (x <= -700 && x >= -900) {
			  current = BiomeT.DESERT;
		  }
		  if (x <= -900 && x >= -1200) {
			  current = BiomeT.FROZEN_PLAINS;
			  snow = true;
		  } else {
			  snow = false;
		  }
		  if (x <= -1200 && x >= -1400) {
			  current = BiomeT.JUNGLE;
			  jungle = true;
		  }
		  else {
			  jungle = false;
		  }
		  
		  if (x <= -1400 && x >= -1700) {
			  current = BiomeT.CRIMSON;
			  crimson = true;
		  }
		  else {
			  crimson = false;
		  }
		  
		  if (x <= -1700 && x >= -1800) {
			  current = BiomeT.BEACH;
		  }
		  if (x <= -1900) {
			  current = BiomeT.DEEP_WARM_OCEAN;
		  }
		  
		   return current;
	   }

	   public Biome[] getBiomes(int x, int z, int width, int length, boolean cacheFlag) {
		      Biome[] abiome = new Biome[width * length];
		      Long2ObjectMap<Biome> long2objectmap = new Long2ObjectOpenHashMap<>();

		      for(int i = 0; i < width; ++i) {
		         for(int j = 0; j < length; ++j) {
		            int k = i + x;
		            int l = j + z;
		            long i1 = ChunkPos.asLong(k, l);
		            Biome biome = long2objectmap.get(i1);
		            if (biome == null) {
		               biome = this.getBiome(k, l);
		               long2objectmap.put(i1, biome);
		            }

		            abiome[i + j * width] = biome;
		         }
		      }

		      return abiome;
		   }

	   public Set<Biome> getBiomesInSquare(int centerX, int centerZ, int sideLength) {
		      int i = centerX - sideLength >> 2;
		      int j = centerZ - sideLength >> 2;
		      int k = centerX + sideLength >> 2;
		      int l = centerZ + sideLength >> 2;
		      int i1 = k - i + 1;
		      int j1 = l - j + 1;
		      return Sets.newHashSet(this.getBiomeBlock(i, j, i1, j1));
		   }

		   @Nullable
		   public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
		      int i = x - range >> 2;
		      int j = z - range >> 2;
		      int k = x + range >> 2;
		      int l = z + range >> 2;
		      int i1 = k - i + 1;
		      int j1 = l - j + 1;
		      Biome[] abiome = this.getBiomeBlock(i, j, i1, j1);
		      BlockPos blockpos = null;
		      int k1 = 0;

		      for(int l1 = 0; l1 < i1 * j1; ++l1) {
		         int i2 = i + l1 % i1 << 2;
		         int j2 = j + l1 / i1 << 2;
		         if (biomes.contains(abiome[l1])) {
		            if (blockpos == null || random.nextInt(k1 + 1) == 0) {
		               blockpos = new BlockPos(i2, 0, j2);
		            }

		            ++k1;
		         }
		      }

		      return blockpos;
		   }

		   public float func_222365_c(int p_222365_1_, int p_222365_2_) {
		      int i = p_222365_1_ / 2;
		      int j = p_222365_2_ / 2;
		      int k = p_222365_1_ % 2;
		      int l = p_222365_2_ % 2;
		      float f = 100.0F - MathHelper.sqrt((float)(p_222365_1_ * p_222365_1_ + p_222365_2_ * p_222365_2_)) * 8.0F;
		      f = MathHelper.clamp(f, -100.0F, 80.0F);

		      for(int i1 = -12; i1 <= 12; ++i1) {
		         for(int j1 = -12; j1 <= 12; ++j1) {
		            long k1 = (long)(i + i1);
		            long l1 = (long)(j + j1);
		            if (k1 * k1 + l1 * l1 > 4096L && this.genElevation.getValue((double)k1, (double)l1) < (double)-0.9F) {
		               float f1 = (MathHelper.abs((float)k1) * 3439.0F + MathHelper.abs((float)l1) * 147.0F) % 13.0F + 9.0F;
		               float f2 = (float)(k - i1 * 2);
		               float f3 = (float)(l - j1 * 2);
		               float f4 = 100.0F - MathHelper.sqrt(f2 * f2 + f3 * f3) * f1;
		               f4 = MathHelper.clamp(f4, -100.0F, 80.0F);
		               f = Math.max(f, f4);
		            }
		         }
		      }

		      return f;
		   }

		   public boolean hasStructure(Structure<?> structureIn) {
		      return this.hasStructureCache.computeIfAbsent(structureIn, (p_205008_1_) -> {
		         for(Biome biome : this.biomes) {
		            if (biome.hasStructure(p_205008_1_)) {
		               return true;
		            }
		         }

		         return false;
		      });
		   }

		   public Set<BlockState> getSurfaceBlocks() {
		      if (this.topBlocksCache.isEmpty()) {
		         for(Biome biome : this.biomes) {
		            this.topBlocksCache.add(biome.getSurfaceBuilderConfig().getTop());
		         }
		      }

		      return this.topBlocksCache;
		   }

}
