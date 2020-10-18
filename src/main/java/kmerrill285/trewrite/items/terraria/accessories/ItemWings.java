package kmerrill285.trewrite.items.terraria.accessories;

import kmerrill285.trewrite.items.accessories.Accessory;
import net.minecraft.entity.player.PlayerEntity;

public class ItemWings extends Accessory {

	public ItemWings(Properties properties, String name) {
		super(properties, name);
	}

	public void accessoryTick(PlayerEntity player) {
		player.abilities.allowFlying = true;
		player.sendPlayerAbilities();
	}
	
}
