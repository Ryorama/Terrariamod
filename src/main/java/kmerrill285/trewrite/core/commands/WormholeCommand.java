package kmerrill285.trewrite.core.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Collection;
import java.util.Random;
import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.network.NetworkHandler;
import kmerrill285.trewrite.core.network.server.SPacketSyncInventoryTerraria;
import kmerrill285.trewrite.events.ScoreboardEvents;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.PacketDistributor;

public class WormholeCommand implements Command {
   private static final WormholeCommand CMD = new WormholeCommand();

   public static LiteralArgumentBuilder register(CommandDispatcher cmd) {
      return (LiteralArgumentBuilder)Commands.literal("wh").then(Commands.argument("destination", EntityArgument.entity()).executes(new WormholeCommand()));
   }

   private static int teleportToEntity(CommandSource source, Collection entities, Entity entity) {
      if (!(entity instanceof PlayerEntity)) {
         return 1;
      } else {
         InventoryTerraria inventory = WorldEvents.getOrLoadInventory((PlayerEntity)entity);
         if (inventory != null) {
            InventorySlot slot = null;

            int i;
            for(i = 0; i < inventory.main.length; ++i) {
               if (inventory.main[i].stack != null && inventory.main[i].stack.item == ItemsT.WORMHOLE_POTION) {
                  slot = inventory.main[i];
                  break;
               }
            }

            if (slot == null) {
               for(i = 0; i < inventory.hotbar.length; ++i) {
                  if (inventory.hotbar[i].stack != null && inventory.hotbar[i].stack.item == ItemsT.WORMHOLE_POTION) {
                     slot = inventory.hotbar[i];
                     break;
                  }
               }
            }

            if (slot != null) {
               slot.decrementStack(1);
               NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
                  return (ServerPlayerEntity)entity;
               }), new SPacketSyncInventoryTerraria(0, slot.area, slot.id, slot.stack));

               try {
                  source.asPlayer().teleport((ServerWorld)entity.getEntityWorld(), entity.posX, entity.posY, entity.posZ, entity.rotationYaw + (float)(new Random()).nextInt(360), entity.rotationPitch);
               } catch (CommandSyntaxException var6) {
                  var6.printStackTrace();
               }

               if (ScoreboardEvents.getScore(((ServerPlayerEntity)entity).getWorldScoreboard(), (ServerPlayerEntity)entity, ScoreboardEvents.HORRIFIED).getScorePoints() > 0) {
                  ((ServerPlayerEntity)entity).onKillCommand();
               }
            }
         }

         return 1;
      }
   }

   public int run(CommandContext context) throws CommandSyntaxException {
      return teleportToEntity((CommandSource)context.getSource(), (Collection)null, EntityArgument.getEntity(context, "destination"));
   }
}
