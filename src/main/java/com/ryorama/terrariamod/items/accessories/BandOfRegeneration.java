package com.ryorama.terrariamod.items.accessories;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class BandOfRegeneration extends TrinketItem {

    public BandOfRegeneration(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stiack, SlotReference slot, LivingEntity entity) {
        entity.heal(0.02f);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slotReference, LivingEntity entity, UUID uuid) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = super.getModifiers(stack, slotReference, entity, uuid);
        SlotAttributes.addSlotModifier(modifiers, "hand/ring", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        return  modifiers;
    }
}
