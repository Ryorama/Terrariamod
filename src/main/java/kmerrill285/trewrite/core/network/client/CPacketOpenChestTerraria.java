package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;
import kmerrill285.trewrite.core.inventory.container.TerrariaChestContainerHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketOpenChestTerraria {
   public String playername;

   public CPacketOpenChestTerraria(String playername) {
      this.playername = playername;
   }

   public void encode(PacketBuffer buf) {
      buf.writeString(this.playername);
   }

   public CPacketOpenChestTerraria(PacketBuffer buf) {
      this(buf.readString(100).trim());
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity sender = ((Context)ctx.get()).getSender();
         if (sender != null) {
            NetworkHooks.openGui(sender, new TerrariaChestContainerHandler());
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
