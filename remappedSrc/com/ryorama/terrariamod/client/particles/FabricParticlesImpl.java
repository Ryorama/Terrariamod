package com.ryorama.terrariamod.core.client.particles;

import java.util.IdentityHashMap;
import java.util.Map;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public class FabricParticlesImpl implements FabricParticles {

	public final Map<ParticleType<?>, ParticleFactory<?>> factoriesAwaitingRegistry = new IdentityHashMap<>();

	public <T extends ParticleEffect> void registerParticleFactory(ParticleType<T> type, ParticleFactory<T> factory) {
		ParticleManagerHooks manager = (ParticleManagerHooks)MinecraftClient.getInstance().particleManager;
		if(manager != null) manager.fabric_registerCustomFactory(type, factory);
		else factoriesAwaitingRegistry.put(type, factory);
	}
}