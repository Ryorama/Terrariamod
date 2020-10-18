package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketHeal {
   private float heal;

   public CPacketHeal(float heal) {
      this.heal = heal;
   }

   public void encode(PacketBuffer buf) {
      buf.writeFloat(this.heal);
   }

   public CPacketHeal(PacketBuffer buf) {
      this.heal = buf.readFloat();
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity player = ((Context)ctx.get()).getSender();
         player.heal(this.heal);
      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
