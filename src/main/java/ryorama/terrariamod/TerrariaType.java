package ryorama.terrariamod;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;
import ryorama.terrariamod.TForest;

public class TerrariaType extends WorldType
{
	public TerrariaType()
	{
		super("terraria_type");
	}
	
	@Override
	public ChunkGenerator<?> createChunkGenerator(World world)
	{
		OverworldGenSettings settings = new OverworldGenSettings();
		SingleBiomeProviderSettings single = new SingleBiomeProviderSettings();
		single.setBiome(TForest.terrforestbiome);
		return new OverworldChunkGenerator(world, new SingleBiomeProvider(single), settings);
	}
}
