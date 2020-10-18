package kmerrill285.trewrite.items.terraria.picks;

import kmerrill285.trewrite.items.Pickaxe;
import kmerrill285.trewrite.util.Conversions;

public class CobaltPickaxe extends Pickaxe {

	public CobaltPickaxe() {
		super();
		this.pick = 110;
		this.damage = 10;
		this.knockback = 5;
		this.useTime = 25;
		this.speed = 13;
		this.sellPrice = 400;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("cobalt_pickaxe");
	}
}
