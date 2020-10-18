package kmerrill285.trewrite.items.terraria.shortswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.items.Shortsword;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.item.Item.Properties;

public class GoldShortsword extends Shortsword {
	public GoldShortsword() {
		super();
		this.damage = 11;
		this.knockback = 4;
		this.useTime = 11;
		this.sellPrice = 1400;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("gold_shortsword");
	}
}
