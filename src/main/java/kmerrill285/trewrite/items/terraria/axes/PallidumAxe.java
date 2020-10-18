package kmerrill285.trewrite.items.terraria.axes;

import kmerrill285.trewrite.items.Axe;
import kmerrill285.trewrite.util.Conversions;

public class PallidumAxe extends Axe {

	public PallidumAxe() {
		super();
		this.axe = 75;
		this.damage = 36;
		this.knockback = 5.5f;
		this.useTime = 35;
		this.speed = 12;
		this.sellPrice = 1600;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("pallidum_axe");
	}

}
