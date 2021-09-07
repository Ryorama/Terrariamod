package com.ryorama.terrariamod.items.accessories;

import com.google.common.collect.Multimap;
import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.items.AccessoryT;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.StatType;
import net.minecraft.stat.Stats;

import java.util.UUID;

public class BandOfStarpower extends AccessoryT {

    public boolean once = false;
    public boolean unequipOnce = false;

    public BandOfStarpower(Settings settings) {
        super(settings);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (MinecraftClient.getInstance().player != null && !once) {
            MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA)) + 20);
            System.out.println(MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA)));
            once = true;
            unequipOnce = false;
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (MinecraftClient.getInstance().player != null && !unequipOnce) {
            MinecraftClient.getInstance().player.getStatHandler().setStat(MinecraftClient.getInstance().player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA), MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MAX_MANA)) - 20);
            unequipOnce = true;
            once = false;
        }
    }
}
