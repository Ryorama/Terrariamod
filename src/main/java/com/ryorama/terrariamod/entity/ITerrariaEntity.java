package com.ryorama.terrariamod.entity;


import com.ryorama.terrariamod.world.WorldDataT;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;

public interface ITerrariaEntity {

    default void setMaxHealth(LivingEntity entity, float normal) {
        entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(normal);
    }

    default void setMaxHealth(LivingEntity entity, float normal, float expert, float master) {
        if (!WorldDataT.expert && !WorldDataT.master) {
            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(normal);
        } else if (WorldDataT.expert && !WorldDataT.master) {
            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(expert);
        } else if (!WorldDataT.expert && WorldDataT.master) {
            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(master);
        } else {
            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(normal);
        }
    }
}
