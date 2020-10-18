package kmerrill285.trewrite.items.modifiers;

public class CommonModifier extends ItemModifier {

	public CommonModifier(String name, double damage, double speed, double crit, double knockback, double tier, double value) {
		super(EnumModifierType.COMMON, name, tier, value);
		this.damage = damage;
		this.knockback = knockback;
		this.speed = speed;
		this.crit = crit;
	}

}
