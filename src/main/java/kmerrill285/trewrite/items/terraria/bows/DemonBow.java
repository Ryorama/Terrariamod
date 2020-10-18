package kmerrill285.trewrite.items.terraria.bows;

import kmerrill285.trewrite.items.Bow;
import net.minecraft.item.ItemGroup;

public class DemonBow extends Bow {
	public DemonBow() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1), "demon_bow", 14);
		this.knockback = 1;
		this.useTime = 25;
		this.velocity = 6.7f;
		this.setBuySell(3600);
	}
}
