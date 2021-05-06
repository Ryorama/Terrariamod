package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.client.gui.screen.ingame.InventoryScreen;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

	/*
	
	public List<Slot> slots = new ArrayList<Slot>();
	
	public int slotAmount = 40;
	public int currentX = 0;
	public int currentY = 0;
	public int maxX = 50;
	public int maxY = 20;
	
	@Inject(at = @At("HEAD"), method = "init")
	protected void init() {
		for (int i = 1; i <= slotAmount; i++) {
			if (currentX <= maxX) {
				currentX += 5;
				addSlot(this, slotAmount - 1, currentX, 1);
			}
		}
	}
	
	public void addSlot(Inventory inv, int slotId, int x, int y) {
		slots.add(new Slot(inv, slotId, x, y));
	}

	@Override
	public void clear() {
		
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public ItemStack getStack(int slot) {
		return null;
	}

	@Override
	public ItemStack removeStack(int slot, int amount) {
		return null;
	}

	@Override
	public ItemStack removeStack(int slot) {
		return null;
	}

	@Override
	public void setStack(int slot, ItemStack stack) {		
	}

	@Override
	public void markDirty() {		
	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return false;
	}
	
	*/
}
