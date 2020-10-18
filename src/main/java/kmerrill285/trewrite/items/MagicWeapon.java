package kmerrill285.trewrite.items;


import kmerrill285.trewrite.entities.projectiles.EntityMagicProjectile;
import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public abstract class MagicWeapon extends ItemT {

	public MagicWeapon(Properties properties, String name, int damage) {
		super(properties, name);
		this.damage = damage;
		this.magic = true;
		MODIFIER_TYPE = EnumModifierType.MAGIC;
		this.setMaxStack(1);
		this.animation = ItemT.STAFF_ANIMATION;
	}
	
	public int getUseDuration(ItemStack t) {
		return 4;
	}

	public abstract void shoot(EntityMagicProjectile projectile);
	public abstract void tick(EntityMagicProjectile projectile);
	public abstract void hit(EntityMagicProjectile projectile, LivingEntity entity);
}
