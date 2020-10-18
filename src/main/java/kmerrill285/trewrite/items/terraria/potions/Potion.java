package kmerrill285.trewrite.items.terraria.potions;

import java.util.ArrayList;

import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.client.CPacketSyncInventoryTerraria;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.ItemT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public abstract class Potion extends ItemT {
	
	private boolean autoBuff;
	
	public static ArrayList<Potion> buffs = new ArrayList<Potion>();
	
	public Potion(Properties properties, String name, boolean consumable,  boolean autoBuff) {
		super(properties, name);
		if (consumable) this.setConsumable();
		this.autoBuff = autoBuff;
		if (autoBuff) {
			buffs.add(this);
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
		ItemStack itemstack = player.getHeldItem(handIn);
		  player.getCooldownTracker().setCooldown(this, 20);

		  doPotionStuff(worldIn, player);
		  InventoryTerraria inv = null;
		    if (!worldIn.isRemote()) {
				inv = WorldEvents.getOrLoadInventory(player);
			} else {
				inv = ContainerTerrariaInventory.inventory;
			}
		    InventorySlot s = inv.hotbar[inv.hotbarSelected];
		    if (s != null) {
		    	if (s.stack != null) {
		    		if (s.stack.item == this) {
		    			s.decrementStack(1);
		    		}
		    	}
		    }
		return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	}
	
	public void buff(World worldIn, PlayerEntity player, boolean needsAutoBuff) {
		if (autoBuff || !needsAutoBuff) {
			InventoryTerraria inv = null;
		    if (!worldIn.isRemote()) {
				inv = WorldEvents.getOrLoadInventory(player);
			} else {
				inv = ContainerTerrariaInventory.inventory;
			}
		    InventorySlot slot = null;
		    for (int i = 0; i < inv.hotbar.length; i++) {
		    	if (inv.hotbar[i].stack != null) {
		    		if (inv.hotbar[i].stack.item == this) {
		    			slot = inv.hotbar[i];
		    		}
		    	}
		    }
		    if (slot == null) {
		    	for (int i = 0; i < inv.main.length; i++) {
			    	if (inv.main[i].stack != null) {
			    		if (inv.main[i].stack.item == this) {
			    			slot = inv.main[i];
			    		}
			    	}
			    }
		    }
		    
		    if (slot != null) {
		    	if (doPotionStuff(worldIn, player)) {
		    		slot.decrementStack(1);
		    		if (worldIn.isRemote) {
		    			NetworkHandler.INSTANCE.sendToServer(new CPacketSyncInventoryTerraria(0, slot.area, slot.id, slot.stack));
		    		}
		    	}
		    }
		}
		
	}
	
	protected abstract boolean doPotionStuff(World world, PlayerEntity player);
}
