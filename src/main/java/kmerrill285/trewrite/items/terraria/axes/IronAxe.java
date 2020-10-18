package kmerrill285.trewrite.items.terraria.axes;

import kmerrill285.trewrite.items.Axe;
import kmerrill285.trewrite.util.Conversions;

public class IronAxe extends Axe {

	public IronAxe() {
		super();
		this.axe = 45;
		this.damage = 5;
		this.knockback = 4.5f;
		this.useTime = 26;
		this.speed = 19;
		this.sellPrice = 320;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("iron_axe");
	}

}
