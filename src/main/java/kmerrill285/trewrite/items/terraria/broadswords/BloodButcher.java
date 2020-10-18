package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;

public class BloodButcher extends Broadsword {

	public BloodButcher() {
		super();
		this.damage = 22;
		this.knockback = 5;
		this.useTime = 25;
		this.speed = 25;
		this.sellPrice = 90;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("blood_butcher");
	}
	
}