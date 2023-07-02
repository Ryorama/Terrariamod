package com.ryorama.terrariamod.client.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class Leaf extends SpriteBillboardParticle {
    protected Leaf(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);
        this.maxAge = 20;
        this.collidesWithWorld = false;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    public float getSize(float tickDelta) {
        return 0.3F;
    }

    public void tick() {
        if (this.age++ >= this.maxAge) {
            this.markDead();
        } else {
            velocityY -= 0.5f;
        }
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprite;

        public Factory(SpriteProvider sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            Leaf flameParticle = new Leaf(world, x, y, z);
            flameParticle.setSprite(this.sprite);
            flameParticle.setColor(1.0F, 1.0F, 1.0F);
            return flameParticle;
        }
    }
}