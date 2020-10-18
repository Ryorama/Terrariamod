package kmerrill285.trewrite.items.terraria.clickable;

import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.items.ItemT;
import net.minecraft.entity.player.PlayerEntity;

public abstract class Clickable extends ItemT {
	
	public Clickable(Properties properties, String name) {
		super(properties, name);
	}
	
	public abstract void use(PlayerEntity player, InventorySlot slot);
}
