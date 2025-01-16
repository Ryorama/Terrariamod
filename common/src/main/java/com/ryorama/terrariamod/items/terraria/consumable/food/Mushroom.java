package com.ryorama.terrariamod.items.terraria.consumable.food;

import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.client.TAudio;
import com.ryorama.terrariamod.items.impl.ItemT;
import com.ryorama.terrariamod.items.impl.interfaces.IFoodItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Mushroom extends ItemT implements IFoodItem {

    public Mushroom(Settings settings) {
        super(settings.maxCount(64));
        this.isConsumable(true);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (!BuffsT.EntityHasBuff(playerEntity, BuffsT.POTION_SICKNESS)) {
            playerEntity.playSound(TAudio.EAT, 1, 1);
            playerEntity.heal(15);
            addBuffOnEaten(playerEntity, BuffsT.POTION_SICKNESS,1000);
            handlePlayerHunger(playerEntity, 4, 2);
            if (!playerEntity.isCreative()) {
                playerEntity.getStackInHand(hand).decrement(1);
            }
            return TypedActionResult.success(playerEntity.getStackInHand(hand));
        }

        return TypedActionResult.fail(playerEntity.getStackInHand(hand));
    }
}