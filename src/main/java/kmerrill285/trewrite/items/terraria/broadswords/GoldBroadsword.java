package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.item.Item.Properties;

public class GoldBroadsword extends Broadsword {
	public GoldBroadsword() {
		super();
		this.damage = 13;
		this.knockback = 5;
		this.useTime = 20;
		this.sellPrice = 1800;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("gold_broadsword");
	}
}
