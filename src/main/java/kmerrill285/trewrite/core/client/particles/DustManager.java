package kmerrill285.trewrite.core.client.particles;

import kmerrill285.trewrite.Trewrite;
import kmerrill285.trewrite.core.client.ParticleRegistry;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureAtlasSpriteStitcher;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import javax.annotation.Resource;

public abstract class DustManager extends SpriteTexturedParticle {

    public static String particleLocation;
    public static int size;
    public Vec3d particleGravity3d;
    public World worldIn;

    public DustManager(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.worldIn = worldIn;
        this.maxAge = 100;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.particleGravity != 0.0F) {
            this.particleGravity3d = new Vec3d(0.0D, this.particleGravity, 0.0D);
            this.particleGravity = 0.0F;
        }
        if (this.particleGravity3d != null)
            accelerate(this.particleGravity3d.scale(-0.03999999910593033D));
    }

    public Vec3d setGravity3d(double x, double y, double z) {
        particleGravity3d = new Vec3d(x, y, z);
        return new Vec3d(x, y, z);
    }

    public World getWorld() {
        return worldIn;
    }

    public void accelerate(Vec3d v) {
        this.motionX += v.x;
        this.motionY += v.y;
        this.motionZ += v.z;

        this.move(motionX, motionY, motionZ);
    }

    public static int setWidthHeight(int textureSize) {
        size = textureSize;
        return textureSize;
    }

    public String setTextureName(String name) {
        particleLocation = name;
        return name;
    }

    public void addWindToGravity(float scale) {
        Vec3d wind = WorldStateHolder.get(worldIn).toWindVec3D(0).scale(-scale);
        if (particleGravity3d == null) {
            this.particleGravity3d = wind;
        } else {
            this.particleGravity3d = this.particleGravity3d.add(wind);
        }
    }
}
