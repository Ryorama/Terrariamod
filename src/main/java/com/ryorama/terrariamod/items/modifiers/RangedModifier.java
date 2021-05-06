package com.ryorama.terrariamod.items.modifiers;

import com.ryorama.terrariamod.items.EnumModifierType;
import com.ryorama.terrariamod.items.ItemModifier;

public class RangedModifier extends ItemModifier {

	public RangedModifier(String name, double damage, double speed , double crit, double velocity, double knockback, double tier, double value) {
		super(EnumModifierType.RANGED, name, tier, value);
		this.damage = damage;
		this.crit = crit;
		this.knockback = knockback;
		this.speed = speed;
		this.velocity = velocity;
	}

}
