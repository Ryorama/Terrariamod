package com.ryorama.terrariamod.callbacks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public interface PlayerEquipArmorCallback {
    Event<PlayerEquipArmorCallback> EVENT = EventFactory.createArrayBacked(PlayerEquipArmorCallback.class,
            (listeners) -> (slot, stack) -> {
                for (PlayerEquipArmorCallback listener : listeners) {
                    ActionResult result = listener.call(slot, stack);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult call(EquipmentSlot slot, ItemStack stack);
}
