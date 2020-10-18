package kmerrill285.trewrite.items.terraria.arrows;

import kmerrill285.trewrite.items.Arrow;
import net.minecraft.item.ItemGroup;

public class WoodenArrow extends Arrow {

	public WoodenArrow() {
		super(new Properties().group(ItemGroup.COMBAT), "wooden_arrow", 5);
		this.maxStack = 999;
		this.setBuySell(1);
	}

}
