package kmerrill285.stackeddimensions.events;

import kmerrill285.stackeddimensions.StackedDimensions;
import kmerrill285.stackeddimensions.Util;
import kmerrill285.stackeddimensions.configuration.DimensionConfigs;
import kmerrill285.stackeddimensions.configuration.DimensionConfiguration;
import kmerrill285.stackeddimensions.networking.SPacketSendChunk;
import kmerrill285.stackeddimensions.render.ChunkEncoder;
import kmerrill285.stackeddimensions.render.StackedChunkProvider;
import kmerrill285.stackeddimensions.render.StackedChunkRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   value = {Dist.CLIENT},
   bus = Bus.FORGE
)
public class ClientEventHandler {
   @SubscribeEvent
   @OnlyIn(Dist.CLIENT)
   public static void handleWorldRenderEvent(RenderWorldLastEvent event) {
      if (Minecraft.getInstance().renderViewEntity != null) {
         Entity e = Minecraft.getInstance().renderViewEntity;
         if (Minecraft.getInstance().world == null) {
            return;
         }

         if (Minecraft.getInstance().world.dimension == null) {
            return;
         }

         if (Minecraft.getInstance().world.dimension.getType() == null) {
            return;
         }

         DimensionConfiguration config = DimensionConfigs.getConfig(Minecraft.getInstance().world.dimension.getType().getRegistryName());
         if (config != null && (e.posY >= (double)config.getMax() || e.posY <= (double)(config.getMin() + 256))) {
            return;
         }
      }

      if (Util.refreshDimensionRenderer) {
         StackedDimensions.renderWorld = null;
         StackedDimensions.loadRenderers = true;
         Util.refreshDimensionRenderer = false;
      }

      for(int i = 0; i < Util.chunksend.size(); ++i) {
         SPacketSendChunk packet = (SPacketSendChunk)Util.chunksend.get(i);
         if (StackedDimensions.renderWorld != null) {
            try {
               ((StackedChunkProvider)StackedDimensions.renderWorld.getChunkProvider()).func_217250_a(StackedDimensions.renderWorld, packet.x, packet.z, packet.buf, new CompoundNBT(), 0, true);
            } catch (Exception var7) {
               var7.printStackTrace();
            }

            ChunkEncoder.readIntoChunk(StackedDimensions.renderWorld.getChunk(packet.x, packet.z), packet.buf);
            if (packet.markDirty) {
               StackedDimensions.loadRenderers = true;
               StackedDimensions.renderWorld.getChunk(packet.x, packet.z).markDirty();
            }
         }

         Util.chunksend.remove(i);
      }

      if (Minecraft.getInstance().world != null) {
         if (Minecraft.getInstance().world.dimension != null) {
            if (Minecraft.getInstance().world.dimension.getType() != null) {
               DimensionConfiguration config = DimensionConfigs.getConfig(Minecraft.getInstance().world.dimension.getType().getRegistryName());
               if (config != null) {
                  if (Minecraft.getInstance().world.dimension.getType().getId() == config.getDimension().getId() && config != null) {
                     DimensionConfiguration c2;
                     int i;
                     if (config.getAbove() != null && Minecraft.getInstance().player.posY > (double)(config.getMax() - 25)) {
                        i = 1;
                        c2 = DimensionConfigs.getConfig(config.above);
                        if (c2 != null) {
                           i = c2.getMin() + 256;
                        }

                        try {
                           StackedChunkRenderer.update(event.getPartialTicks(), config.above, config.getMax() + i + 1, true);
                        } catch (Exception var6) {
                           var6.printStackTrace();
                           StackedChunkRenderer.rendering = false;
                           StackedDimensions.worldRenderer = null;
                        }
                     }

                     if (config.getBelow() != null && Minecraft.getInstance().player.posY < (double)(config.getMin() + 256 + 25)) {
                        i = 0;
                        c2 = DimensionConfigs.getConfig(config.below);
                        if (c2 != null) {
                           i = c2.getMax() - 255;
                        }

                        try {
                           StackedChunkRenderer.update(event.getPartialTicks(), config.below, config.getMin() - i, false);
                        } catch (Exception var5) {
                           var5.printStackTrace();
                           StackedChunkRenderer.rendering = false;
                           StackedDimensions.worldRenderer = null;
                        }
                     }
                  }

               }
            }
         }
      }
   }
}
