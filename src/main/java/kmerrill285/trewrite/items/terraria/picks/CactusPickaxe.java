package kmerrill285.trewrite.items.terraria.picks;

import kmerrill285.trewrite.items.Pickaxe;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.util.ResourceLocation;

public class CactusPickaxe extends Pickaxe {

	public CactusPickaxe() {
		super();
		this.pick = 35;
		this.damage = 5;
		this.knockback = 2;
		this.useTime = 23;
		this.speed = 15;
		this.sellPrice = 400;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("cactus_pickaxe");
	}

}
