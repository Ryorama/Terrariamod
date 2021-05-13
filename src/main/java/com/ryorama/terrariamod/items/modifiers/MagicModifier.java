package com.ryorama.terrariamod.items.modifiers;

import com.ryorama.terrariamod.items.EnumModifierType;
import com.ryorama.terrariamod.items.ItemModifier;

public class MagicModifier extends ItemModifier {

	public MagicModifier(String name, double damage, double speed, double crit, double manaCost, double knockback, double tier, double value) {
		super(EnumModifierType.MAGIC, name, tier, value);
		this.damage = damage;
		this.crit = crit;
		this.knockback = knockback;
		this.speed = speed;
		this.manaCost = manaCost;
	}

}