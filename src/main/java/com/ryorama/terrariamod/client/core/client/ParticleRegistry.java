package com.ryorama.terrariamod.client.core.client;

import com.ryorama.terrariamod.TerrariaMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ParticleRegistry {

    public static DefaultParticleType FOREST_LEAF = FabricParticleTypes.simple();

    public static void init() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(TerrariaMod.MODID, "leaf"), FOREST_LEAF);
    }
}
