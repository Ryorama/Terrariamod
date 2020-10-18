package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;
import kmerrill285.trewrite.core.inventory.InventoryChestTerraria;
import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketSyncInventoryChest {
   private int entityId;
   private int slotId;
   private ItemStackT stack;
   private int inventoryArea;
   private String pos;

   public CPacketSyncInventoryChest(int entityId, int inventoryArea, int slotId, ItemStackT stack, String position) {
      this.entityId = entityId;
      this.slotId = slotId;
      this.stack = stack;
      this.inventoryArea = inventoryArea;
      if (this.stack == null) {
         this.stack = new ItemStackT(ItemsT.DIRT_BLOCK, -1, (ItemModifier)null);
      }

      this.pos = position;
   }

   public void encode(PacketBuffer buf) {
      buf.writeInt(this.entityId);
      buf.writeInt(this.inventoryArea);
      buf.writeInt(this.slotId);
      buf.writeString(ItemsT.getStringForItem(this.stack.item));
      buf.writeInt(this.stack.size);
      buf.writeInt(this.stack.modifier);
      buf.writeString(this.pos);
   }

   public CPacketSyncInventoryChest(PacketBuffer buf) {
      this(buf.readInt(), buf.readInt(), buf.readInt(), new ItemStackT(ItemsT.getItemFromString(buf.readString(100).trim()), buf.readInt(), ItemModifier.getModifier(buf.readInt())), buf.readString(100).trim());
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity sender = ((Context)ctx.get()).getSender();
         if (sender != null) {
            int i = this.slotId;
            ItemStackT stack = this.stack;
            if (stack.size < 0) {
               stack = null;
            }

            String pos = this.pos;
            InventoryChestTerraria inventory = (InventoryChestTerraria)WorldStateHolder.get(sender.world).chests.get(pos);
            InventoryTerraria inventoryPlayer = WorldEvents.getOrLoadInventory(sender);
            if (inventory == null || inventoryPlayer == null) {
               return;
            }

            System.out.println("INVENTORY CHEST EXISTS.  SYNCING FROM CLIENT TO SERVER");
            if (this.inventoryArea == 2) {
               inventory.trash.stack = stack;
               inventoryPlayer.trash.stack = stack;
            } else {
               InventorySlot[] slots = null;
               int area = this.inventoryArea;
               if (area == 0) {
                  slots = inventoryPlayer.main;
               }

               if (area == 1) {
                  slots = inventoryPlayer.hotbar;
               }

               if (area == 3) {
                  slots = inventory.chest;
               }

               if (slots[i] != null) {
                  slots[i].stack = stack;
               }
            }
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
