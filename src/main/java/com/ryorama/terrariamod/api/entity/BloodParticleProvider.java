package com.ryorama.terrariamod.api.entity;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.registry.Registry;

public class BloodParticleProvider implements ParticleEffect, ParticleFactory<BloodParticleProvider> {

	@Override
	public Particle createParticle(BloodParticleProvider parameters, ClientWorld world, double x, double y, double z,
			double velocityX, double velocityY, double velocityZ) {
		return new BloodParticles(world, velocityX, velocityY, velocityZ);
	}

	@Override
	public ParticleType<?> getType() {
		return this.getType();
	}

	@Override
	public void write(PacketByteBuf buf) {		
	}

	@Override
	public String asString() {
		return Registry.PARTICLE_TYPE.getId(this.getType()).toString();
	}
}
