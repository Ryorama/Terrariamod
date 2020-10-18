package kmerrill285.trewrite.items.terraria.bows;

import kmerrill285.trewrite.items.Bow;
import net.minecraft.item.ItemGroup;

public class CopperBow extends Bow {
	public CopperBow() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1), "copper_bow", 0);
		this.damage = 6;
		this.knockback = 0;
		this.useTime = 29;
		this.velocity = 6.6f;
		this.setBuySell(70);
	}
}
