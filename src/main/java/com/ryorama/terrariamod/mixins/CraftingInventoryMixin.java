package com.ryorama.terrariamod.mixins;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CraftingInventory.class)
public abstract class CraftingInventoryMixin implements Inventory {
    private static final int STACC_SIZE_LIMIT;
    static {
        if(FabricLoader.getInstance().isModLoaded("fastbench")) {
            STACC_SIZE_LIMIT = 1_000_000;
        } else {
            STACC_SIZE_LIMIT = 1_000;
        }
    }
    @Override
    public int getMaxCountPerStack() {
        return STACC_SIZE_LIMIT;
    }
}