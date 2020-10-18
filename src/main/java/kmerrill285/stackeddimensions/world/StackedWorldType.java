package kmerrill285.stackeddimensions.world;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.NetherGenSettings;
import net.minecraft.world.gen.OverworldGenSettings;

public class StackedWorldType extends WorldType {
   public StackedWorldType(String name) {
      super(name);
   }

   public float getCloudHeight() {
      return 180.0F;
   }

   public ChunkGenerator createChunkGenerator(World world) {
      if (world.getDimension().getType() == DimensionType.OVERWORLD) {
         OverworldGenSettings overworldGenSettings = new OverworldGenSettings();
         OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings();
         biomeProviderSettings.setWorldInfo(world.getWorldInfo());
         biomeProviderSettings.setGeneratorSettings(overworldGenSettings);
         return new OverworldChunkGen(world, new OverworldBiomeProvider(biomeProviderSettings), overworldGenSettings);
      } else if (world.getDimension().getType() == DimensionType.THE_NETHER) {
         NetherGenSettings nethergensettings = (NetherGenSettings)ChunkGeneratorType.CAVES.createSettings();
         nethergensettings.setDefaultBlock(Blocks.NETHERRACK.getDefaultState());
         nethergensettings.setDefaultFluid(Blocks.LAVA.getDefaultState());
         return new NetherChunkGen(world, BiomeProviderType.FIXED.create(((SingleBiomeProviderSettings)BiomeProviderType.FIXED.createSettings()).setBiome(Biomes.NETHER)), nethergensettings);
      } else {
         return super.createChunkGenerator(world);
      }
   }

   public void onGUICreateWorldPress() {
      System.out.println(Minecraft.getInstance().world);
   }
}
