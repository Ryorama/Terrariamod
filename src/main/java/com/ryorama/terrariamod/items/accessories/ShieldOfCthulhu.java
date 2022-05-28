package com.ryorama.terrariamod.items.accessories;


import com.ryorama.terrariamod.items.api.AccessoryT;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class ShieldOfCthulhu extends AccessoryT {

    public boolean isEquiped = false;

    public ShieldOfCthulhu(Settings settings) {
        super(settings);

    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        isEquiped = true;
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        isEquiped = false;
    }
}

