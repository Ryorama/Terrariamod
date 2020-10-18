package kmerrill285.trewrite.items.terraria.accessories;

import kmerrill285.trewrite.items.accessories.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;

public class BandOfStarpower extends Accessory {

	public BandOfStarpower() {
		super(new Properties().group(ItemGroup.MISC), "band_of_starpower");
		this.tooltip = "Increases maximum mana by 20";
		this.setBuySell(10000);
		this.extraMana = 20;
	}
	
	public void accessoryTick(PlayerEntity player) {
		
	}

}
