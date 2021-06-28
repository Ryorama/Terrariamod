package com.ryorama.terrariamod.client.fx;

import com.ryorama.terrariamod.TerrariaMod;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TerrariaModParticles {

	public static final ParticleType<DamageParticleData> DAMAGE = new DamageParticleType();
	
	public static void init() {
		Registry.register(Registry.PARTICLE_TYPE, new Identifier(TerrariaMod.MODID, "damage_indicator"), DAMAGE);
	}

	public static class FactoryHandler {
		public static void registerFactories() {
			ParticleFactoryRegistry.getInstance().register(TerrariaModParticles.DAMAGE, DamageParticleType.Factory::new);
			
		}
	}
}
