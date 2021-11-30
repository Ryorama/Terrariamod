package com.ryorama.terrariamod.core.client.particles;

import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public interface ParticleManagerHooks {
	/** Get the sprite atlas texture used by {@link net.minecraft.client.particle.ParticleManager}. */
	SpriteAtlasTexture fabric_getSpriteAtlasTexture();

	/** Register a custom {@link ParticleFactory} for the given {@link ParticleType}. */
	<T extends ParticleEffect> void fabric_registerCustomFactory(ParticleType<T> pt, ParticleFactory<T> pf);
}
