package kmerrill285.stackeddimensions.world;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class OverworldChunkGen extends OverworldChunkGenerator {
   public OverworldChunkGen(IWorld worldIn, BiomeProvider provider, OverworldGenSettings settingsIn) {
      super(worldIn, provider, settingsIn);
   }

   public void generateSurface(IChunk chunkIn) {
      super.generateSurface(chunkIn);
      MutableBlockPos pos = new MutableBlockPos(0, 0, 0);

      for(int x = 0; x < 16; ++x) {
         for(int y = 0; y < 5; ++y) {
            for(int z = 0; z < 16; ++z) {
               chunkIn.setBlockState(pos.setPos(x, y, z), Blocks.STONE.getDefaultState(), false);
            }
         }
      }

   }
}
