package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;

public class PallidumSword extends Broadsword {

	public PallidumSword() {
		super();
		this.damage = 45;
		this.knockback = 4.75f;
		this.useTime = 25;
		this.speed = 25;
		this.sellPrice = 90;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("pallidum_sword");
	}
	
}