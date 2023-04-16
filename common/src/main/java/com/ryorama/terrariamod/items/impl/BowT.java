package com.ryorama.terrariamod.items.impl;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BowT extends ItemT {

    public BowT(Settings settings, int damage) {
        super(settings);
        this.damage = damage;
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {

        ItemStack itemstack = playerIn.getMainHandStack();

        Inventory inventory = playerIn.getInventory();


        boolean gotFirstArrowStack = false;
        int arrowSlotId = 0;

        for (int i = 0; i <= inventory.size(); i++) {
            if (inventory.getStack(i).getItem() instanceof ArrowT && !gotFirstArrowStack) {
                gotFirstArrowStack = true;
                arrowSlotId = i;
            } else if (inventory.getStack(arrowSlotId).getCount() <= 0 && gotFirstArrowStack) {
                gotFirstArrowStack = false;
            }
        }

        if (inventory.getStack(arrowSlotId).getCount() > 0 && gotFirstArrowStack) {
            ArrowT arrowItem = (ArrowT)inventory.getStack(arrowSlotId).getItem();
            PersistentProjectileEntity arrow = ProjectileUtil.createArrowProjectile(playerIn, inventory.getStack(arrowSlotId), 0);
            arrow.setDamage(this.damage + arrowItem.damage);
            arrow.setOnFire(arrowItem.fireDamage);
            if (arrowItem.fireDamage) {
                arrow.setOnFireFor(10000);
            }
            arrow.setVelocity(playerIn, playerIn.getPitch(), playerIn.getYaw(), 0.0F, 3.0F, 1.0F);
            worldIn.spawnEntity(arrow);

            inventory.getStack(arrowSlotId).decrement(1);
        }

        playerIn.getItemCooldownManager().set(this, (int) ((this.useTime - this.useTime * speed) * (30.0 / 60.0)));

        return new TypedActionResult<>(ActionResult.SUCCESS, itemstack);
    }
}