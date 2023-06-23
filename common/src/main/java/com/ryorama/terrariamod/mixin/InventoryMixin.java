package com.ryorama.terrariamod.mixin;

import net.minecraft.inventory.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * makes inventories able to store infinite items, so u can actually store superstacks
 */
@Mixin (Inventory.class)
public interface InventoryMixin {
	/**
	 * @author HalfOf2
	 * @reason increase max stack size
	 */
	@Overwrite
	default int getMaxCountPerStack() {
		return 9999;
	}
}