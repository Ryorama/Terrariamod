package ryorama.terrariamod;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary.Type;

public class TForest {
	public static Biome terrforestbiome;
	
	public static void registerBiome()
	{
		registerBiome(terrforestbiome, Type.FOREST, Type.HOT);
	}
	
	public static void registerBiome(Biome biome, Type... types)
	{
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
	}
}