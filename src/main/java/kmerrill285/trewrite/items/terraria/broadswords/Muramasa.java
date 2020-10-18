package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;

public class Muramasa extends Broadsword {

	public Muramasa() {
		super();
		this.damage = 21;
		this.knockback = 2.5f;
		this.useTime = 18;
		this.speed = 18;
		this.sellPrice = 90;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("muramasa");
	}
	
}