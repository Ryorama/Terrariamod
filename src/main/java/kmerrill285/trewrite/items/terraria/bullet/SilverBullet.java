package kmerrill285.trewrite.items.terraria.bullet;

import kmerrill285.trewrite.items.Bullet;
import net.minecraft.item.ItemGroup;

public class SilverBullet extends Bullet {

	public SilverBullet() {
		super(new Properties().group(ItemGroup.COMBAT), "silver_bullet", 0, 0, 0);
		this.damage = 9;
		this.velocity = 4.5f;
		this.knockback = 3;
		this.setBuySell(15);
	}

}
