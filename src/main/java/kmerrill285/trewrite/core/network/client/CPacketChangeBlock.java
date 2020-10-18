package kmerrill285.trewrite.core.network.client;

import java.util.function.Supplier;

import kmerrill285.stackeddimensions.networking.SPacketSendChunk;
import kmerrill285.trewrite.core.network.NetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.network.NetworkEvent.Context;
import net.minecraftforge.fml.network.PacketDistributor;

public class CPacketChangeBlock {
   int x;
   int y;
   int z;
   ResourceLocation dimension;
   int state;
   int height;
   boolean moving;

   public CPacketChangeBlock(int x, int y, int z, ResourceLocation dimension, BlockState block, boolean moving) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.dimension = dimension;
      this.state = Block.getStateId(block);
      this.height = 0;
      this.moving = moving;
   }

   public CPacketChangeBlock(int x, int y, int z, ResourceLocation dimension, BlockState block, int height, boolean moving) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.dimension = dimension;
      this.state = Block.getStateId(block);
      this.height = height;
      this.moving = moving;
   }

   public void encode(PacketBuffer buf) {
      buf.writeInt(this.x);
      buf.writeInt(this.y);
      buf.writeInt(this.z);
      buf.writeResourceLocation(this.dimension);
      buf.writeInt(this.state);
      buf.writeInt(this.height);
      buf.writeBoolean(this.moving);
   }

   public CPacketChangeBlock(PacketBuffer buf) {
      this.x = buf.readInt();
      this.y = buf.readInt();
      this.z = buf.readInt();
      this.dimension = buf.readResourceLocation();
      this.state = buf.readInt();
      this.height = buf.readInt();
      this.moving = buf.readBoolean();
   }

   public void handle(Supplier ctx) {
      ((Context)ctx.get()).enqueueWork(() -> {
         ServerPlayerEntity player = ((Context)ctx.get()).getSender();
         DimensionType t = null;

         if (t != null) {
            DimensionManager.keepLoaded(t, true);
            ServerWorld dim = DimensionManager.getWorld(player.getServer(), t, true, true);
            DimensionManager.keepLoaded(t, true);
            System.out.println("set bloch " + dim.getBlockState(new BlockPos(this.x, this.y, this.z)));
            dim.getBlockState(new BlockPos(this.x, this.y, this.z));
            dim.setBlockState(new BlockPos(this.x, this.y, this.z), Block.getStateById(this.state), this.moving ? 64 : 1);
            Chunk chunk = dim.getChunk(this.x / 16, this.z / 16);
            NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
               return player;
            }), new SPacketSendChunk(chunk, this.x, this.z, false));
         }
      });
      ((Context)ctx.get()).setPacketHandled(true);
   }
}
