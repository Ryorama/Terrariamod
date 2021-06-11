package kmerrill285.trewrite.core.inventory;

import kmerrill285.trewrite.Trewrite;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.items.Armor;
import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.items.accessories.Accessory;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.util.ResourceLocation;

public class InventorySlot {

	public ItemStackT stack;
	public ItemType itemType;
	
	
	public int x, y, area, id;
	
	public boolean isTrashSlot = false;
	
	public InventorySlot(ItemType itemType, int x, int y, int area, int id) {
		this.itemType = itemType;
		this.x = x;
		this.y = y;
		this.area = area;
		this.id = id;
	}
	
	public void swapStacks(InventorySlot slot) {
		if (stack != null)
		if (this.canInteractWith(slot)) {
			ItemStackT stack = new ItemStackT(this.stack.item, this.stack.size, ItemModifier.getModifier(this.stack.modifier));
			this.stack = slot.stack;
			slot.stack = stack;
		}
	}
	
	public void takeFromStack(InventorySlot slot) {
		if (canInteractWith(slot)) {
			if (stack == null) {
				ItemStackT stack = new ItemStackT(slot.stack.item, slot.stack.size, ItemModifier.getModifier(this.stack.modifier));
				this.stack = stack;
				slot.stack = null;
				return;
			}
			if (stack.item instanceof ItemT) {
				if (stack.size == ((ItemT)stack.item).maxStack || stack.item != slot.stack.item) {
					swapStacks(slot);
				} else {
					stack.size += slot.stack.size;
					if (stack.size > ((ItemT)stack.item).maxStack) {
						slot.stack.size = stack.size - ((ItemT)stack.item).maxStack;
						stack.size = ((ItemT)stack.item).maxStack;
					} else {
						slot.stack = null;
					}
				}
			} else {
				if (stack.size == stack.itemForRender.getMaxStackSize() || stack.item != slot.stack.item) {
					swapStacks(slot);
				} else {
					stack.size += slot.stack.size;
					if (stack.size > stack.itemForRender.getMaxStackSize()) {
						slot.stack.size = stack.size - stack.itemForRender.getMaxStackSize();
						stack.size = stack.itemForRender.getMaxStackSize();
					} else {
						slot.stack = null;
					}
				}
			}
			
			
		}
	}
	
	public void decrementStack(int i) {
		if (this.stack != null) {
			this.stack.size -= 1;
			if (this.stack.size <= 0) {
				this.stack = null;
			}
		}
	}
	
	
	public boolean canInteractWith(InventorySlot slot) {
		if (this.itemType == ItemType.ANY) return true; 
		if (this.itemType == ItemType.ACCESSORY) {
			if (slot.stack.item instanceof Accessory) {
				return true;
			}
		}
		if (this.itemType == ItemType.HEAD) {
			if (slot.stack.item instanceof Armor) {
				Armor a = (Armor)slot.stack.item;
				if (a.type == Armor.ArmorType.HEAD)
					return true;
			}
		}
		if (this.itemType == ItemType.CHESTPLATE) {
			if (slot.stack.item instanceof Armor) {
				Armor a = (Armor)slot.stack.item;
				if (a.type == Armor.ArmorType.CHEST)
					return true;
			}
		}
		if (this.itemType == ItemType.LEGGINGS) {
			if (slot.stack.item instanceof Armor) {
				Armor a = (Armor)slot.stack.item;
				if (a.type == Armor.ArmorType.LEGS)
					return true;
			}
		}
		return false;
	}
	
	public void update() {
		if (this.stack != null) {
			if (this.stack.size <= 0) {
				this.stack = null;
			}
			if (this.stack.item instanceof ItemT) {
				if (this.stack.size > ((ItemT)this.stack.item).maxStack) {
					this.stack.size = ((ItemT)this.stack.item).maxStack;
				}
			} else {
				if (this.stack.size > this.stack.itemForRender.getMaxStackSize()) {
					this.stack.size = this.stack.itemForRender.getMaxStackSize();
				}
			}
		}
	}
	
	public enum ItemType {
		ANY, HEAD, CHESTPLATE, LEGGINGS, DYE, ACCESSORY
	}
}
