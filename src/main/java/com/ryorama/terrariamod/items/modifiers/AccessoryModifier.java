package com.ryorama.terrariamod.items.modifiers;

import com.ryorama.terrariamod.items.EnumModifierType;
import com.ryorama.terrariamod.items.ItemModifier;

public class AccessoryModifier extends ItemModifier {

	public AccessoryModifier(String name, double tier, double value) {
		super(EnumModifierType.ACCESSORY, name, tier, value);
	}

	public ItemModifier defense(int i) {
		this.defense = i;
		return this;
	}
	public ItemModifier crit(int i) {
		this.crit = i;
		return this;
	}
	public ItemModifier damage(int i) {
		this.damage = i;
		return this;
	}
	public ItemModifier movementSpeed(int i) {
		this.movementSpeed = i;
		return this;
	}
	public ItemModifier meleeSpeed(int i) {
		this.meleeSpeed = i;
		return this;
	}
	public ItemModifier mana(int i) {
		this.mana = i;
		return this;
	}
	public ItemModifier health(int i) {
		this.health = i;
		return this;
	}
}