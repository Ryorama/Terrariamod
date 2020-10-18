package kmerrill285.stackeddimensions.networking;

import java.util.function.Supplier;
import kmerrill285.stackeddimensions.StackedDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class SPacketSendRenderBlock {
   public BlockPos pos;
   public BlockState state;

   public SPacketSendRenderBlock(BlockPos pos, BlockState state) {
      this.pos = pos;
      this.state = state;
   }

   public void encode(PacketBuffer buf) {
      buf.writeBlockPos(this.pos);
      buf.writeInt(Block.getStateId(this.state));
   }

   public SPacketSendRenderBlock(PacketBuffer buf) {
      this.pos = buf.readBlockPos();
      this.state = Block.getStateById(buf.readInt());
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         if (StackedDimensions.renderWorld != null && this.pos != null && this.state != null && StackedDimensions.renderWorld.chunkExists(this.pos.getX() / 16, this.pos.getZ() / 16)) {
            StackedDimensions.renderWorld.setBlockState(this.pos, this.state);
         }

      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
