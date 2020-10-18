package kmerrill285.trewrite.items.terraria.shortswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.items.Shortsword;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.item.Item.Properties;

public class IronShortsword extends Shortsword {
	public IronShortsword() {
		super();
		this.damage = 8;
		this.knockback = 4;
		this.useTime = 11;
		this.sellPrice = 280;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("iron_shortsword");
	}
}
