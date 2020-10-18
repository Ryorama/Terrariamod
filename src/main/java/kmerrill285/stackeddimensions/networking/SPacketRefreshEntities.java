package kmerrill285.stackeddimensions.networking;

import java.util.function.Supplier;
import kmerrill285.stackeddimensions.StackedDimensions;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SPacketRefreshEntities {
   public SPacketRefreshEntities() {
   }

   public void encode(PacketBuffer buf) {
   }

   public SPacketRefreshEntities(PacketBuffer buf) {
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         if (StackedDimensions.renderWorld != null) {
            StackedDimensions.refreshEntities = true;
            StackedDimensions.renderEntities.clear();
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
