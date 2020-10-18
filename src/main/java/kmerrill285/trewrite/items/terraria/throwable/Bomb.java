package kmerrill285.trewrite.items.terraria.throwable;

import kmerrill285.trewrite.entities.projectiles.EntityThrowingT;
import kmerrill285.trewrite.items.ThrowableItem;
import net.minecraft.item.ItemGroup;

public class Bomb extends ThrowableItem {
	public Bomb() {
		super(new Properties().group(ItemGroup.COMBAT), "bomb", 100);
		this.knockback = 0;
		this.useTime = 25;
		this.setBuySell(60);
		this.velocity = 5.0f;
		this.recovery = 0.0f;
		this.maxStack = 99;
		this.setTooltip("A small explosion that will destroy some tiles");
	}
	
	protected void setExplosiveAttribs(EntityThrowingT entity) {
		//public void setExplosiveAttribs(boolean grenade, boolean sticky, boolean destroyTiles, boolean bounce) {
		entity.setThrowingAttribs(60, false, true, false, 0, true);
	}
}
