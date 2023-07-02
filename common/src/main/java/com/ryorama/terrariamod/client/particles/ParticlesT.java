package com.ryorama.terrariamod.client.particles;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.mixin.DefaultParticleTypeMixin;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ParticlesT {

    public static DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(TerrariaMod.MOD_ID, RegistryKeys.PARTICLE_TYPE);

    public static final RegistrySupplier<DefaultParticleType> FLAME = register("flame", () -> DefaultParticleTypeMixin.init(false));
    public static final RegistrySupplier<DefaultParticleType> LEAF = register("leaf", () -> DefaultParticleTypeMixin.init(false));

    public static void init() {
        PARTICLE_TYPES.register();
    }

    static <T extends ParticleType<?>> RegistrySupplier<T> register(String name, Supplier<T> type) {
        return PARTICLE_TYPES.register(new Identifier(TerrariaMod.MOD_ID, name), type);
    }
}
