package com.ryorama.terrariamod.items.accessories;

import com.ryorama.terrariamod.items.api.AccessoryT;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class BandOfRegeneration extends AccessoryT {

    public BandOfRegeneration(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity.heal(0.02f);
    }
}
