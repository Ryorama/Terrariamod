package com.ryorama.terrariamod.core.network.server;

import java.util.function.Supplier;

import com.ryorama.terrariamod.core.inventory.InventoryTerraria;
import com.ryorama.terrariamod.core.inventory.container.ContainerTerrariaInventory;
import com.ryorama.terrariamod.core.items.ItemStackT;
import com.ryorama.terrariamod.core.items.modifiers.ItemModifier;
import com.ryorama.terrariamod.items.ItemsT;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SPacketSyncInventoryTerraria {

	   private int entityId;
	   private int slotId;
	   private ItemStackT stack;
	   private int inventoryArea;

	   public SPacketSyncInventoryTerraria(int entityId, int inventoryArea, int slotId, ItemStackT stack) {
	      this.entityId = entityId;
	      this.slotId = slotId;
	      this.stack = stack;
	      this.inventoryArea = inventoryArea;
	   }

	   public static void encode(SPacketSyncInventoryTerraria msg, PacketBuffer buf) {
	      buf.writeInt(msg.entityId);
	      buf.writeInt(msg.inventoryArea);
	      buf.writeInt(msg.slotId);
	      if (msg.stack != null) {
	         buf.writeString(ItemsT.getStringForItem(msg.stack.item));
	         buf.writeInt(msg.stack.size);
	         buf.writeInt(msg.stack.modifier);
	      } else {
	         buf.writeString("dirt_block");
	         buf.writeInt(-1);
	         buf.writeInt(-1);
	      }

	   }

	   public static SPacketSyncInventoryTerraria decode(PacketBuffer buf) {
	      int a = buf.readInt();
	      int b = buf.readInt();
	      int c = buf.readInt();
	      String item = buf.readString(100).trim();
	      return new SPacketSyncInventoryTerraria(a, b, c, new ItemStackT(ItemsT.getItemFromString(item), buf.readInt(), ItemModifier.getModifier(buf.readInt())));
	   }

	   public static void handle(SPacketSyncInventoryTerraria msg, Supplier ctx) {
	      ((Context)ctx.get()).enqueueWork(() -> {
	         int i = msg.slotId;
	         ItemStackT stack = msg.stack;
	         if (stack != null && stack.size < 0) {
	            stack = null;
	         }

	         int invArea = msg.inventoryArea;
	         InventoryTerraria inventory = ContainerTerrariaInventory.inventory;
	         switch(invArea) {
	         case 0:
	            inventory.main[i].stack = stack;
	            break;
	         case 1:
	            inventory.hotbar[i].stack = stack;
	            break;
	         case 2:
	            inventory.armor[i].stack = stack;
	            break;
	         case 3:
	            inventory.armorVanity[i].stack = stack;
	            break;
	         case 4:
	            inventory.armorDyes[i].stack = stack;
	            break;
	         case 5:
	            inventory.accessory[i].stack = stack;
	            break;
	         case 6:
	            inventory.accessoryVanity[i].stack = stack;
	            break;
	         case 7:
	            inventory.accessoryDyes[i].stack = stack;
	            break;
	         case 8:
	            inventory.trash.stack = stack;
	         }

	      });
	      ((Context)ctx.get()).setPacketHandled(true);
	   }
	}