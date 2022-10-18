package com.ryorama.terrariamod.utils;

import com.ryorama.terrariamod.TerrariaMod;
import io.netty.buffer.Unpooled;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class FlightHelper {

    private int timer, soundTimer, glideDelay, glideCloudDelay;
    private int flightTime = 40;

    public void wingFly(double speed, double glideSpeed, int priority, int flyTime, LivingEntity player) {
        if (player instanceof PlayerEntity user && user.isMainPlayer()) {
            if (!user.isSubmergedInWater()) {
                flightTime = flyTime;
                realFly(speed, glideSpeed, priority, true, user);
            }
        }
    }


    private void realFly(double speed, double glideSpeed, int priority, boolean wings, PlayerEntity player) {
        if (player.isOnGround()) {
            if (timer > 0) {
                timer = 0;
                soundTimer = 0;
                glideDelay = 0;
                glideCloudDelay = 0;
            }
        }

        if (wings && timer >= flightTime / 2) {
            if (InputHandler.isHoldingJump(player)) {
                //boolean getCloudEquipped = CloudBottleEquippedCheck.isEquipped(player);
                boolean getCloudEquipped = false;
                boolean trueCloudFinished = true;

                /*if (getCloudEquipped && ModComponents.MOVEMENT_ORDER.get(player).getCloudFinished()) {
                    if (glideCloudDelay >= 10) {
                        trueCloudFinished = true;
                    } else {
                        glideCloudDelay++;
                    }
                }*/


                if (((trueCloudFinished || !getCloudEquipped) && glideDelay >= 3) || (!trueCloudFinished && glideDelay >= 10)) {
                    double currentAccel = speed * (player.getVelocity().getY() < 0.3D ? 2.5D : 1.0D);
                    double motionY = player.getVelocity().getY();
                    double glideFallSpeed = InputHandler.isHoldingShift(player) ? -0.28D : -0.14D;
                    fly(player, Math.min(motionY + currentAccel, glideFallSpeed));
                    player.onLanding();

                    float speedSideways = (float) (player.isSneaking() ? glideSpeed * 0.5F : glideSpeed);
                    if (InputHandler.isHoldingForwards(player)) {
                        player.updateVelocity(1, new Vec3d(0, 0, speedSideways));
                    }
                    if (InputHandler.isHoldingBackwards(player)) {
                        player.updateVelocity(1, new Vec3d(0, 0, -speedSideways * 0.8F));
                    }
                    if (InputHandler.isHoldingLeft(player)) {
                        player.updateVelocity(1, new Vec3d(speedSideways, 0, 0));
                    }
                    if (InputHandler.isHoldingRight(player)) {
                        player.updateVelocity(1, new Vec3d(-speedSideways, 0, 0));
                    }
                } else {
                    glideDelay++;
                }
            } else {
                glideDelay = 0;
            }
        }

        if (timer < flightTime / 2) {
            if (InputHandler.isHoldingJump(player)) {
                double currentAccel = speed * (player.getVelocity().getY() < 0.3D ? 2.5D : 1.0D);
                double currentSpeedVertical = speed * (player.isSubmergedInWater() ? 0.4D : 1.0D);
                timer++;
                soundTimer++;

                double motionY = player.getVelocity().getY();
                if (InputHandler.isHoldingJump(player)) {
                    fly(player, Math.abs(Math.min(motionY + currentAccel, currentSpeedVertical)));
                }

                float speedSideways = (float) (player.isSneaking() ? glideSpeed * 0.5F : glideSpeed);
                if (InputHandler.isHoldingForwards(player)) {
                    player.updateVelocity(1, new Vec3d(0, 0, speedSideways));
                }
                if (InputHandler.isHoldingBackwards(player)) {
                    player.updateVelocity(1, new Vec3d(0, 0, -speedSideways * 0.8F));
                }
                if (InputHandler.isHoldingLeft(player)) {
                    player.updateVelocity(1, new Vec3d(speedSideways, 0, 0));
                }
                if (InputHandler.isHoldingRight(player)) {
                    player.updateVelocity(1, new Vec3d(-speedSideways, 0, 0));
                }

                player.onLanding();
            }
        }
    }


    private void fly(PlayerEntity player, double y) {
        Vec3d motion = player.getVelocity();
        player.setVelocity(motion.getX(), y, motion.getZ());
    }
}
