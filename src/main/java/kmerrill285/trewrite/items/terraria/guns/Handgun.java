package kmerrill285.trewrite.items.terraria.guns;

import kmerrill285.trewrite.items.Gun;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;

public class Handgun extends Gun {
	public Handgun() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1), "handgun", 0);
		this.knockback = 3;
		this.useTime = 12;
		this.velocity = 10;
		this.damage = 17;
		this.setBuySell(10000);
		this.setMaterial();
	}
}
