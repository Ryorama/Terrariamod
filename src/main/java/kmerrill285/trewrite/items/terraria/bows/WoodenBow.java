package kmerrill285.trewrite.items.terraria.bows;

import kmerrill285.trewrite.items.Bow;
import net.minecraft.item.ItemGroup;

public class WoodenBow extends Bow {
	public WoodenBow() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1), "wooden_bow", 4);
		this.knockback = 0;
		this.useTime = 30;
		this.velocity = 6.1f;
		this.setBuySell(20);
	}
}
