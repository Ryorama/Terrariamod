package com.ryorama.terrariamod.buffs.terraria.harmful;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffT;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class OnFireDebuff extends BuffT {
    public OnFireDebuff() {
        super(new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/buffs/on_fire.png"));
    }

    @Override
    public void tick(LivingEntity entity) {
        super.tick(entity);

        if (IsActive()) {
            int t = 0;

            for (t = 0; t <= 20; t++) {
                if (t == 20) {
                    entity.hurt(entity.damageSources().inFire(), 2);
                }
            }
        }
    }
}
