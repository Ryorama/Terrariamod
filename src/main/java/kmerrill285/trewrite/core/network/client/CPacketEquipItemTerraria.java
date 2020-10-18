package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.events.WorldEvents;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketEquipItemTerraria {
   private int hotbarId;

   public CPacketEquipItemTerraria(int hotbarId) {
      this.hotbarId = hotbarId;
   }

   public void encode(PacketBuffer buf) {
      buf.writeInt(this.hotbarId);
   }

   public CPacketEquipItemTerraria(PacketBuffer buf) {
      this(buf.readInt());
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity sender = ((Context)ctx.get()).getSender();
         if (sender != null) {
            InventoryTerraria inventory = WorldEvents.getOrLoadInventory(sender);
            if (inventory != null) {
               for(int i = 0; i < 9; ++i) {
                  if (inventory.savedHotbar[i] == null) {
                     inventory.savedHotbar[i] = (ItemStack)sender.inventory.mainInventory.get(i);
                  }

                  sender.inventory.setInventorySlotContents(i, new ItemStack(Items.AIR, 1));
               }

               if (inventory.hotbar[this.hotbarId] != null && inventory.hotbar[this.hotbarId].stack != null) {
                  sender.setHeldItem(Hand.MAIN_HAND, new ItemStack(inventory.hotbar[this.hotbarId].stack.itemForRender.getItem(), 1));
                  inventory.open = true;
                  inventory.hotbarSelected = this.hotbarId;
               }
            }
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
