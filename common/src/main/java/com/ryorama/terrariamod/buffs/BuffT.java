package com.ryorama.terrariamod.buffs;

import com.ryorama.terrariamod.client.ui.UIRenderer;
import net.minecraft.util.Identifier;

public class BuffT {

    public String name;
    public boolean isHarmful;
    public static Identifier icon;

    public static boolean isActive;

    public static float duration = 0;

    public BuffT(String name, boolean isHarmful, Identifier icon) {
        this.name = name;
        this.isHarmful = isHarmful;
        this.icon = icon;
    }

    public void tick() {
        if (duration > 0) {
            System.out.println(duration);
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

    public static void renderIcon() {
        if (isActive) {
            UIRenderer.renderOverlay(icon, 50, 16, 16, 17, 27, -90);
        }
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
}
