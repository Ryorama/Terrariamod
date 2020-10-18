package kmerrill285.trewrite.items.terraria.axes;

import kmerrill285.trewrite.items.Axe;
import kmerrill285.trewrite.util.Conversions;

public class SilverAxe extends Axe {

	public SilverAxe() {
		super();
		this.axe = 50;
		this.damage = 6;
		this.knockback = 4.5f;
		this.useTime = 26;
		this.speed = 18;
		this.sellPrice = 800;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("silver_axe");
	}

}
