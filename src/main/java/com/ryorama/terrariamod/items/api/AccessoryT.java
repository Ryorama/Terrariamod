package com.ryorama.terrariamod.items.api;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class AccessoryT extends TrinketItem {

    public boolean isExpert;

    public AccessoryT(Settings settings) {
        super(settings);
    }

    public AccessoryT isExpert(boolean isExpert) {
        this.isExpert = isExpert;
        return this;
    }

    @Override
    public void appendTooltip(ItemStack stack, World worldIn, List<Text> tooltip, TooltipContext context) {
        if (isExpert) {
            tooltip.add(new TranslatableText("Expert").formatted(Formatting.LIGHT_PURPLE));
        }
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
