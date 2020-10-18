package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.item.Item.Properties;

public class SilverBroadsword extends Broadsword {
	public SilverBroadsword() {
		super();
		this.damage = 11;
		this.knockback = 5;
		this.useTime = 21;
		this.sellPrice = 900;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("silver_broadsword");
	}
}
