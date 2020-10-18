package kmerrill285.trewrite.items.terraria.throwable;

import kmerrill285.trewrite.entities.projectiles.EntityThrowingT;
import kmerrill285.trewrite.items.ThrowableItem;
import net.minecraft.item.ItemGroup;

public class Grenade extends ThrowableItem {
	public Grenade() {
		super(new Properties().group(ItemGroup.COMBAT), "grenade", 60);
		this.knockback = 8;
		this.useTime = 45;
		this.setBuySell(15);
		this.velocity = 8.5f;
		this.recovery = 0.0f;
		this.maxStack = 99;
	}
	
	protected void setExplosiveAttribs(EntityThrowingT entity) {
		//public void setExplosiveAttribs(boolean grenade, boolean sticky, boolean destroyTiles, boolean bounce) {
		entity.setThrowingAttribs(60, false, false, false, 0, true);
	}
}
