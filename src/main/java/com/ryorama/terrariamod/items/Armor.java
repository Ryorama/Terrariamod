package com.ryorama.terrariamod.items;

import com.ryorama.terrariamod.core.inventory.InventorySlot;

import net.minecraft.item.ArmorMaterial;

public class Armor extends ItemT {
	
	public enum ArmorType {
		HEAD,
		CHEST,
		LEGS
	}
	
	public ArmorType type;
	
	
	public Armor(Properties properties, String name, ArmorType type, int defense) {
		super(properties, name);
		this.defense = defense;
		this.type = type;
	}

	public ArmorMaterial getArmorMaterial() {
		return ArmorMaterial.TURTLE;
	}
	
	public int getDefense(InventorySlot[] armor) {
		return defense;
	}
}