package com.ryorama.terrariamod;

import com.mojang.serialization.Codec;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.client.particles.ParticlesT;
import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.world.TerrariaChunkGenerator;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

import java.util.function.Supplier;

public class TerrariaMod {
    public static final String MOD_ID = "terrariamod";
    public static TerrariaModConfig CONFIG = new TerrariaModConfig();

    public static final DeferredRegister<Codec<? extends ChunkGenerator>> CHUNK_GENERATORS = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.CHUNK_GENERATOR);

    public static final RegistrySupplier<Codec<? extends ChunkGenerator>> TERRARIA_CHUNK_GENERATOR = registerChunkGenerator("terraria_chunk_generator", () -> TerrariaChunkGenerator.CODEC);

    public static void init() {
        AutoConfig.register(TerrariaModConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(TerrariaModConfig.class).getConfig();

        CHUNK_GENERATORS.register();
        BuffsT.init();
        ParticlesT.init();
        BlocksT.init();
        EntitiesT.init();
        ItemsT.init();
    }

    static RegistrySupplier<Codec<? extends ChunkGenerator>> registerChunkGenerator(String name, Supplier<Codec<? extends ChunkGenerator>> generator) {
        return CHUNK_GENERATORS.register(new Identifier(TerrariaMod.MOD_ID, name), generator);
    }
}
