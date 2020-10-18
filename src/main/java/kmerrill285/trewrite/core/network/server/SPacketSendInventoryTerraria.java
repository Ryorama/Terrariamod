package kmerrill285.trewrite.core.network.server;

import java.util.function.Supplier;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SPacketSendInventoryTerraria {
   private String player;
   private String data;

   public SPacketSendInventoryTerraria(String player, String data) {
      this.player = player;
      this.data = data;
   }

   public SPacketSendInventoryTerraria(PacketBuffer buf) {
      this(buf.readString(100).trim(), buf.readString(100).trim());
   }

   public void encode(PacketBuffer buf) {
      buf.writeString(this.player);
      buf.writeString(this.data);
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         Minecraft mc = Minecraft.getInstance();
         if (mc != null && mc.player != null && this.player.contentEquals(mc.player.getScoreboardName())) {
            ContainerTerrariaInventory.inventory.loadFromString(this.data);
            System.out.println("loaded inventory from packet");
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
