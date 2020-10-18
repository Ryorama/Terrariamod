package kmerrill285.trewrite.world.biome.features;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import kmerrill285.trewrite.world.biome.provider.TerrariaBiomeProvider;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;

public class UndergroundHouseFeature extends Feature<NoFeatureConfig> {	
	
	public UndergroundHouseFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
		int i = pos.getX();
		int k = pos.getZ();
		if (rand.nextInt(900) == 0) {
			i += rand.nextInt(16) + 8;
				k += rand.nextInt(16) + 8;
				int j = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, i, k);
				j -= 1;
				Template template = ((ServerWorld) worldIn.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("trewrite", "underground_house"));
				if (template == null)
					return false;
				Rotation rotation = Rotation.values()[rand.nextInt(3)];
				Mirror mirror = Mirror.values()[rand.nextInt(2)];
				BlockPos spawnTo = new BlockPos(i, j - 24, k);
				template.addBlocksToWorldChunk(worldIn, spawnTo, new PlacementSettings().setRotation(rotation).setRandom(rand).setMirror(mirror)
						.setChunk((ChunkPos) null).setIgnoreEntities(false));
				return true;
		}
		return false;
	}
}
