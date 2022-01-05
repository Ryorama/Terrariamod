package com.ryorama.terrariamod.client.core.client;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.client.fx.particles.LeafParticle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ParticleRegistry {

    public static DefaultParticleType FOREST_LEAF = FabricParticleTypes.simple();

    public static void init() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(TerrariaMod.MODID, "leaf"), FOREST_LEAF);
    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier(TerrariaMod.MODID, "particle/leaf"));
        }));

        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.FOREST_LEAF, LeafParticle.Factory::new);
    }
}
