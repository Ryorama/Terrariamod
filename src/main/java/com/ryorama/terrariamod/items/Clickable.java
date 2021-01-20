package com.ryorama.terrariamod.items;

import com.ryorama.terrariamod.core.inventory.InventorySlot;

import net.minecraft.entity.player.PlayerEntity;

public abstract class Clickable extends ItemT {
	
	public Clickable(Properties properties, String name) {
		super(properties, name);
	}
	
	public abstract void use(PlayerEntity player, InventorySlot slot);
}