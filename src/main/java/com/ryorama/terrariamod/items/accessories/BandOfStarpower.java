package com.ryorama.terrariamod.items.accessories;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.items.api.AccessoryT;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;

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
