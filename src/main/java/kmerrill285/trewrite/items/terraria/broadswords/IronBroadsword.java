package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.item.Item.Properties;

public class IronBroadsword extends Broadsword {
	public IronBroadsword() {
		super();
		this.damage = 10;
		this.knockback = 5;
		this.useTime = 20;
		this.sellPrice = 360;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("iron_broadsword");
	}
}
