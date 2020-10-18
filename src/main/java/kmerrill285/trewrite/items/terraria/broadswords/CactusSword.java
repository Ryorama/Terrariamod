package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.item.Item.Properties;

public class CactusSword extends Broadsword {
	public CactusSword() {
		super();
		this.damage = 9;
		this.knockback = 5;
		this.useTime = 25;
		this.sellPrice = 360;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("cactus_sword");
	}
}
