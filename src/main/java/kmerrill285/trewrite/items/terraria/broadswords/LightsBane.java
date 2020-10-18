package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.item.Item.Properties;

public class LightsBane extends Broadsword {
	public LightsBane() {
		super();
		this.damage = 17;
		this.knockback = 5;
		this.useTime = 20;
		this.sellPrice = 2700;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("lights_bane");
	}
}
