package com.ryorama.terrariamod.buffs.terraria.helpful;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffT;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

public class RegenerationBuff extends BuffT {
    public RegenerationBuff() {
        super(new Identifier(TerrariaMod.MOD_ID, "textures/ui/buffs/regeneration.png"));
    }

    @Override
    public void tick(LivingEntity entity) {
        super.tick(entity);

        if (IsActive()) {
            int t = 0;

            for (t = 0; t <= 20; t++) {
                if (t == 20) {
                    entity.heal(1);
                }
            }
        }
    }
}
