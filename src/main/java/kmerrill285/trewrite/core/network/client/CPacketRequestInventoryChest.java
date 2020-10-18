package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;
import kmerrill285.trewrite.core.inventory.InventoryChestTerraria;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.server.SPacketSyncInventoryChest;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketRequestInventoryChest {
   public String playername;
   public String worldfile;
   public String position;

   public CPacketRequestInventoryChest(String playername, String worldFile, String position) {
      this.playername = playername;
      this.worldfile = worldFile;
      this.position = position;
   }

   public void encode(PacketBuffer buf) {
      buf.writeString(this.playername);
      buf.writeString(this.worldfile);
      buf.writeString(this.position);
   }

   public CPacketRequestInventoryChest(PacketBuffer buf) {
      this(buf.readString(100).trim(), buf.readString(100).trim(), buf.readString(100).trim());
      System.out.println("DECODING INVENTORY REQUEST");
   }

   public void handle(Supplier ctx) {
      System.out.println("HANDLING INVENTORY REQUEST");
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity sender = ((Context)ctx.get()).getSender();
         this.worldfile = sender.getEntityWorld().getWorldInfo().getWorldName();
         if (sender != null) {
            System.out.println("INVENTORY PACKET HAS VALID SENDER");
            System.out.println("sender: " + sender);
            System.out.println("Loading and sending over an inventory.");
            InventoryTerraria inventoryPlayer = WorldEvents.getOrLoadInventory(sender);
            InventoryChestTerraria inventory = (InventoryChestTerraria)WorldStateHolder.get(sender.world).chests.get(this.position);
            if (inventory == null) {
               System.out.println("NuLLLLL");
               inventory = new InventoryChestTerraria(false);
            }

            WorldStateHolder.get(sender.world).markDirty();
            inventory.player = sender;
            this.sendInventoryData(this, ctx, sender, inventory, inventoryPlayer);
            inventory.canSave = true;
            WorldStateHolder.get(sender.world).chests.put(this.position, inventory);
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }

   private void sendInventoryData(CPacketRequestInventoryChest msg, Supplier ctx, ServerPlayerEntity sender, InventoryChestTerraria inventory, InventoryTerraria inventoryPlayer) {
      int i;
      for(i = 0; i < 30; ++i) {
         NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            return sender;
         }), new SPacketSyncInventoryChest(0, i, inventoryPlayer.main[i].stack));
      }

      for(i = 0; i < 10; ++i) {
         NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            return sender;
         }), new SPacketSyncInventoryChest(1, i, inventoryPlayer.hotbar[i].stack));
      }

      NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
         return sender;
      }), new SPacketSyncInventoryChest(2, 0, inventoryPlayer.trash.stack));

      for(i = 0; i < 30; ++i) {
         NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            return sender;
         }), new SPacketSyncInventoryChest(3, i, inventory.chest[i].stack));
      }

   }
}
