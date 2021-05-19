package com.ryorama.terrariamod.client.fx;

import com.mojang.serialization.Codec;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleType;

public class DamageParticleType extends ParticleType<DamageParticleData> {

	protected DamageParticleType() {
		super(false, DamageParticleData.FACTORY);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Codec<DamageParticleData> getCodec() {
		return (Codec<DamageParticleData>) Codec.EMPTY;
	}
	
	public static class Factory implements ParticleFactory<DamageParticleData> {
		private final SpriteProvider sprite;

		public Factory(SpriteProvider sprite) {
			this.sprite = sprite;
		}

		@Override
		public Particle createParticle(DamageParticleData data, ClientWorld world, double x, double y, double z, double mx, double my, double mz) {
			FXDamageIndicator ret = new FXDamageIndicator(world, x, y, z);
			ret.setSprite(sprite);
			return ret;
		}
	}

}
