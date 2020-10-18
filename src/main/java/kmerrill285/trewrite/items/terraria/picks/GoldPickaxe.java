package kmerrill285.trewrite.items.terraria.picks;

import kmerrill285.trewrite.items.Pickaxe;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.util.ResourceLocation;

public class GoldPickaxe extends Pickaxe {

	public GoldPickaxe() {
		super();
		this.pick = 55;
		this.damage = 6;
		this.knockback = 2;
		this.useTime = 20;
		this.speed = 17;
		this.sellPrice = 2000;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setTooltip("Can mine Meteorite");
		this.setLocation("gold_pickaxe");
	}

}
