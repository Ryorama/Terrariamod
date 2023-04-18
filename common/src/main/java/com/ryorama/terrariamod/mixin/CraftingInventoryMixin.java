package com.ryorama.terrariamod.mixin;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CraftingInventory.class)
public abstract class CraftingInventoryMixin implements Inventory {
    @Override
    public int getMaxCountPerStack() {
        return 999;
    }
}