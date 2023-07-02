package com.ryorama.terrariamod.items.impl;

import com.ryorama.terrariamod.entities.impl.ArrowEntityT;
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
        super(settings.maxCount(1));
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
            if (arrowItem.fireDamage) {
                if (arrow instanceof ArrowEntityT) {
                    ((ArrowEntityT)arrow).SetFireDamage(true);
                }
            }
            arrow.setDamage(this.damage + arrowItem.damage);
            arrow.setVelocity(playerIn, playerIn.getPitch(), playerIn.getYaw(), 0.0F, 3.0F, 1.0F);
            worldIn.spawnEntity(arrow);

            inventory.getStack(arrowSlotId).decrement(1);
        }

        playerIn.getItemCooldownManager().set(this, (int) ((this.useTime - this.useTime * speed) * (30.0 / 60.0)));

        return new TypedActionResult<>(ActionResult.SUCCESS, itemstack);
    }
}