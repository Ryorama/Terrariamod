package com.ryorama.terrariamod.items.accessories;


import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.items.api.AccessoryT;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;

public class RoyalGel extends AccessoryT {

    public boolean isEquiped = false;

    public RoyalGel(Settings settings) {
        super(settings);

    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        isEquiped = true;

        if (entity instanceof PlayerEntity) {
            if (entity.getWorld().isClient()) {
                ClientPlayerEntity player = MinecraftClient.getInstance().player;

                player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.ROYAL_GEL_EQ), 1);
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        isEquiped = false;

        if (entity instanceof PlayerEntity) {
            if (entity.getWorld().isClient()) {
                ClientPlayerEntity player = MinecraftClient.getInstance().player;

                player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.ROYAL_GEL_EQ), 0);
            }
        }
    }
}

