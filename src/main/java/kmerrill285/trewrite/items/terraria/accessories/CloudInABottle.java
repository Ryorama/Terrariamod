package kmerrill285.trewrite.items.terraria.accessories;

import kmerrill285.trewrite.items.accessories.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;

public class CloudInABottle extends Accessory {

	public CloudInABottle() {
		super(new Properties().group(ItemGroup.MISC), "cloud_in_a_bottle");
		this.tooltip = "Allows the holder to double jump";
		this.setBuySell(10000);
	}
	
	public void accessoryTick(PlayerEntity player) {
		
	}

}
