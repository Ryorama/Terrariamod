package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;

import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.TerrariaInventoryContainerHandler;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.events.WorldEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import net.minecraftforge.fml.network.NetworkHooks;

public class CPacketOpenInventoryTerraria {
   public String playername;

   public CPacketOpenInventoryTerraria(String playername) {
      this.playername = playername;
   }

   public void encode(PacketBuffer buf) {
      buf.writeString(this.playername);
   }

   public CPacketOpenInventoryTerraria(PacketBuffer buf) {
      this(buf.readString(100).trim());
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity sender = ((Context)ctx.get()).getSender();
         if (sender != null) {
            NetworkHooks.openGui(sender, new TerrariaInventoryContainerHandler());
            Minecraft.getInstance().world.playSound(Minecraft.getInstance().player.getPosition(), SoundsT.MENU_OPEN, SoundCategory.PLAYERS, 500, 1, false);
            InventoryTerraria inventory = WorldEvents.getOrLoadInventory(sender);
            if (inventory != null) {
               inventory.open = true;
            }
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
