package com.ryorama.terrariamod.entity;


import com.ryorama.terrariamod.world.WorldDataT;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;

public interface ITerrariaEntity {


    default void setMaxHealth(MobEntity entity, int health, int difficultyScale, boolean multiply) {
        if (multiply) {
            int expModeDmgScale = WorldDataT.expert ? health * difficultyScale: health;

            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(WorldDataT.master ? expModeDmgScale * difficultyScale : expModeDmgScale);

        } else {
            int expModeDmgScale = WorldDataT.expert ? health + difficultyScale: health;

            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(WorldDataT.master ? expModeDmgScale + difficultyScale : expModeDmgScale);
        }
    }

    default void setMaxHealth(LivingEntity entity, int health, int difficultyScale, boolean multiply) {
        if (multiply) {
            int expModeDmgScale = WorldDataT.expert ? health * difficultyScale: health;

            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(WorldDataT.master ? expModeDmgScale * difficultyScale : expModeDmgScale);

        } else {
            int expModeDmgScale = WorldDataT.expert ? health + difficultyScale: health;

            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(WorldDataT.master ? expModeDmgScale + difficultyScale : expModeDmgScale);
        }
    }
}
