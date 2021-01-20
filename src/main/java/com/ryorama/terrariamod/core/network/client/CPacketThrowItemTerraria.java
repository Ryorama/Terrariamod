package com.ryorama.terrariamod.core.network.client;

import java.util.function.Supplier;

import com.ryorama.terrariamod.core.inventory.InventorySlot;
import com.ryorama.terrariamod.core.inventory.InventoryTerraria;
import com.ryorama.terrariamod.core.items.ItemStackT;
import com.ryorama.terrariamod.core.items.modifiers.ItemModifier;
import com.ryorama.terrariamod.core.network.NetworkHandler;
import com.ryorama.terrariamod.core.network.server.SPacketSyncInventoryTerraria;
import com.ryorama.terrariamod.entities.EntityItemT;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.world.WorldEvents;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import net.minecraftforge.fml.network.PacketDistributor;

public class CPacketThrowItemTerraria {


	private int entityId;
	   private int slotId;
	   private ItemStackT stack;
	   private int inventoryArea;
	   private int delay;

	   public CPacketThrowItemTerraria(int entityId, int inventoryArea, int slotId, ItemStackT stack) {
	      this.entityId = entityId;
	      this.slotId = slotId;
	      this.stack = stack;
	      this.inventoryArea = inventoryArea;
	      if (this.stack == null) {
	         this.stack = new ItemStackT(ItemsT.DIRT_BLOCK, -1, (ItemModifier)null);
	      }

	      this.delay = 80;
	   }

	   public CPacketThrowItemTerraria(int entityId, int inventoryArea, int slotId, ItemStackT stack, int delay) {
	      this.entityId = entityId;
	      this.slotId = slotId;
	      this.stack = stack;
	      this.inventoryArea = inventoryArea;
	      if (this.stack == null) {
	         this.stack = new ItemStackT(ItemsT.DIRT_BLOCK, -1, (ItemModifier)null);
	      }

	      this.delay = delay;
	   }

	   public void encode(PacketBuffer buf) {
	      buf.writeInt(this.entityId);
	      buf.writeInt(this.inventoryArea);
	      buf.writeInt(this.slotId);
	      buf.writeString(ItemsT.getStringForItem(this.stack.item));
	      buf.writeInt(this.stack.size);
	      buf.writeInt(this.stack.modifier);
	      buf.writeInt(this.delay);
	   }

	   public CPacketThrowItemTerraria(PacketBuffer buf) {
	      this(buf.readInt(), buf.readInt(), buf.readInt(), new ItemStackT(ItemsT.getItemFromString(buf.readString(100).trim()), buf.readInt(), ItemModifier.getModifier(buf.readInt())), buf.readInt());
	   }

	   public void handle(Supplier ctx) {
	      System.out.println("handle drop item packet");
	      ((Context)ctx.get()).enqueueWork(() -> {
	         ServerPlayerEntity sender = ((Context)ctx.get()).getSender();
	         if (sender != null) {
	            System.out.println("Sender is not null");
	            int i = this.slotId;
	            ItemStackT stack = this.stack;
	            if (stack.size < 0) {
	               stack = null;
	            }

	            InventoryTerraria inventory = WorldEvents.getOrLoadInventory(sender);
	            if (inventory == null) {
	               return;
	            }

	            System.out.println("Inventory is not null");
	            if (this.inventoryArea == 8) {
	               System.out.println("trash");
	               inventory.trash.stack = stack;
	            } else {
	               System.out.println("start drop");
	               InventorySlot[] slots = null;
	               int area = this.inventoryArea;
	               if (area == 0) {
	                  slots = inventory.main;
	               }

	               if (area == 1) {
	                  slots = inventory.hotbar;
	               }

	               if (area == 2) {
	                  slots = inventory.armor;
	               }

	               if (area == 3) {
	                  slots = inventory.armorVanity;
	               }

	               if (area == 4) {
	                  slots = inventory.armorDyes;
	               }

	               if (area == 5) {
	                  slots = inventory.accessory;
	               }

	               if (area == 6) {
	                  slots = inventory.accessoryVanity;
	               }

	               if (area == 7) {
	                  slots = inventory.accessoryDyes;
	               }

	               ItemStackT s;
	               if (area == -1) {
	                  s = new ItemStackT(this.stack.item, this.stack.size, ItemModifier.getModifier(this.stack.modifier));
	                  if (Minecraft.getInstance().world != null) {
	      				
	  					ItemEntity ie = new ItemEntity(Minecraft.getInstance().world, Minecraft.getInstance().player.getPosX(), Minecraft.getInstance().player.getPosY(), Minecraft.getInstance().player.getPosZ(), new ItemStack(s.item));
	  					Minecraft.getInstance().world.addEntity(ie);
	  					ie.setPosition(Minecraft.getInstance().player.getPosX(), Minecraft.getInstance().player.getPosY(), Minecraft.getInstance().player.getPosZ());
	  					
	  				  }
	                  System.out.println("drop item");
	               } else if (slots[i] != null && slots[i].stack != null) {
	                  s = new ItemStackT(slots[i].stack.item, slots[i].stack.size, ItemModifier.getModifier(slots[i].stack.modifier));
	                  slots[i].stack = null;
	                  NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
	                     return sender;
	                  }), new SPacketSyncInventoryTerraria(0, area, i, slots[i].stack));
	                  if (Minecraft.getInstance().world != null) {
		      				
		  					ItemEntity ie = new ItemEntity(Minecraft.getInstance().world, Minecraft.getInstance().player.getPosX(), Minecraft.getInstance().player.getPosY(), Minecraft.getInstance().player.getPosZ(), new ItemStack(s.item));
		  					Minecraft.getInstance().world.addEntity(ie);
		  					ie.setPosition(Minecraft.getInstance().player.getPosX(), Minecraft.getInstance().player.getPosY(), Minecraft.getInstance().player.getPosZ());
		  					
		  			  }	                  
	                  System.out.println("drop item");
	               }
	            }
	         }

	      });
	      ((Context)ctx.get()).setPacketHandled(true);
	   }
	}