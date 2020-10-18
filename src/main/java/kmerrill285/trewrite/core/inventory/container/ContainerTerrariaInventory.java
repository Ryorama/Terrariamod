package kmerrill285.trewrite.core.inventory.container;

import javax.annotation.Nullable;

import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.client.CPacketThrowItemTerraria;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;

public class ContainerTerrariaInventory extends Container {

	private PlayerEntity player;
    private boolean isLocalWorld;
	
    public static InventoryTerraria inventory;
    
//    public ContainerTerrariaInventory(ClientPlayerEntity entity) {
//    	super(Containers.INVENTORY, 0);
//    	this.player = entity;
//    	this.isLocalWorld = entity.world.isRemote;
//    }
    @Nullable
    public ContainerTerrariaInventory (int windowId, PlayerInventory inv) {
    	super(Containers.INVENTORY, windowId);
    }
    @Nullable
    public ContainerTerrariaInventory(int windowId, PlayerInventory inv, PacketBuffer extraData) {
    	super(Containers.INVENTORY, windowId);
    	this.player = inv.player;
    	this.isLocalWorld = inv.player.world.isRemote;
    }
    
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}

	@Override
    public void onContainerClosed(PlayerEntity playerIn) {
		isLocalWorld = playerIn.getEntityWorld().isRemote;
		if (isLocalWorld)
		if (inventory.holdingSlot != null) {
			if (inventory.holdingSlot.stack != null) {
        		NetworkHandler.INSTANCE.sendToServer(new CPacketThrowItemTerraria(0, -1, 0, new ItemStackT(inventory.holdingSlot.stack.item, inventory.holdingSlot.stack.size, ItemModifier.getModifier(inventory.holdingSlot.stack.modifier))));
//    			EntityItemT item = (EntityItemT) EntitiesT.ITEM.spawn(playerIn.world, null, null, playerIn.getPosition().up(), SpawnReason.EVENT, false, false);
				EntityItemT.spawnItem(playerIn.world, playerIn.getPosition().up(), new ItemStackT(inventory.holdingSlot.stack.item, inventory.holdingSlot.stack.size, ItemModifier.getModifier(inventory.holdingSlot.stack.modifier)), 20 * 4);

//            	item.item = inventory.holdingSlot.stack.item.itemName;
//				item.stack = inventory.holdingSlot.stack.size;
//				item.pickupDelay = 20 * 4;
			}
		}
	}
	
	static {
		if (ContainerTerrariaInventory.inventory == null)
		ContainerTerrariaInventory.inventory = new InventoryTerraria();
	}
}
