package kmerrill285.trewrite.items.terraria.bows;

import kmerrill285.trewrite.items.Bow;
import net.minecraft.item.ItemGroup;

public class SilverBow extends Bow {
	public SilverBow() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1), "silver_bow", 0);
		this.damage = 9;
		this.knockback = 0;
		this.useTime = 27;
		this.velocity = 6.6f;
		this.setBuySell(700);
	}
}
