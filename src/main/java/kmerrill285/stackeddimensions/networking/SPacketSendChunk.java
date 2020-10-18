package kmerrill285.stackeddimensions.networking;

import java.util.function.Supplier;
import kmerrill285.stackeddimensions.Util;
import kmerrill285.stackeddimensions.render.ChunkEncoder;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SPacketSendChunk {
   public Chunk chunk;
   public int x;
   public int z;
   public PacketBuffer buf;
   public boolean markDirty;

   public SPacketSendChunk(Chunk chunk, int x, int z, boolean markDirty) {
      this.chunk = chunk;
      this.x = x;
      this.z = z;
      this.markDirty = markDirty;
   }

   public void encode(PacketBuffer buf) {
      buf.writeInt(this.x);
      buf.writeInt(this.z);
      buf.writeBoolean(this.markDirty);
      ChunkEncoder.encodeChunk(this.chunk, buf);
   }

   public SPacketSendChunk(PacketBuffer buf) {
      this.x = buf.readInt();
      this.z = buf.readInt();
      this.markDirty = buf.readBoolean();
      this.buf = buf;
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         Util.chunksend.add(this);
      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
