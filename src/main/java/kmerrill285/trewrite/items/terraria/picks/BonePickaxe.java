package kmerrill285.trewrite.items.terraria.picks;

import kmerrill285.trewrite.items.Pickaxe;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.util.ResourceLocation;

public class BonePickaxe extends Pickaxe {

	public BonePickaxe() {
		super();
		this.pick = 50;
		this.damage = 8;
		this.knockback = 3;
		this.useTime = 19;
		this.speed = 11;
		this.sellPrice = 3000;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("bone_pickaxe");
	}

}
