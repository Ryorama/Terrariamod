package kmerrill285.trewrite.items.terraria.accessories;

import kmerrill285.trewrite.items.accessories.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;

public class LuckyHorseshoe extends Accessory {

	public LuckyHorseshoe() {
		super(new Properties().group(ItemGroup.MISC), "lucky_horseshoe");
		this.tooltip = "Negates fall damage";
		this.setBuySell(10000);
	}
	
	public void accessoryTick(PlayerEntity player) {
		player.fallDistance = 0;
	}

}
