package kmerrill285.trewrite.world.biome.surfacebuilders;

import java.util.Random;

import kmerrill285.trewrite.blocks.BlocksT;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class TerrariaSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	
	   public TerrariaSurfaceBuilder() {
		   super(SurfaceBuilderConfig::deserialize);
	   }

	   public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
	      this.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTop(), config.getUnder(), config.getUnderWaterMaterial(), seaLevel);
	   }

	   protected void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle, BlockState bottom, int sealevel) {
	      BlockState blockstate = top;
	      BlockState blockstate1 = middle;
	      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
	      int i = -1;
	      int j = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
	      int k = x & 15;
	      int l = z & 15;

	      for(int i1 = startHeight; i1 >= 0; --i1) {
	         blockpos$mutableblockpos.setPos(k, i1, l);
	         BlockState blockstate2 = chunkIn.getBlockState(blockpos$mutableblockpos);
	         if (i1 <= j)
	         chunkIn.setBlockState(new BlockPos(x, i1, z), BlocksT.STONE_BLOCK.getDefaultState(), false);
	      }

	   }
	   
	}
