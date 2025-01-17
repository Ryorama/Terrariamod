package com.ryorama.terrariamod.items.terraria.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class GoldTArmorMaterial implements ArmorMaterial {

	@Override
	public int getDurabilityForType(ArmorItem.Type type) {
		return 9999999;
	}

	@Override
	public int getDefenseForType(ArmorItem.Type type) {
		return 24;
	}

	@Override
	public int getEnchantmentValue() {
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
		return "goldt";
	}

	@Override
	public float getToughness() {
		return 24;
	}

	@Override
	public float getKnockbackResistance() {
		return 1;
	}

}
