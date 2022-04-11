package com.ryorama.terrariamod.items.accessories;

import com.ryorama.terrariamod.items.api.AccessoryT;
import dev.emi.trinkets.api.SlotReference;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public class MagmaStone extends AccessoryT {

    public boolean once = false;
    public boolean unequipOnce = false;

    public boolean isEquiped = false;

    public MagmaStone(Settings settings) {
        super(settings);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        isEquiped = true;
        AttackEntityCallback.EVENT.register((player, world, hand, entity1, hitResult) -> {

            if (isEquiped) {
                entity1.setOnFireFor(5);
            }

            return ActionResult.PASS;
        });
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        isEquiped = false;
    }
}
