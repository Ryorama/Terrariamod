package com.ryorama.terrariamod.core.items.modifiers;

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