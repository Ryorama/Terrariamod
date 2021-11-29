package com.ryorama.terrariamod.items.api;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class AccessoryT extends TrinketItem {

    public AccessoryT(Settings settings) {
        super(settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slotReference, LivingEntity entity, UUID uuid) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = super.getModifiers(stack, slotReference, entity, uuid);
        SlotAttributes.addSlotModifier(modifiers, "acc1/acc1", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        SlotAttributes.addSlotModifier(modifiers, "acc1/acc2", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        SlotAttributes.addSlotModifier(modifiers, "acc1/acc3", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        SlotAttributes.addSlotModifier(modifiers, "acc1/acc4", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        SlotAttributes.addSlotModifier(modifiers, "acc1/acc5", uuid, 1, EntityAttributeModifier.Operation.ADDITION);

        return  modifiers;
    }
}
