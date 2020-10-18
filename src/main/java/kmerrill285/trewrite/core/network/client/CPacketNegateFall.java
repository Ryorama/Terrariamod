package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketNegateFall {
   public CPacketNegateFall() {
   }

   public void encode(PacketBuffer buf) {
   }

   public CPacketNegateFall(PacketBuffer buf) {
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity sender = ((Context)ctx.get()).getSender();
         if (sender != null) {
            sender.fallDistance = 0.0F;
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
