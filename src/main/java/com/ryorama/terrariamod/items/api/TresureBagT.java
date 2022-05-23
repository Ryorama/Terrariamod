package com.ryorama.terrariamod.items.api;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TresureBagT extends ItemT {

    public List<ItemStack> loot = new ArrayList<>();

    public TresureBagT(Settings settings) {
        super(settings);
        addLoot(new ArrayList<>());
    }

    public void addLoot(List<ItemStack> loot) {
        for (int e = 0; e < loot.size() - 1; e++) {
            this.loot.add(loot.get(e));
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (loot.size() > 0) {
            for (int e = 0; e <= loot.size() - 1; e++) {
                ItemEntity drop = new ItemEntity(world, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), loot.get(e));
                drop.setPos(playerEntity.getX(), playerEntity.getY(), playerEntity.getZ());
                world.spawnEntity(drop);
            }
            playerEntity.getInventory().getMainHandStack().decrement(1);
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
