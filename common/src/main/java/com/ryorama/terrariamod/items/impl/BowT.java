package com.ryorama.terrariamod.items.impl;

import com.ryorama.terrariamod.entities.impl.ArrowEntityT;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BowT extends ItemT {

    public BowT(Properties settings, int damage) {
        super(settings.stacksTo(1));
        this.damage = damage;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {

        ItemStack itemstack = playerIn.getMainHandItem();

        Inventory inventory = playerIn.getInventory();


        boolean gotFirstArrowStack = false;
        int arrowSlotId = 0;

        for (int i = 0; i <= inventory.getContainerSize(); i++) {
            if (inventory.getItem(i).getItem() instanceof ArrowT && !gotFirstArrowStack) {
                gotFirstArrowStack = true;
                arrowSlotId = i;
            } else if (inventory.getItem(arrowSlotId).getCount() <= 0 && gotFirstArrowStack) {
                gotFirstArrowStack = false;
            }
        }

        if (inventory.getItem(arrowSlotId).getCount() > 0 && gotFirstArrowStack) {
            ArrowT arrowItem = (ArrowT)inventory.getItem(arrowSlotId).getItem();
            AbstractArrow arrow = ProjectileUtil.getMobArrow(playerIn, inventory.getItem(arrowSlotId), 0);
            if (arrowItem.fireDamage) {
                if (arrow instanceof ArrowEntityT) {
                    ((ArrowEntityT)arrow).SetFireDamage(true);
                }
            }
            arrow.setBaseDamage(this.damage + arrowItem.damage);
            arrow.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 3.0F, 1.0F);
            worldIn.addFreshEntity(arrow);

            inventory.getItem(arrowSlotId).shrink(1);
        }

        playerIn.getCooldowns().addCooldown(this, (int) ((this.useTime - this.useTime * speed) * (30.0 / 60.0)));

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
    }
}