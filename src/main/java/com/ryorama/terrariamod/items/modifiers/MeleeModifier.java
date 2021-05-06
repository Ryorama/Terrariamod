package com.ryorama.terrariamod.items.modifiers;

import com.ryorama.terrariamod.items.EnumModifierType;
import com.ryorama.terrariamod.items.ItemModifier;

public class MeleeModifier extends ItemModifier {

	public MeleeModifier(String name, double damage, double speed, double crit, double size, double knockback, double tier, double value) {
		super(EnumModifierType.MELEE, name, tier, value);
		this.damage = damage;
		this.crit = crit;
		this.knockback = knockback;
		this.speed = speed;
		this.size = size;
	}

}