package kmerrill285.trewrite.items.terraria.picks;

import kmerrill285.trewrite.items.Pickaxe;
import kmerrill285.trewrite.util.Conversions;

public class PallidumPickaxe extends Pickaxe {

	public PallidumPickaxe() {
		super();
		this.pick = 70;
		this.damage = 12;
		this.knockback = 3.5f;
		this.useTime = 22;
		this.speed = 14;
		this.sellPrice = 2000;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("pallidum_pickaxe");
	}

}
