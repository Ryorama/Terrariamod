package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;

public class BreakerBlade extends Broadsword {

	public BreakerBlade() {
		super();
		this.damage = 43;
		this.knockback = 8;
		this.useTime = 29;
		this.speed = 29;
		this.sellPrice = 90;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("breaker_blade");
		this.setLightValue(13);
	}
	
}