package com.ryorama.terrariamod.items.api;

import com.ryorama.terrariamod.utils.InputHandler;
import dev.emi.trinkets.api.SlotReference;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

import java.util.Random;

public class WingsItemT extends AccessoryT {

    private final double speed;
    private final double glideSpeed;
    private final int flightTime;
    private final int priority;
    private boolean isEquipped = false;
    private int timer;
    private int soundTimer;
    private double speedSide = 0.03D;
    private double sprintSpeed = 1.0D;
    private int wingTime = 40;
    private Random random = new Random();
    private int glideDelay;
    private int glideCloudDelay;
    private boolean wingsFinished;

    public WingsItemT(Settings settings, double speed, double glideSpeed, int flightTime, int priority) {
        super(settings);
        this.speed = speed;
        this.glideSpeed = glideSpeed;
        this.flightTime = flightTime;
        this.priority = priority;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        int flightTimeCache;
        double speedCache;

        if (isEquipped) {
            flightTimeCache = flightTime + 35;
        } else {
            flightTimeCache = flightTime;
        }
    }

    public void wingFly(double speed, double glideSpeed, int priority, int flyTime, LivingEntity player) {
        if (player instanceof PlayerEntity user && user.isMainPlayer()) {
            if (!user.isSubmergedInWater()) {
                wingTime = flyTime;
                realFly(speed, glideSpeed, priority, true, user);
            }
        }
    }

    private void realFly(double speed, double glideSpeed, int priority, boolean wings, PlayerEntity player) {
        if (player.isOnGround())
        {
            if (timer > 0)
            {
                timer = 0;
                soundTimer = 0;
                glideDelay = 0;
                glideCloudDelay = 0;
            }
            if (wings) {
                wingsFinished = false;
            }
        }

        if (wings && timer >= wingTime / 2) {
            wingsFinished = true;
            if (InputHandler.isHoldingJump(player)) {
                if (glideDelay >= 3) {
                    Vec3d playerPos = player.getPos();
                    double currentAccel = speed * (player.getTrackedPosition().getDeltaY(playerPos) < 0.3D ? 2.5D : 1.0D);
                    double motionY = player.getTrackedPosition().getDeltaY(playerPos);
                    double glideFallSpeed = InputHandler.isHoldingShift(player) ? -0.28D : -0.14D;
                    fly(player, Math.min(motionY + currentAccel, glideFallSpeed));
                    player.fallDistance = 0;

                    float speedSideways = (float) (player.isSneaking() ? glideSpeed * 0.5F : glideSpeed);
                    if (InputHandler.isHoldingForwards(player)) {
                        player.applyMovementInput(new Vec3d(0, 0, speedSideways), 1);
                    }
                    if (InputHandler.isHoldingBackwards(player)) {
                        player.applyMovementInput(new Vec3d(0, 0, -speedSideways * 0.8F), 1);
                    }
                    if (InputHandler.isHoldingLeft(player)) {
                        player.applyMovementInput(new Vec3d(speedSideways, 0, 0), 1);
                    }
                    if (InputHandler.isHoldingRight(player)) {
                        player.applyMovementInput(new Vec3d(-speedSideways, 0, 0), 1);
                    }
                } else {
                    glideDelay++;
                }
            } else {
                glideDelay = 0;
            }
        }

        if (timer < wingTime / 2) {
            if (InputHandler.isHoldingJump(player)) {
                Vec3d playerPos = player.getPos();
                double currentAccel = speed * (player.getTrackedPosition().getDeltaY(playerPos) < 0.3D ? 2.5D : 1.0D);
                double currentSpeedVertical = speed * (player.isSubmergedInWater() ? 0.4D : 1.0D);
                timer++;
                soundTimer++;

                double motionY = player.getTrackedPosition().getDeltaY(playerPos);
                if (InputHandler.isHoldingJump(player)) {
                    fly(player, Math.abs(Math.min(motionY + currentAccel, currentSpeedVertical)));
                }

                float speedSideways = (float) (player.isSneaking() ? glideSpeed * 0.5F : glideSpeed);
                if (InputHandler.isHoldingForwards(player)) {
                    player.setVelocity(new Vec3d(0, 0, speedSideways));
                }
                if (InputHandler.isHoldingBackwards(player)) {
                    player.setVelocity( new Vec3d(0, 0, -speedSideways * 0.8F));
                }
                if (InputHandler.isHoldingLeft(player)) {
                    player.setVelocity(new Vec3d(speedSideways, 0, 0));
                }
                if (InputHandler.isHoldingRight(player)) {
                    player.setVelocity(new Vec3d(-speedSideways, 0, 0));
                }

                player.fallDistance = 0;
            }
        }
    }

    private void fly(PlayerEntity player, double y) {
        Vec3d playerPos = player.getPos();
        Vec3d motion = new Vec3d(player.getTrackedPosition().getDeltaX(playerPos), player.getTrackedPosition().getDeltaY(playerPos), player.getTrackedPosition().getDeltaZ(playerPos));
        player.setVelocity(motion.getX(), y, motion.getZ());
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        isEquipped = true;
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        isEquipped = false;
    }

}
