package com.ryorama.terrariamod.client.fx;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.registry.Registry;

public class DamageParticleData implements ParticleEffect {
	
	@Override
	public ParticleType<DamageParticleData> getType() {
		return null;
	}

	@Override
	public void write(PacketByteBuf buf) {
		
	}

	@Override
	public String asString() {
		return Registry.PARTICLE_TYPE.getId(getType()).getNamespace() + ":" + Registry.PARTICLE_TYPE.getId(getType()).getPath();
	}
	
	public static final Factory<DamageParticleData> FACTORY = new Factory<DamageParticleData>() {

		@Override
		public DamageParticleData read(ParticleType<DamageParticleData> type, StringReader reader)
				throws CommandSyntaxException {
			return new DamageParticleData();
		}

		@Override
		public DamageParticleData read(ParticleType<DamageParticleData> type, PacketByteBuf buf) {
			return new DamageParticleData();
		}
		
	};
}
