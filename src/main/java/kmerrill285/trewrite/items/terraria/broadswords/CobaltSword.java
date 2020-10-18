package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;

public class CobaltSword extends Broadsword {

	public CobaltSword() {
		super();
		this.damage = 39;
		this.knockback = 3.85f;
		this.useTime = 23;
		this.speed = 23;
		this.sellPrice = 90;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("cobalt_sword");
	}
	
}
