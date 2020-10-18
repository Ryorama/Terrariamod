package kmerrill285.trewrite.items.terraria.bullet;

import kmerrill285.trewrite.items.Bullet;
import net.minecraft.item.ItemGroup;

public class MusketBall extends Bullet {

	public MusketBall() {
		super(new Properties().group(ItemGroup.COMBAT), "musket_ball", 0, 0, 0);
		this.damage = 7;
		this.velocity = 4;
		this.knockback = 2;
		this.setBuySell(1);
		this.setMaterial();
	}

}
