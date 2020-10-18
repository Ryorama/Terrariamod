package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.events.WorldEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketCloseInventoryTerraria {
   public CPacketCloseInventoryTerraria() {
   }

   public void encode(PacketBuffer buf) {
   }

   public CPacketCloseInventoryTerraria(PacketBuffer buf) {
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity sender = ((Context)ctx.get()).getSender();
         if (sender != null) {
            InventoryTerraria inventory = WorldEvents.getOrLoadInventory(sender);
            if (inventory != null) {
               for(int i = 0; i < 9; ++i) {
                  if (inventory.savedHotbar[i] != null) {
                     sender.inventory.setInventorySlotContents(i, new ItemStack(inventory.savedHotbar[i].getItem(), inventory.savedHotbar[i].getCount()));
                     inventory.savedHotbar[i] = null;
                  }
               }
               inventory.open = false;
            }
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
