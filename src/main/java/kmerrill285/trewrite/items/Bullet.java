package kmerrill285.trewrite.items;

import kmerrill285.trewrite.entities.projectiles.EntityBullet;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.LivingEntity;

public class Bullet extends ItemT {
	
	public float recovery = 0.0f;
	public float dropRegular = -1.0f;
	public int piercing;
	public boolean gravity = true;
	
	public Bullet (Properties properties, String name, int damage, int velocity, int knockback) {
		super(properties, name);
		this.damage = damage;
		this.ranged = true;
		this.velocity = velocity;
		this.knockback = knockback;
		this.isAmmo = true;
	}
	
	public void onBulletShoot(EntityBullet bullet) {
		WorldStateHolder.get(bullet.world).setLight(bullet.getPosition(), 10, bullet.world.getDimension().getType());
	}
	
	public void bulletTick(EntityBullet bullet) {
		
	}
	
	public void onBulletHit(EntityBullet bullet, LivingEntity hit) {
		
	}
	
}
