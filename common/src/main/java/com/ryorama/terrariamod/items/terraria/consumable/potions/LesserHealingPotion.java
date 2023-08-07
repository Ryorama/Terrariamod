package com.ryorama.terrariamod.items.terraria.consumable.potions;

import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.client.TAudio;
import com.ryorama.terrariamod.items.impl.ItemT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LesserHealingPotion extends ItemT {

    public LesserHealingPotion(Settings settings) {
        super(settings.maxCount(9999));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (!BuffsT.EntityHasBuff(playerEntity, BuffsT.POTION_SICKNESS)) {
            playerEntity.playSound(TAudio.DRINK, 1, 1);
            playerEntity.heal(50);
            BuffsT.AddBuffToEntity(playerEntity, 1000, BuffsT.POTION_SICKNESS);
            return TypedActionResult.success(playerEntity.getStackInHand(hand));
        }

        return TypedActionResult.fail(playerEntity.getStackInHand(hand));
    }
}