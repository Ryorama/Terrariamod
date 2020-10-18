package kmerrill285.trewrite.items;

import kmerrill285.trewrite.core.inventory.InventorySlot;
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
		if (this == ItemsT.WOODEN_HELMET) {
			if (armor[1].stack != null && armor[2].stack != null)
			if (armor[1].stack.item == ItemsT.WOODEN_CHESTPLATE && armor[2].stack.item == ItemsT.WOODEN_GREAVES) {
				return defense + 1;
			}
		}
		if (this == ItemsT.RICH_MAHOGANY_HELMET) {
			if (armor[1].stack != null && armor[2].stack != null)
				if (armor[1].stack.item == ItemsT.RICH_MAHOGANY_BREASTPLATE && armor[2].stack.item == ItemsT.RICH_MAHOGANY_GREAVES) {
					return defense + 1;
				}
		}
		if (this == ItemsT.SILVER_HELMET) {
			if (armor[1].stack != null && armor[2].stack != null)
				if (armor[1].stack.item == ItemsT.SILVER_CHESTPLATE && armor[2].stack.item == ItemsT.SILVER_GREAVES) {
					return defense + 3;
				}
		}
		return defense;
	}
}