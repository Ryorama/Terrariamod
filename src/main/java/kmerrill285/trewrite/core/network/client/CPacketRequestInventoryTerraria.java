package kmerrill285.trewrite.core.network.client;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Supplier;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.server.SPacketSyncInventoryTerraria;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketRequestInventoryTerraria {
   public String playername;
   public String worldfile;

   public CPacketRequestInventoryTerraria(String playername, String worldFile) {
      this.playername = playername;
      this.worldfile = worldFile;
   }

   public static void encode(CPacketRequestInventoryTerraria msg, PacketBuffer buf) {
      buf.writeString(msg.playername);
      buf.writeString(msg.worldfile);
   }

   public static CPacketRequestInventoryTerraria decode(PacketBuffer buf) {
      System.out.println("DECODING INVENTORY REQUEST");
      return new CPacketRequestInventoryTerraria(buf.readString(100).trim(), buf.readString(100).trim());
   }

   public static void handle(CPacketRequestInventoryTerraria msg, Supplier ctx) {
      System.out.println("HANDLING INVENTORY REQUEST");
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity sender = ((Context)ctx.get()).getSender();
         if (sender != null) {
            System.out.println("INVENTORY PACKET HAS VALID SENDER");
            (new Thread() {
               public void run() {
                  ServerPlayerEntity sender = ((Context)ctx.get()).getSender();
                  System.out.println("sender: " + sender);
                  Set players = WorldStateHolder.get(sender.world).inventories.keySet();
                  boolean hasPlayer = false;
                  Iterator var4 = players.iterator();

                  while(var4.hasNext()) {
                     String player = (String)var4.next();
                     if (player.equals(msg.playername)) {
                        hasPlayer = true;
                        break;
                     }
                  }

                  System.out.println("Loading and sending over an inventory.");
                  InventoryTerraria inventory = WorldEvents.getOrLoadInventory(sender);
                  inventory.player = sender;
                  CPacketRequestInventoryTerraria.sendInventoryData(msg, ctx, sender, inventory);
                  inventory.canSave = true;
                  System.out.println("UNLOCKING INVENTORIES");
                  System.out.println(WorldStateHolder.get(sender.world).inventories);
                  System.out.println("PUT PLAYER INTO INVENTORY LIST");
                  WorldStateHolder.get(sender.world).inventories.put(msg.playername, inventory);
               }
            }).start();
         }

      });
   }

   private static void sendInventoryData(CPacketRequestInventoryTerraria msg, Supplier ctx, ServerPlayerEntity sender, InventoryTerraria inventory) {
      System.out.println("SEND INVENTORY DATA: " + msg + ", " + ctx + ", " + sender + ", " + inventory);

      int i;
      for(i = 0; i < 30; ++i) {
         NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            return sender;
         }), new SPacketSyncInventoryTerraria(0, 0, i, inventory.main[i].stack));
      }

      for(i = 0; i < 10; ++i) {
         NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            return sender;
         }), new SPacketSyncInventoryTerraria(0, 1, i, inventory.hotbar[i].stack));
      }

      for(i = 0; i < 3; ++i) {
         NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            return sender;
         }), new SPacketSyncInventoryTerraria(0, 2, i, inventory.armor[i].stack));
         NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            return sender;
         }), new SPacketSyncInventoryTerraria(0, 3, i, inventory.armorVanity[i].stack));
         NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            return sender;
         }), new SPacketSyncInventoryTerraria(0, 4, i, inventory.armorDyes[i].stack));
      }

      for(i = 0; i < 5; ++i) {
         NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            return sender;
         }), new SPacketSyncInventoryTerraria(0, 5, i, inventory.accessory[i].stack));
         NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            return sender;
         }), new SPacketSyncInventoryTerraria(0, 6, i, inventory.accessoryVanity[i].stack));
         NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            return sender;
         }), new SPacketSyncInventoryTerraria(0, 7, i, inventory.accessoryDyes[i].stack));
      }

      NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
         return sender;
      }), new SPacketSyncInventoryTerraria(0, 8, 0, inventory.trash.stack));
   }
}
