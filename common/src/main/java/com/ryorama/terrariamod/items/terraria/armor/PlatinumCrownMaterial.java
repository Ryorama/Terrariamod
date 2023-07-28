package com.ryorama.terrariamod.items.terraria.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class PlatinumCrownMaterial implements ArmorMaterial {

	@Override
	public int getDurability(ArmorItem.Type type) {
		return 9999999;
	}

	@Override
	public int getProtection(ArmorItem.Type type) {
		return 5;
	}

	@Override
	public int getEnchantability() {
		return 0;
	}

	@Override
	public SoundEvent getEquipSound() {
		return null;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return null;
	}

	@Override
	public String getName() {
		return "platinum_crown";
	}

	@Override
	public float getToughness() {
		return 5;
	}

	@Override
	public float getKnockbackResistance() {
		return 1;
	}

}
