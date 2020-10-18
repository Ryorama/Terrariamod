package kmerrill285.trewrite.items.terraria.accessories;

import kmerrill285.trewrite.items.accessories.Accessory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;

public class BandOfRegeneration extends Accessory {

	private int tick = 0;
	public BandOfRegeneration() {
		super(new Properties().group(ItemGroup.MISC), "band_of_regeneration");
		this.tooltip = "Slowly regenerates life";
		this.setBuySell(10000);
	}
	
	public void accessoryTick(PlayerEntity player) {
		tick++;
		if (tick > 20) {
			tick = 0;
			player.heal(1);
		}
	}

}
