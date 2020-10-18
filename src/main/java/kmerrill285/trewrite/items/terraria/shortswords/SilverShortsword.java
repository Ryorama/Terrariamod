package kmerrill285.trewrite.items.terraria.shortswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.items.Shortsword;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.item.Item.Properties;

public class SilverShortsword extends Shortsword {
	public SilverShortsword() {
		super();
		this.damage = 9;
		this.knockback = 4;
		this.useTime = 11;
		this.sellPrice = 700;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("silver_shortsword");
	}
}
