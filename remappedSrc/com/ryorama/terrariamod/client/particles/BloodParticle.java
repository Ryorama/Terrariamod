package com.ryorama.terrariamod.core.client.particles;

import com.ryorama.terrariamod.TerrariaMod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;

@Environment(EnvType.CLIENT)
public class BloodParticle extends SpriteBillboardParticle {
		
	public BloodParticle(ClientWorld w, double x, double y, double z, double vx, double vy, double vz) {
		super(w, x, y, z, vx, vy, vz);
	}

	public ParticleTextureSheet getType() { return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE; }
}