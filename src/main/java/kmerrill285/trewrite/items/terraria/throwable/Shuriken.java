package kmerrill285.trewrite.items.terraria.throwable;

import kmerrill285.trewrite.items.ThrowableItem;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;

public class Shuriken extends ThrowableItem {
	public Shuriken() {
		super(new Properties().group(ItemGroup.COMBAT), "shuriken", 10);
		this.knockback = 0;
		this.useTime = 15;
		this.setBuySell(3);
		this.velocity = 9;
		this.recovery = 0.5f;
	}
}
