package com.ryorama.terrariamod.api.entity;
import com.ryorama.terrariamod.world.WorldDataT;

import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;

public interface IHostile {

    default void dealDamage(Entity entity, DamageSource source, int damage) {
        int expModeDmgScale = WorldDataT.expert ? damage * 2 : damage;

        entity.damage(source, WorldDataT.master ? expModeDmgScale * 2 : expModeDmgScale);
    }

    default void dealDamage(Entity entity, DamageSource source, int damage, int difficultyScale) {
        int expModeDmgScale = WorldDataT.expert ? damage + difficultyScale: damage;

        entity.damage(source, WorldDataT.master ? expModeDmgScale + difficultyScale : expModeDmgScale);
    }
}
