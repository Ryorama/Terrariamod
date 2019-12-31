package ryorama.terrariamod;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class TForestBiome extends Biome
{
	protected TForestBiome()
	{
		super((new Biome.Builder())
				.surfaceBuilder(new ConfiguredSurfaceBuilder<SurfaceBuilderConfig>(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.STONE.getDefaultState())))
				.precipitation(RainType.RAIN)
				.category(Category.FOREST)
				.downfall(0.3f)
				.depth(0.1f)
				.scale(0.7f)
				.temperature(0.1f)
				.waterColor(0x00FFE8)
				.waterFogColor(0x00FFE8)
				.parent(null));
		
		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addOres(this);
		
		this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SLIME, 100, 1, 5));
		
		this.setRegistryName(Terrariamod.RegistryEvents.location("tforestbiome"));
	}	
}
