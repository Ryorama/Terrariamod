package com.ryorama.terrariamod.core.client.inventory;

import com.ryorama.terrariamod.items.ItemModifier;
import com.ryorama.terrariamod.items.ItemStackT;
import com.ryorama.terrariamod.items.api.AccessoryT;
import com.ryorama.terrariamod.items.api.ArmorT;
import com.ryorama.terrariamod.items.api.ItemT;

public class InventorySlot {

    public ItemStackT stack;
    public ItemType itemType;
    public boolean isFavorite;

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
                if (stack.size == stack.itemForRender.getMaxCount() || stack.item != slot.stack.item) {
                    swapStacks(slot);
                } else {
                    stack.size += slot.stack.size;
                    if (stack.size > stack.itemForRender.getMaxCount()) {
                        slot.stack.size = stack.size - stack.itemForRender.getMaxCount();
                        stack.size = stack.itemForRender.getMaxCount();
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
            if (slot.stack.item instanceof AccessoryT) {
                return true;
            }
        }
        if (this.itemType == ItemType.HEAD) {
            if (slot.stack.item instanceof ArmorT) {
                ArmorT a = (ArmorT)slot.stack.item;
                if (a.type == ArmorT.ArmorType.HEAD)
                    return true;
            }
        }
        if (this.itemType == ItemType.CHESTPLATE) {
            if (slot.stack.item instanceof ArmorT) {
                ArmorT a = (ArmorT)slot.stack.item;
                if (a.type == ArmorT.ArmorType.CHEST)
                    return true;
            }
        }
        if (this.itemType == ItemType.LEGGINGS) {
            if (slot.stack.item instanceof ArmorT) {
                ArmorT a = (ArmorT)slot.stack.item;
                if (a.type == ArmorT.ArmorType.LEGS)
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
                if (this.stack.size > this.stack.itemForRender.getMaxCount()) {
                    this.stack.size = this.stack.itemForRender.getMaxCount();
                }
            }
        }
    }

    public enum ItemType {
        ANY, HEAD, CHESTPLATE, LEGGINGS, DYE, ACCESSORY
    }
}