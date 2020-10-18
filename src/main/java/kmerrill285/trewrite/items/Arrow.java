package kmerrill285.trewrite.items;

import kmerrill285.trewrite.entities.projectiles.EntityArrowT;
import net.minecraft.entity.LivingEntity;

public class Arrow extends ItemT {
	
	public float recovery = 0.5f;
	public float dropRegular = -1.0f;
	public int piercing;
	public boolean gravity = true;
	
	public Arrow (Properties properties, String name, int damage) {
		super(properties, name);
		this.damage = damage;
		this.ranged = true;
		this.velocity = 3;
		this.knockback = 2;
		this.isAmmo = true;
	}
	
	public void onArrowShoot(EntityArrowT arrow) {
		
	}
	
	public void arrowTick(EntityArrowT entityArrowT) {
		
	}
	
	public void onArrowHit(EntityArrowT arrow, LivingEntity hit) {
		
	}
	
}
