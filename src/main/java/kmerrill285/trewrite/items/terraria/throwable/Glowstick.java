package kmerrill285.trewrite.items.terraria.throwable;

import kmerrill285.trewrite.entities.projectiles.EntityThrowingT;
import kmerrill285.trewrite.items.ThrowableItem;
import net.minecraft.item.ItemGroup;

public class Glowstick extends ThrowableItem {
	public Glowstick() {
		super(new Properties().group(ItemGroup.COMBAT), "glowstick", 0);
		this.knockback = 0;
		this.useTime = 15;
		this.setBuySell(2);
		this.velocity = 6;
		this.recovery = 1.0f/3.0f;
		this.setTooltip("Works when wet");
		this.maxStack = 99;
	}
	
	protected void setExplosiveAttribs(EntityThrowingT entity) {
		//public void setExplosiveAttribs(boolean grenade, boolean sticky, boolean destroyTiles, boolean bounce) {
		entity.setThrowingAttribs(5*20*60, false, false, false, 12, false);
	}
}
