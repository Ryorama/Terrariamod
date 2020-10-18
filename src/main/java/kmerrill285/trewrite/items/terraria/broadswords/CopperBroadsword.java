package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;

public class CopperBroadsword extends Broadsword {

	public CopperBroadsword() {
		super();
		this.damage = 8;
		this.knockback = 5f;
		this.useTime = 23;
		this.speed = 23;
		this.sellPrice = 90;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("copper_broadsword");
	}

}
