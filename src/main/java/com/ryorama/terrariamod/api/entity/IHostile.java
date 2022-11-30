package com.ryorama.terrariamod.api.entity;
import com.ryorama.terrariamod.world.WorldDataT;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;

public interface IHostile {
    default void dealDamage(Entity entity, DamageSource source, int damage) {
        int expModeDmgScale = WorldDataT.expert ? damage * 2 : damage;

        entity.damage(source, WorldDataT.master ? expModeDmgScale * 2 : expModeDmgScale);
    }

    default void dealDamage(Entity entity, DamageSource source, int damage, int diffucultyScale) {
        int expModeDmgScale = WorldDataT.expert ? damage + diffucultyScale: damage;

        entity.damage(source, WorldDataT.master ? expModeDmgScale + diffucultyScale : expModeDmgScale);
    }
}
