package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.inventory.Inventory;

@Mixin(Inventory.class)
public interface InventoryMixin {
	
	@Overwrite
	default int getMaxCountPerStack() {
		return 999;
	}
}
