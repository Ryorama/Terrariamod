package kmerrill285.stackeddimensions.events;

import kmerrill285.stackeddimensions.Util;
import kmerrill285.stackeddimensions.configuration.DimensionConfigs;
import kmerrill285.stackeddimensions.configuration.DimensionConfiguration;
import kmerrill285.stackeddimensions.networking.NetworkHandler;
import kmerrill285.stackeddimensions.networking.SPacketForceMovement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.world.WorldEvent.Unload;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.network.PacketDistributor;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class CommonEventHandler {
   @OnlyIn(Dist.CLIENT)
   @SubscribeEvent
   public static void worldUnloadEventClient(Unload event) {
      Util.refreshDimensionRenderer = true;
   }

   @SubscribeEvent
   public static void handleLivingEvent(EntityEvent event) {
      Entity entity = event.getEntity();
      if (entity != null) {
         DimensionConfiguration config = DimensionConfigs.getConfig(event.getEntity().dimension.getRegistryName());
         if (config != null && !(event.getEntity() instanceof PlayerEntity)) {
            if (event.getEntity().posY > (double)(config.getMax() - 1)) {
               if (config.getAbove() != null) {
                  event.getEntity().teleportKeepLoaded(event.getEntity().posX, 1.0D, event.getEntity().posZ);
                  event.getEntity().posY = 1.0D;
                  event.getEntity().changeDimension(config.getAbove());
                  return;
               }
            } else if (event.getEntity().posY < (double)(config.getMin() + 256)) {
               int max = 255;
               DimensionConfiguration c2 = DimensionConfigs.getConfig(config.below);
               if (c2 != null) {
                  max = c2.getMax() - 1;
               }

               if (config.getBelow() != null) {
                  event.getEntity().teleportKeepLoaded(event.getEntity().posX, (double)max, event.getEntity().posZ);
                  event.getEntity().posY = (double)max;
                  event.getEntity().changeDimension(config.getBelow());
                  return;
               }
            }
         }

      }
   }

   public static void moveEntity(Entity entity, double x, double y, double z, boolean onGround) {
      if (entity != null) {
         if (entity instanceof PlayerEntity) {
            if (entity.world.isRemote) {
               entity.move(MoverType.PISTON, new Vec3d(x, y, z));
               entity.setMotion(new Vec3d(x != 0.0D ? 0.0D : entity.getMotion().x, y != 0.0D ? 0.0D : entity.getMotion().y, z != 0.0D ? 0.0D : entity.getMotion().z));
            } else {
               entity.move(MoverType.PISTON, new Vec3d(x * 0.75D, y * 0.75D, z * 0.75D));
               entity.setMotion(new Vec3d(x != 0.0D ? 0.0D : entity.getMotion().x, y != 0.0D ? 0.0D : entity.getMotion().y, z != 0.0D ? 0.0D : entity.getMotion().z));
               try {
                  NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
                     return (ServerPlayerEntity)entity;
                  }), new SPacketForceMovement(x, y, z, onGround));
               } catch (Exception var9) {
               }
            }
         } else {
            entity.move(MoverType.PISTON, new Vec3d(x, y, z));
            entity.setMotion(new Vec3d(x != 0.0D ? 0.0D : entity.getMotion().x, y != 0.0D ? 0.0D : entity.getMotion().y, z != 0.0D ? 0.0D : entity.getMotion().z));
         }

         if (onGround) {
            entity.onGround = true;
            entity.isAirBorne = false;
            entity.fallDistance = 0.0F;
         }

      }
   }
}
