package kmerrill285.stackeddimensions.networking;

import java.util.function.Supplier;
import kmerrill285.stackeddimensions.Util;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class CPacketRequestChunks {
   int x;
   int z;
   ResourceLocation dimension;

   public CPacketRequestChunks(int x, int z, ResourceLocation dimension) {
      this.x = x;
      this.z = z;
      this.dimension = dimension;
   }

   public void encode(PacketBuffer buf) {
      buf.writeInt(this.x);
      buf.writeInt(this.z);
      buf.writeResourceLocation(this.dimension);
   }

   public CPacketRequestChunks(PacketBuffer buf) {
      this.x = buf.readInt();
      this.z = buf.readInt();
      this.dimension = buf.readResourceLocation();
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity player = ((Context)ctx.get()).getSender();
         DimensionType t = Util.getDimension(this.dimension);
         if (t != null) {
            DimensionManager.keepLoaded(t, true);
            ServerWorld dim = DimensionManager.getWorld(player.getServer(), t, true, true);
            DimensionManager.keepLoaded(t, true);
            Chunk chunk = dim.getChunk(this.x, this.z);
            NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
               return player;
            }), new SPacketSendChunk(chunk, this.x, this.z, false));
         }
      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
