package kmerrill285.trewrite.items.terraria.bows;

import kmerrill285.trewrite.items.Bow;
import net.minecraft.item.ItemGroup;

public class GoldBow extends Bow {
	public GoldBow() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1), "gold_bow", 0);
		this.damage = 11;
		this.knockback = 0;
		this.useTime = 26;
		this.velocity = 6.6f;
		this.setBuySell(1400);
	}
}
