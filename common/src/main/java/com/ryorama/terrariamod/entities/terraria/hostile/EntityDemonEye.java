package com.ryorama.terrariamod.entities.terraria.hostile;

import com.ryorama.terrariamod.client.TAudio;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EntityDemonEye extends Mob implements GeoAnimatable {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.demon_eye.fly");

    public boolean bounce;

    public double velX, velY, velZ;
    public double oldVelX, oldVelY, oldVelZ;

    public static final EntityDataAccessor<Integer> typed_data = SynchedEntityData.defineId(EntityDemonEye.class, EntityDataSerializers.INT);

    public double speed = 2;

    public int damage = 18;

    public EntityDemonEye(EntityType<? extends EntityDemonEye> entityType, Level world) {
        super(entityType, world);
        this.getEntityData().define(EntityDemonEye.typed_data, 1);
        this.getEntityData().set(EntityDemonEye.typed_data, random.nextInt(6));
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(60);
        this.getAttribute(Attributes.ARMOR).setBaseValue(2);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isAlive()) {
            boolean night = this.level().isNight();

            double motionY = this.getDeltaMovement().y;
            double motionX = this.getDeltaMovement().x;
            double motionZ = this.getDeltaMovement().z;
            motionY = 0;
            this.setNoGravity(true);
            this.fallDistance = 0;
            this.setXRot(0);
            this.setYRot(0);
            this.yHeadRot = 0;
            Level world = this.level();
            Player target = null;
            double distance = 1000;

            for(int i = 0; i < world.players().size(); ++i) {
                double dist = world.players().get(i).position().distanceTo(this.position());
                if (dist < distance) {
                    distance = dist;
                    target = world.players().get(i);
                }

            }

            if (target != null) {
                this.lookAt(EntityAnchorArgument.Anchor.EYES, target.position());
            }

            if (world.getBlockState(new BlockPos(this.getBlockX(), this.getBlockY() - 1, this.getBlockZ())).getBlock().defaultBlockState() == Blocks.WATER.defaultBlockState()) {
                if (velY < 0) {
                    velY = 3;
                }
            }

            if (this.noPhysics == false) {
                if (world.getBlockState(new BlockPos(this.getBlockX(), (int) (this.getBlockY() - 0.5f), this.getBlockZ())).isSolid()) {
                    this.velY = 2.0f;
                }
            }

            if (bounce == true) {
                double absX = Math.abs(motionX);
                double absY = Math.abs(motionY);
                double absZ = Math.abs(motionZ);

                if (this.onGround() == false) {
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

                if (this.onGround() == true) {
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
                if (this.position().y() >= 120) {
                    this.remove(RemovalReason.DISCARDED);
                }
            } else if (target != null) {
                if (velX > -4 && this.position().x > target.position().x + target.getBbWidth()) {
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
                } else if (velX < 4 && this.position().x + 1 < target.position().x) {
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

                if (velZ > -4 && this.position().z > target.position().z + target.getBbWidth()) {
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
                } else if (velZ < 4f && this.position().z + 1 < target.position().z) {
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

                if (velY > -2.5 && this.position().y > target.position().y + target.getBbHeight()) {
                    velY -= 0.1f;
                    if (velY > 2.5) {
                        velY -= 0.05;
                    } else if (velY > 0f) {
                        velY -= 0.15;
                    }
                    if (velY < -2.5) {
                        velY = -2.5;
                    }
                } else if (velY < 2.5 && this.position().y + 1 < target.position().y) {
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

            this.setYRot((float)Math.toDegrees(Math.atan2(velZ, velX)) - 90);

            this.setDeltaMovement(motionX, motionY, motionZ);
        } else {
            this.setDeltaMovement(0, -0.5f, 0);
        }
    }

    @Override
    public void playerTouch(Player playerIn) {
        super.playerTouch(playerIn);

        if (this.isAlive()) {
            playerIn.hurt(this.level().damageSources().mobAttack(this), damage);
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
