package com.ryorama.terrariamod.world.biomes;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class LowlandsBiome extends Biome {

	protected LowlandsBiome() {
		super(new Builder().surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlocksT.GRASS_BLOCK.getDefaultState(), BlocksT.DIRT_BLOCK.getDefaultState(), BlocksT.STONE_BLOCK.getDefaultState()))).precipitation(RainType.RAIN).category(Category.PLAINS).depth(0.235f).scale(0.05f).temperature(1f).downfall(1f).waterColor(4159204).waterFogColor(329011).parent((String)null));
		this.setRegistryName(TerrariaMod.modid, "lowlands");
	}
}
