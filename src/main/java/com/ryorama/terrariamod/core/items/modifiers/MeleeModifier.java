package com.ryorama.terrariamod.core.items.modifiers;

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