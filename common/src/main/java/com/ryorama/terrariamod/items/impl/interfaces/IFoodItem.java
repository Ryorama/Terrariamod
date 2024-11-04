package com.ryorama.terrariamod.items.impl.interfaces;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffT;
import com.ryorama.terrariamod.buffs.BuffsT;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;

public interface IFoodItem {

    default void addBuffOnEaten(PlayerEntity entity, BuffT buff, int duration) {
        BuffsT.AddBuffToEntity(entity, duration, buff);
    }

    default void handlePlayerHunger(PlayerEntity playerEntity, int hungerValue, int saturationValue) {
        if (!TerrariaMod.CONFIG.disableHunger) {
            HungerManager hungerManager = playerEntity.getHungerManager();
            hungerManager.add(hungerValue, saturationValue);
        }
    }
}
