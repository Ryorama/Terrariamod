package com.ryorama.terrariamod.items.accessories;

import com.google.common.collect.Multimap;
import com.ryorama.terrariamod.items.AccessoryT;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class BandOfRegeneration extends AccessoryT {

    public BandOfRegeneration(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stiack, SlotReference slot, LivingEntity entity) {
        entity.heal(0.02f);
    }
}
