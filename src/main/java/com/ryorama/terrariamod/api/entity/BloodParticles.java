/*
package com.ryorama.terrariamod.api.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class BloodParticles extends Particle {

	protected BloodParticles(ClientWorld world, double x, double y, double z) {
		super(world, x, y, z);
		this.scale(1);
	}
	
	protected Sprite sprite;
	protected float scale;

	public void tick() {
	      this.move(this.x + this.random.nextInt(4), this.y - 0.5f, this.z + this.random.nextInt(4));
	}
	
	@Override
	public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
		Vec3d vec3d = camera.getPos();
	      float f = (float)(MathHelper.lerp((double)tickDelta, this.prevPosX, this.x) - vec3d.getX());
	      float g = (float)(MathHelper.lerp((double)tickDelta, this.prevPosY, this.y) - vec3d.getY());
	      float h = (float)(MathHelper.lerp((double)tickDelta, this.prevPosZ, this.z) - vec3d.getZ());
	      Quaternion quaternion2;
	      if (this.angle == 0.0F) {
	         quaternion2 = camera.getRotation();
	      } else {
	         quaternion2 = new Quaternion(camera.getRotation());
	         float i = MathHelper.lerp(tickDelta, this.prevAngle, this.angle);
	         quaternion2.hamiltonProduct(Vec3f.POSITIVE_Z.getRadialQuaternion(i));
	      }

	      Vec3f vec3f = new Vec3f(-1.0F, -1.0F, 0.0F);
	      vec3f.rotate(quaternion2);
	      Vec3f[] vec3fs = new Vec3f[]{new Vec3f(-1.0F, -1.0F, 0.0F), new Vec3f(-1.0F, 1.0F, 0.0F), new Vec3f(1.0F, 1.0F, 0.0F), new Vec3f(1.0F, -1.0F, 0.0F)};
	      float j = this.getSize(tickDelta);

	      for(int k = 0; k < 4; ++k) {
	         Vec3f vec3f2 = vec3fs[k];
	         vec3f2.rotate(quaternion2);
	         vec3f2.scale(j);
	         vec3f2.add(f, g, h);
	      }

	      float l = this.getMinU();
	      float m = this.getMaxU();
	      float n = this.getMinV();
	      float o = this.getMaxV();
	      int p = this.getBrightness(tickDelta);
	      vertexConsumer.vertex((double)vec3fs[0].getX(), (double)vec3fs[0].getY(), (double)vec3fs[0].getZ()).texture(m, o).color(this.red, this.green, this.blue, this.alpha).light(p).next();
	      vertexConsumer.vertex((double)vec3fs[1].getX(), (double)vec3fs[1].getY(), (double)vec3fs[1].getZ()).texture(m, n).color(this.red, this.green, this.blue, this.alpha).light(p).next();
	      vertexConsumer.vertex((double)vec3fs[2].getX(), (double)vec3fs[2].getY(), (double)vec3fs[2].getZ()).texture(l, n).color(this.red, this.green, this.blue, this.alpha).light(p).next();
	      vertexConsumer.vertex((double)vec3fs[3].getX(), (double)vec3fs[3].getY(), (double)vec3fs[3].getZ()).texture(l, o).color(this.red, this.green, this.blue, this.alpha).light(p).next();
	}
	
	public float getSize(float tickDelta) {
		return this.scale;
	}

	public Particle scale(float scale) {
		 this.scale *= scale;
		 return super.scale(scale);
	}
	
	protected float getMinU() {
		return this.sprite.getMinU();
	}

	protected float getMaxU() {
		return this.sprite.getMaxU();
	}

	protected float getMinV() {
		return this.sprite.getMinV();
	}

	protected float getMaxV() {
		return this.sprite.getMaxV();
	}
	
	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
	} 
	
	protected void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void setSprite(SpriteProvider spriteProvider) {
		this.setSprite(spriteProvider.getSprite(this.random));
	}
	
	 @Environment(EnvType.CLIENT)
	 public static class BloodParticleFactory implements ParticleFactory<DefaultParticleType> {
	      private final SpriteProvider spriteProvider;

	      public BloodParticleFactory(SpriteProvider spriteProvider) {
	         this.spriteProvider = spriteProvider;
	      }

	      public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
	         BloodParticles bloodParticles = new BloodParticles(clientWorld, d, e, f);
	         bloodParticles.setSprite(this.spriteProvider);
	         return bloodParticles;
	      }
	 }
}
*/