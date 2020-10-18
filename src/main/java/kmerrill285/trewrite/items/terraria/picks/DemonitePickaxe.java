package kmerrill285.trewrite.items.terraria.picks;

import kmerrill285.trewrite.items.Pickaxe;
import kmerrill285.trewrite.util.Conversions;

public class DemonitePickaxe extends Pickaxe {

	public DemonitePickaxe() {
		super();
		this.pick = 65;
		this.damage = 9;
		this.knockback = 3;
		this.useTime = 20;
		this.speed = 15;
		this.sellPrice = 2000;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setTooltip("Can mine Hellstone");
		this.setLocation("demonite_pickaxe");
	}

}
