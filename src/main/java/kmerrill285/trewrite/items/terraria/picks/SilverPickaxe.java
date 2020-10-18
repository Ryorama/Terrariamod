package kmerrill285.trewrite.items.terraria.picks;

import kmerrill285.trewrite.items.Pickaxe;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.util.ResourceLocation;

public class SilverPickaxe extends Pickaxe {

	public SilverPickaxe() {
		super();
		this.pick = 45;
		this.damage = 6;
		this.knockback = 2;
		this.useTime = 19;
		this.speed = 11;
		this.sellPrice = 10000;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("silver_pickaxe");
	}

}
