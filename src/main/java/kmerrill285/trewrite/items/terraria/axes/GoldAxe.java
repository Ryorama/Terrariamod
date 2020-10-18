package kmerrill285.trewrite.items.terraria.axes;

import kmerrill285.trewrite.items.Axe;
import kmerrill285.trewrite.util.Conversions;

public class GoldAxe extends Axe {

	public GoldAxe() {
		super();
		this.axe = 55;
		this.damage = 7;
		this.knockback = 4.5f;
		this.useTime = 26;
		this.speed = 18;
		this.sellPrice = 1600;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("gold_axe");
	}

}
