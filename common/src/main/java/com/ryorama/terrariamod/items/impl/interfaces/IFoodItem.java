package com.ryorama.terrariamod.items.impl.interfaces;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffT;
import com.ryorama.terrariamod.buffs.BuffsT;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;

public interface IFoodItem {

    default void addBuffOnEaten(Player entity, BuffT buff, int duration) {
        BuffsT.AddBuffToEntity(entity, duration, buff);
    }

    default void handlePlayerHunger(Player playerEntity, int hungerValue, int saturationValue) {
        if (!TerrariaMod.CONFIG.disableHunger) {
            FoodData hungerManager = playerEntity.getFoodData();
            hungerManager.eat(hungerValue, saturationValue);
        }
    }
}
