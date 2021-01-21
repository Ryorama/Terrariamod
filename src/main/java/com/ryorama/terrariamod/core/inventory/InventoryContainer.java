package com.ryorama.terrariamod.core.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

public abstract class InventoryContainer extends Container {

	private final List<InventorySlot> slotsOrigin = new ArrayList<>();
	  
	public final List<InventorySlot> slots = Collections.unmodifiableList(this.slotsOrigin);

	public PlayerEntity player;
	
	public InventoryContainer(ContainerType<?> type, int id) {
		super(type, id);
	}
	
	protected void addSlot(InventorySlot slot) {
		int size = this.slotsOrigin.size();
		
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return false;
	}

	protected abstract void initSlots();
	
}
