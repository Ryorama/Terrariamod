package kmerrill285.trewrite.core.client.particles;

import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class LeafParticle extends DustManager {

    public LeafParticle(World worldIn, Vec3d pos) {
        super(worldIn, pos.getX(), pos.getY(), pos.getZ());
        this.maxAge = 100;
        this.canCollide = false;
        this.setGravity3d(0.0D, 0.05000000074505806D, 0.0D);
        this.addWindToGravity(0.075f);
        this.setSize(5, 5);
    }

    /*
    @Override
    public void tick() {
        super.tick();
        WorldStateHolder worldStateHolder = WorldStateHolder.get(world);

        if (this.maxAge-- <= 0) {
            this.setExpired();
        } else {
            if (!onGround) {
                if (worldStateHolder.windDirection == WorldStateHolder.WIND_DIR.NONE) {
                    if (rand.nextInt(2) == 0) {
                        this.motionX = maxAge / 99;
                    } else {
                        this.motionZ = maxAge / 99;
                    }
                } else if (worldStateHolder.windDirection == WorldStateHolder.WIND_DIR.NORTH) {
                    this.motionX = 0.03f;
                    this.motionZ = 0;
                } else if (worldStateHolder.windDirection == WorldStateHolder.WIND_DIR.EAST) {
                    this.motionX = 0;
                    this.motionZ = 0.03f;
                } else if (worldStateHolder.windDirection == WorldStateHolder.WIND_DIR.SOUTH) {
                    this.motionX = -0.03f;
                    this.motionZ = 0;
                } else if (worldStateHolder.windDirection == WorldStateHolder.WIND_DIR.WEST) {
                    this.motionX = 0;
                    this.motionZ = -0.03f;
                }

                this.motionY = -0.1f;
                this.move(motionX, motionY, motionZ);
            }
        }
    }
    */

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite) {
            this.spriteSet = sprite;
        }

        @Nullable
        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            LeafParticle particle = new LeafParticle(worldIn, new Vec3d(x, y, z));
            particle.setColor(1, 1, 1);
            particle.selectSpriteRandomly(spriteSet);
            return particle;
        }
    }
}
