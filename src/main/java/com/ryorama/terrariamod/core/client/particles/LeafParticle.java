package com.ryorama.terrariamod.core.client.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class LeafParticle extends AbstractSlowingParticle {

    private final SpriteProvider spriteProvider;

    public int getBrightness(float f) {
        return 240;
    }

    protected LeafParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, SpriteProvider spriteProvider) {
        super(clientWorld, d, e, f, g, h, i);
        this.spriteProvider = spriteProvider;
        this.scale(1f);
        this.setSpriteForAge(spriteProvider);
        this.setMaxAge(90);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();

        this.setVelocity(0.01f, -0.05f, 0.01f);
        this.setSpriteForAge(this.spriteProvider);
    }

    @Environment(value= EnvType.CLIENT)
    public static class Factory
            implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            LeafParticle shriekParticle = new LeafParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
            shriekParticle.setAlpha(1.0f);
            return shriekParticle;
        }
    }
}
