package kmerrill285.stackeddimensions.world;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.NetherChunkGenerator;
import net.minecraft.world.gen.NetherGenSettings;

public class NetherChunkGen extends NetherChunkGenerator {
   public NetherChunkGen(World worldIn, BiomeProvider provider, NetherGenSettings settingsIn) {
      super(worldIn, provider, settingsIn);
   }

   public void makeBedrock(IChunk c, Random rand) {
   }
}
