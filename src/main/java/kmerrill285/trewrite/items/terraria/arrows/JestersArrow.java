package kmerrill285.trewrite.items.terraria.arrows;

import kmerrill285.trewrite.entities.projectiles.EntityArrowT;
import kmerrill285.trewrite.items.Arrow;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.item.ItemGroup;

public class JestersArrow extends Arrow {

	public JestersArrow() {
		super(new Properties().group(ItemGroup.COMBAT), "jesters_arrow", 10);
		this.maxStack = 999;
		this.velocity = 0.5f;
		this.knockback = 4;
		this.recovery = 0.25f;
		this.setBuySell(20);
		this.piercing = 9999;
		this.gravity = false;
	}
	
	public void arrowTick(EntityArrowT arrow) {
		WorldStateHolder.get(arrow.world).setLight(arrow.getPosition(), 7, arrow.world.getDimension().getType());
	}

}
