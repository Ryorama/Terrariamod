package com.ryorama.terrariamod.items.api;

import com.ryorama.terrariamod.utils.FlightHelper;
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
    public FlightHelper flightHelper = new FlightHelper();

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
        flightTimeCache = flightTime;
        speedCache = speed;

        flightHelper.wingFly(speedCache, glideSpeed, priority, flightTimeCache, entity);
    }
}
