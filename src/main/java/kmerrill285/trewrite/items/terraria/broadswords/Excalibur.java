package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;

public class Excalibur extends Broadsword {

	public Excalibur() {
		super();
		this.damage = 66;
		this.knockback = 4.5f;
		this.useTime = 20;
		this.speed = 20;
		this.sellPrice = 90;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("excalibur");
	}
	
}