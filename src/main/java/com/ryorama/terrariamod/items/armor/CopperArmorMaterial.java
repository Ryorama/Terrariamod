package com.ryorama.terrariamod.items.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class CopperArmorMaterial implements ArmorMaterial {

	@Override
	public int getDurability(EquipmentSlot slot) {
		return 9999999;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return 8;
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
		return "copper";
	}

	@Override
	public float getToughness() {
		return 8;
	}

	@Override
	public float getKnockbackResistance() {
		return 1;
	}

}
