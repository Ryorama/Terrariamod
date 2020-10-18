package kmerrill285.trewrite.items.terraria.shortswords;

import kmerrill285.trewrite.items.Shortsword;
import kmerrill285.trewrite.util.Conversions;

public class CopperShortsword extends Shortsword {

	public CopperShortsword() {
		super();
		this.damage = 5;
		this.knockback = 4f;
		this.useTime = 13;
		this.speed = 13;
		this.sellPrice = 70;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("copper_shortsword");
	}

}
