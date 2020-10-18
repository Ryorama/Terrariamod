package kmerrill285.trewrite.items.terraria.axes;

import kmerrill285.trewrite.items.Axe;
import kmerrill285.trewrite.util.Conversions;

public class CrimtaneAxe extends Axe {

	public CrimtaneAxe() {
		super();
		this.axe = 75;
		this.damage = 22;
		this.knockback = 6f;
		this.useTime = 32;
		this.speed = 15;
		this.sellPrice = 1600;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("crimtane_axe");
	}

}
