package kmerrill285.trewrite.items.terraria.picks;

import kmerrill285.trewrite.items.Pickaxe;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.util.ResourceLocation;

public class IronPickaxe extends Pickaxe {

	public IronPickaxe() {
		super();
		this.pick = 40;
		this.damage = 5;
		this.knockback = 2;
		this.useTime = 19;
		this.speed = 13;
		this.sellPrice = 400;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("iron_pickaxe");
	}

}
