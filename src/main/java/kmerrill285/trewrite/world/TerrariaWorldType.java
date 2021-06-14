package kmerrill285.trewrite.world;

import kmerrill285.trewrite.util.Util;
import kmerrill285.trewrite.world.biome.provider.TerrariaBiomeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.OverworldBiomeProviderSettings;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.NetherGenSettings;
import net.minecraft.world.gen.OverworldGenSettings;

public class TerrariaWorldType extends WorldType {

	public TerrariaWorldType(String name) {
		super(name);
	}

//	public TerrariaServerWorld(MinecraftServer p_i50703_1_, Executor p_i50703_2_, SaveHandler p_i50703_3_,
//			WorldInfo p_i50703_4_, DimensionType p_i50703_5_, IProfiler p_i50703_6_, IChunkStatusListener p_i50703_7_) {
	
	public float getCloudHeight() {
		return 180;
	}
	
	@Override

    public ChunkGenerator<?> createChunkGenerator(World world)

    {
//		WorldEntitySpawner
    	if (world.getDimension().getType() == DimensionType.OVERWORLD)

    	{
    		System.out.println("ovrwerld");
	        OverworldGenSettings overworldGenSettings = new OverworldGenSettings();
	        
//	        SingleBiomeProviderSettings biomeProviderSettings = new SingleBiomeProviderSettings();
	        OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings();
	        biomeProviderSettings.setWorldInfo(world.getWorldInfo());

	        biomeProviderSettings.setGeneratorSettings(overworldGenSettings);	
//	        biomeProviderSettings.setBiome(BiomeT.LOWLANDS);

	        if (Util.stacked_dimensions == false) {
	        	return new TerrariaChunkGenerator(world, new TerrariaBiomeProvider(biomeProviderSettings), overworldGenSettings);
	        }
	        else {
	        	return new TerrariaChunkGenerator2(world, new TerrariaBiomeProvider(biomeProviderSettings), overworldGenSettings);
	        }
		}
    	
//    	else if (world.getDimension().getType() == Dimensions.THE_SKY) {
//    		System.out.println("skai");
//    		 OverworldGenSettings overworldGenSettings = new OverworldGenSettings();
// 	        
// 	        OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings();
// 	        biomeProviderSettings.setWorldInfo(world.getWorldInfo());
//
// 	        biomeProviderSettings.setGeneratorSettings(overworldGenSettings);	
// 	        
// 	        return new TerrariaSkyChunkGenerator(world, new TerrariaBiomeProvider(biomeProviderSettings), overworldGenSettings);
//    	}

		else if (world.getDimension().getType() == DimensionType.THE_NETHER)

		{

			NetherGenSettings nethergensettings = ChunkGeneratorType.CAVES.createSettings();

			nethergensettings.setDefaultBlock(Blocks.NETHERRACK.getDefaultState());

			nethergensettings.setDefaultFluid(Blocks.LAVA.getDefaultState());



			// The nether shares biome provider settings with the overworld

			OverworldBiomeProviderSettings biomeProviderSettings = new OverworldBiomeProviderSettings();

			biomeProviderSettings.setWorldInfo(world.getWorldInfo());

			return ChunkGeneratorType.CAVES.create(world, new SingleBiomeProvider(new SingleBiomeProviderSettings().setBiome(Biomes.NETHER)), nethergensettings);

		}

    	else

    	{

    		return super.createChunkGenerator(world);

    	}

    }
	
	public void onGUICreateWorldPress() {
		System.out.println(Minecraft.getInstance().world);
		
	}
	
}