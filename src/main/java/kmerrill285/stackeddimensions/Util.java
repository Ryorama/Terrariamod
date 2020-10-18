package kmerrill285.stackeddimensions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import kmerrill285.stackeddimensions.blocks.BlockRegistry;
import kmerrill285.stackeddimensions.networking.NetworkHandler;
import kmerrill285.stackeddimensions.networking.SPacketRefreshDimensionRenderer;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.network.PacketDistributor;

public class Util {
   public static boolean refreshDimensionRenderer;
   public static ArrayList chunksend = new ArrayList();
   public static RayTraceResult blockHit;

   public static void makeFieldAccessible(Field field) throws Exception {
      Field modifiers = Field.class.getDeclaredField("modifiers");
      modifiers.setAccessible(true);

      try {
         modifiers.setInt(field, field.getModifiers() & -17);
         modifiers.setInt(field, field.getModifiers() & -5);
         modifiers.setInt(field, field.getModifiers() | 1);
      } catch (IllegalArgumentException var3) {
         var3.printStackTrace();
      } catch (IllegalAccessException var4) {
         var4.printStackTrace();
      }

   }

   public static DimensionType getDimension(ResourceLocation location) {
      return DimensionManager.getRegistry().getValue(location).orElse(null);
   }

   public static EntityType<?> getEntity(ResourceLocation location) {
      return Registry.ENTITY_TYPE.getValue(location).orElse(null);
   }

   public static void teleportPlayer(ServerPlayerEntity player, DimensionType destinationType, BlockPos destinationPos) {
      double iposX = player.posX + 0.0D;
      double iposZ = player.posZ + 0.0D;
      ServerWorld nextWorld = player.getServer().getWorld(destinationType);
      nextWorld.getChunk(destinationPos);
      NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
         return player;
      }), new SPacketRefreshDimensionRenderer());
      player.teleport(nextWorld, destinationPos.getX(),destinationPos.getY(), destinationPos.getZ(), player.rotationYaw, player.rotationPitch);
      player.setPositionAndUpdate(destinationPos.getX(), destinationPos.getY(), destinationPos.getZ());
      if (player.getPosition().getY() == 0) {
         player.setPositionAndUpdate(destinationPos.getX(), (destinationPos.getY() + 1), destinationPos.getZ());
      }

      BlockPos pos2 = new BlockPos(destinationPos.getX(), 0, destinationPos.getZ());
      if (nextWorld.getBlockState(pos2) != null) {
         if (nextWorld.getBlockState(pos2).getBlock() == Blocks.AIR || nextWorld.getBlockState(pos2).getBlock() == Blocks.CAVE_AIR) {
            nextWorld.setBlockState(pos2, BlockRegistry.DIMENSION_BLOCK.getDefaultState());
         }
      } else {
         nextWorld.setBlockState(pos2, BlockRegistry.DIMENSION_BLOCK.getDefaultState());
      }

      NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
         return player;
      }), new SPacketRefreshDimensionRenderer());
      player.attemptTeleport(iposX, destinationPos.getY() + destinationPos.getY() < 50 ? 1.0D : 0.0D, iposZ, true);
   }
}
