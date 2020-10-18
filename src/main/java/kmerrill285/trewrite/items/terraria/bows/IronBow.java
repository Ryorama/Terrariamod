package kmerrill285.trewrite.items.terraria.bows;

import kmerrill285.trewrite.items.Bow;
import net.minecraft.item.ItemGroup;

public class IronBow extends Bow {
	public IronBow() {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1), "iron_bow", 0);
		this.damage = 8;
		this.knockback = 0;
		this.useTime = 28;
		this.velocity = 6.6f;
		this.setBuySell(280);
	}
}
