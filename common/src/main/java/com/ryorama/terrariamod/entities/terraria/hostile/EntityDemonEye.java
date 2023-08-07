package com.ryorama.terrariamod.entities.terraria.hostile;

import com.ryorama.terrariamod.client.TAudio;
import net.minecraft.block.Blocks;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EntityDemonEye extends MobEntity implements GeoAnimatable {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.demon_eye.fly");

    public boolean bounce;

    public double velX, velY, velZ;
    public double oldVelX, oldVelY, oldVelZ;

    public static final TrackedData<Integer> typed_data = DataTracker.registerData(EntityDemonEye.class, TrackedDataHandlerRegistry.INTEGER);

    public double speed = 2;

    public int damage = 18;

    public EntityDemonEye(EntityType<? extends EntityDemonEye> entityType, World world) {
        super(entityType, world);
        this.getDataTracker().startTracking(EntityDemonEye.typed_data, 1);
        this.getDataTracker().set(EntityDemonEye.typed_data, random.nextInt(6));
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(60);
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(2);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isAlive()) {
            boolean night = this.getEntityWorld().isNight();

            double motionY = this.getVelocity().y;
            double motionX = this.getVelocity().x;
            double motionZ = this.getVelocity().z;
            motionY = 0;
            this.setNoGravity(true);
            this.fallDistance = 0;
            this.setPitch(0);
            this.setYaw(0);
            this.headYaw = 0;
            World world = this.getEntityWorld();
            PlayerEntity target = null;
            double distance = 1000;

            for(int i = 0; i < this.getEntityWorld().getPlayers().size(); ++i) {
                double dist = this.getEntityWorld().getPlayers().get(i).getPos().distanceTo(this.getPos());
                if (dist < distance) {
                    distance = dist;
                    target = this.getEntityWorld().getPlayers().get(i);
                }

            }

            if (target != null) {
                this.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, target.getPos());
            }

            if (world.getBlockState(new BlockPos(this.getBlockX(), this.getBlockY() - 1, this.getBlockZ())).getBlock().getDefaultState() == Blocks.WATER.getDefaultState()) {
                if (velY < 0) {
                    velY = 3;
                }
            }

            if (this.noClip == false) {
                if (world.getBlockState(new BlockPos(this.getBlockX(), (int) (this.getBlockY() - 0.5f), this.getBlockZ())).isSolid()) {
                    this.velY = 2.0f;
                }
            }

            if (bounce == true) {
                double absX = Math.abs(motionX);
                double absY = Math.abs(motionY);
                double absZ = Math.abs(motionZ);

                if (this.isOnGround() == false) {
                    //			if (absX > absY) {
                    velX = oldVelX * -0.5;
                    if (velX > 0 && velX < 2) {
                        velX = 2;
                    }
                    if (velX < 0 && velX > -2) {
                        velX = -2;
                    }
                    //			}

                    //			if (absZ > absY) {
                    velZ = oldVelZ * -0.5;
                    if (velZ > 0 && velZ < 2) {
                        velZ = 2;
                    }
                    if (velZ < 0 && velZ > -2) {
                        velZ = -2;
                    }
                    //			}
                }

                if (this.isOnGround() == true) {
                    velY = oldVelY * -0.5;
                    if (velY > 0 && velY < 1) {
                        velY = 1;
                    }
                    if (velY < 0 && velY > -1) {
                        velY = -1;
                    }
                }

            }

            if (night == false) {
                velY = 2f;
                if (this.getPos().getY() >= 120) {
                    this.remove(RemovalReason.DISCARDED);
                }
            } else if (target != null) {
                if (velX > -4 && this.getPos().x > target.getPos().x + target.getWidth()) {
                    velX -= 0.08;
                    if (velX > 4) {
                        velX -= 0.04;
                    }
                    else if (velX > 0) {
                        velX -= 0.2;
                    }
                    if (velX < -4) {
                        velX = -4;
                    }
                } else if (velX < 4 && this.getPos().x + 1 < target.getPos().x) {
                    velX += 0.08;
                    if (velX < -4) {
                        velX += 0.04;
                    }
                    else if (velX < 0) {
                        velX += 0.2;
                    }
                    if (velX > 4) {
                        velX = 4;
                    }
                }

                if (velZ > -4 && this.getPos().z > target.getPos().z + target.getWidth()) {
                    velZ -= 0.08;
                    if (velZ > 4) {
                        velZ -= 0.04;
                    }
                    else if (velZ > 0f) {
                        velZ -= 0.2;
                    }
                    if (velZ < -4) {
                        velZ = -4;
                    }
                } else if (velZ < 4f && this.getPos().z + 1 < target.getPos().z) {
                    velZ += 0.08f;
                    if (velZ < -4) {
                        velZ += 0.04;
                    }
                    else if (velZ < 0f) {
                        velZ += 0.2;
                    }
                    if (velZ > 4) {
                        velZ = 4;
                    }
                }

                if (velY > -2.5 && this.getPos().y > target.getPos().y + target.getHeight()) {
                    velY -= 0.1f;
                    if (velY > 2.5) {
                        velY -= 0.05;
                    } else if (velY > 0f) {
                        velY -= 0.15;
                    }
                    if (velY < -2.5) {
                        velY = -2.5;
                    }
                } else if (velY < 2.5 && this.getPos().y + 1 < target.getPos().y) {
                    velY += 0.1f;
                    if (velY < -2.5) {
                        velY += 0.05;
                    }
                    else if (velY < 0) {
                        velY += 0.15;
                    }
                    if (velY > 2.5) {
                        velY = 2.5;
                    }
                }


            }


            bounce = false;
            oldVelX = velX + 0;
            oldVelY = velY + 0;
            oldVelZ = velZ + 0;
            motionX = velX * 0.075f;
            motionY = velY * 0.075f;
            motionZ = velZ * 0.075f;

            this.setYaw((float)Math.toDegrees(Math.atan2(velZ, velX)) - 90);

            this.setVelocity(motionX, motionY, motionZ);
        } else {
            this.setVelocity(0, -0.5f, 0);
        }
    }

    @Override
    public void onPlayerCollision(PlayerEntity playerIn) {
        super.onPlayerCollision(playerIn);

        if (this.isAlive()) {
            playerIn.damage(this.getEntityWorld().getDamageSources().mobAttack(this), damage);
        }
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object object) {
        return 0;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return TAudio.NPC_HIT1;
    }
}
