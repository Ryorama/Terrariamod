package kmerrill285.trewrite.items.modifiers;

public class UniversalModifer extends ItemModifier {

	public UniversalModifer(String name, double damage, double crit, double knockback, double tier, double value) {
		super(EnumModifierType.UNIVERSAL, name, tier, value);
		this.damage = damage;
		this.crit = crit;
		this.knockback = knockback;
	}

}
