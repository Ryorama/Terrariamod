package com.ryorama.terrariamod.items.terraria.consumable.potions;

import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.client.TAudio;
import com.ryorama.terrariamod.items.impl.ItemT;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LesserHealingPotion extends ItemT {

    public LesserHealingPotion(Properties settings) {
        super(settings);
        this.isConsumable(true);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand hand) {
        if (!BuffsT.EntityHasBuff(playerEntity, BuffsT.POTION_SICKNESS)) {
            playerEntity.playSound(TAudio.DRINK, 1, 1);
            playerEntity.heal(50);
            BuffsT.AddBuffToEntity(playerEntity, 1000, BuffsT.POTION_SICKNESS);
            if (!playerEntity.isCreative()) {
                playerEntity.getItemInHand(hand).shrink(1);
            }
            return InteractionResultHolder.success(playerEntity.getItemInHand(hand));
        }

        return InteractionResultHolder.fail(playerEntity.getItemInHand(hand));
    }
}