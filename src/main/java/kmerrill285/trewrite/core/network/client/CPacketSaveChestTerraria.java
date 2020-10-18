package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketSaveChestTerraria {
   private String pos;

   public CPacketSaveChestTerraria(String position) {
      this.pos = position;
   }

   public void encode(PacketBuffer buf) {
      buf.writeString(this.pos);
   }

   public CPacketSaveChestTerraria(PacketBuffer buf) {
      this(buf.readString(100).trim());
   }

   public void handle(Supplier ctx) {
      System.out.println("handle drop item packet");
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity sender = ((Context)ctx.get()).getSender();
         if (sender != null) {
            String var3 = this.pos;
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
