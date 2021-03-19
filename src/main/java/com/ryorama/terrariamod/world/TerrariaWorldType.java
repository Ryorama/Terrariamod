package com.ryorama.terrariamod.world;

import java.util.ArrayList;
import java.util.List;

import com.ryorama.terrariamod.world.generator.TerrariaBiomeSource;
import com.ryorama.terrariamod.world.generator.TerrariaChunkGenerator;

import net.minecraft.client.world.GeneratorType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BuiltinBiomes;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;

public class TerrariaWorldType extends GeneratorType {
    public TerrariaWorldType() {
        super("terraria");
        GeneratorType.VALUES.add(this);
    }

    @Override
    protected ChunkGenerator getChunkGenerator(Registry<Biome> biomeRegistry, Registry<ChunkGeneratorSettings> chunkGeneratorSettingsRegistry, long seed) {
    	List<Biome> biomeList = new ArrayList<Biome>();
    	biomeList.add(BuiltinBiomes.PLAINS);
    	
    	return new TerrariaChunkGenerator(new TerrariaBiomeSource(biomeList, seed), seed);
    }
}
