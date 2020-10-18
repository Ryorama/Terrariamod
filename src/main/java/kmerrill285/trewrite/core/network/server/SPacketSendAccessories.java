package kmerrill285.trewrite.core.network.server;

import java.util.ArrayList;
import java.util.function.Supplier;
import kmerrill285.trewrite.core.client.ClientProxy;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.events.WorldEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SPacketSendAccessories {
   public String player;
   public PlayerEntity p;
   public ArrayList items = new ArrayList();

   public SPacketSendAccessories(PlayerEntity player) {
      this.player = player.getScoreboardName();
      this.p = player;
   }

   public void encode(PacketBuffer buf) {
      buf.writeString(this.player);
      InventoryTerraria inventory = WorldEvents.getOrLoadInventory(this.p);
      if (inventory != null) {
         for(int i = 0; i < inventory.accessory.length; ++i) {
            if (inventory.accessoryVanity[i].stack != null) {
               buf.writeItemStack(inventory.accessoryVanity[i].stack.itemForRender);
            } else if (inventory.accessory[i].stack != null) {
               buf.writeItemStack(inventory.accessory[i].stack.itemForRender);
            }
         }
      }

   }

   public SPacketSendAccessories(PacketBuffer buf) {
      this.player = buf.readString(100).trim();

      while(buf.isReadable()) {
         this.items.add(buf.readItemStack());
      }

   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ClientProxy.playerAccessories.put(this.player, this.items);
      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
