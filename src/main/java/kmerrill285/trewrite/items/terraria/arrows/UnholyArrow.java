package kmerrill285.trewrite.items.terraria.arrows;

import kmerrill285.trewrite.items.Arrow;
import net.minecraft.item.ItemGroup;

public class UnholyArrow extends Arrow {

	public UnholyArrow() {
		super(new Properties().group(ItemGroup.COMBAT), "unholy_arrow", 12);
		this.maxStack = 999;
		this.velocity = 3.4f;
		this.recovery = 0.25f;
		this.setBuySell(8);
		this.piercing = 4;
	}

}
