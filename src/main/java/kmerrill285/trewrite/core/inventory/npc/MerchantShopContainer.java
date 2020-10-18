package kmerrill285.trewrite.core.inventory.npc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

public class MerchantShopContainer extends Container {

	protected MerchantShopContainer(ContainerType<?> type, int id) {
		super(type, id);
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
}
