package kmerrill285.trewrite.items.terraria.clickable;

import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.client.CPacketAddScore;
import kmerrill285.trewrite.core.network.client.CPacketSyncInventoryTerraria;
import kmerrill285.trewrite.events.ScoreboardEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;

public class Coin extends Clickable {

	private int value;
	public Coin(int value, String name) {
		super(new Properties().group(ItemGroup.MISC), name);
		this.setTooltip("Right click for coins!");
		this.value = value;
		this.setMaxStack(99);
	}

	public Coin(int value, String name, int stack) {
		this(value, name);
		this.setMaxStack(stack);
	}

	@Override
	public void use(PlayerEntity player, InventorySlot slot) {
		NetworkHandler.INSTANCE.sendToServer(new CPacketAddScore(ScoreboardEvents.COINS, value * slot.stack.size));
		slot.decrementStack(slot.stack.size);
		slot.stack = null;
		NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, slot.area, slot.id, slot.stack));
	}

}
