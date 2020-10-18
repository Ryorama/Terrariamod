package kmerrill285.stackeddimensions.networking;

import java.util.function.Supplier;
import kmerrill285.stackeddimensions.Util;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SPacketRefreshDimensionRenderer {
   public SPacketRefreshDimensionRenderer() {
   }

   public void encode(PacketBuffer buf) {
   }

   public SPacketRefreshDimensionRenderer(PacketBuffer buf) {
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         Util.refreshDimensionRenderer = true;
      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
