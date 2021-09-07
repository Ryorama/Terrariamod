package com.ryorama.terrariamod.items.accessories;

import com.google.common.collect.Multimap;
import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.items.AccessoryT;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;

import java.util.UUID;

public class Aglet extends TrinketItem {

    public boolean once = false;
    public boolean unequipOnce = false;

    public Aglet(Settings settings) {
        super(settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slotReference, LivingEntity entity, UUID uuid) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = super.getModifiers(stack, slotReference, entity, uuid);

        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "guidemod:movement_speed", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

        SlotAttributes.addSlotModifier(modifiers, "acc1/acc1", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        SlotAttributes.addSlotModifier(modifiers, "acc1/acc2", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        SlotAttributes.addSlotModifier(modifiers, "acc1/acc3", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        SlotAttributes.addSlotModifier(modifiers, "acc1/acc4", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        SlotAttributes.addSlotModifier(modifiers, "acc1/acc5", uuid, 1, EntityAttributeModifier.Operation.ADDITION);

        return modifiers;
    }
}

