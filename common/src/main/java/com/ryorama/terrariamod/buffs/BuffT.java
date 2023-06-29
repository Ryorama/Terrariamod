package com.ryorama.terrariamod.buffs;

import com.ryorama.terrariamod.client.ui.UIRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BuffT {

    public String name;
    public boolean isHarmful;
    public static Identifier icon;

    public static boolean isActive;

    public static float duration = 0;

    public static List<Identifier> buffIcons = new ArrayList<>();

    public BuffT(Identifier icon) {
        setIcon(icon);
    }

    public void tick() {
        if (duration > 0) {
            isActive = true;
            int ticks;

            for (ticks = 0; ticks <= 20; ticks++) {
                if (ticks == 20) {
                    duration -= 1;
                }
            }

            ticks = 0;
        } else {
            isActive = false;
        }
    }

    public static void renderIcon(LivingEntity entity) {
        callRenderUIFabric(entity);
        callRenderUIQuilt(entity);
    }

    public void setDuration(float time) {
        duration = time;
    }

    public float getDuration() {
        return duration;
    }

    public Identifier getIcon() {
        return icon;
    }

    public void setIcon(Identifier icon) {
        buffIcons.add(icon);
    }

    public boolean IsActive() {
        return isActive;
    }

    public static void callRenderUIFabric(LivingEntity entity) {
        String packageName = "com.ryorama.terrariamod.fabric.client.ui";
        String className = "TerrariaUIRenderer";

        Class<?>[] formalParameters = { LivingEntity.class };
        Object[] effectiveParameters = new Object[] { entity };

        try {
            Class<?> clazz = Class.forName(packageName + "." + className);

            Method method = clazz.getMethod("newRenderTerrariaEffects", formalParameters);
            method.invoke(null, effectiveParameters);
        } catch (Exception e) {
        }
    }

    public static void callRenderUIQuilt(LivingEntity entity) {
        String packageName = "com.ryorama.terrariamod.quilt.client.ui";
        String className = "TerrariaUIRenderer";

        Class<?>[] formalParameters = { LivingEntity.class };
        Object[] effectiveParameters = new Object[] { entity };

        try {
            Class<?> clazz = Class.forName(packageName + "." + className);

            Method method = clazz.getMethod("newRenderTerrariaEffects", formalParameters);
            method.invoke(null, effectiveParameters);
        } catch (Exception e) {
        }
    }
}
