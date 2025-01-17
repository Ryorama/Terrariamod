package com.ryorama.terrariamod.items.terraria.consumable.food;

import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.client.TAudio;
import com.ryorama.terrariamod.items.impl.ItemT;
import com.ryorama.terrariamod.items.impl.interfaces.IFoodItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Mushroom extends ItemT implements IFoodItem {

    public Mushroom(Properties settings) {
        super(settings);
        this.isConsumable(true);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand hand) {
        if (!BuffsT.EntityHasBuff(playerEntity, BuffsT.POTION_SICKNESS)) {
            playerEntity.playSound(TAudio.EAT, 1, 1);
            playerEntity.heal(15);
            addBuffOnEaten(playerEntity, BuffsT.POTION_SICKNESS,1000);
            handlePlayerHunger(playerEntity, 4, 2);
            if (!playerEntity.isCreative()) {
                playerEntity.getItemInHand(hand).shrink(1);
            }
            return InteractionResultHolder.success(playerEntity.getItemInHand(hand));
        }

        return InteractionResultHolder.fail(playerEntity.getItemInHand(hand));
    }
}