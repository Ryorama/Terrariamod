package com.ryorama.terrariamod.mixins;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.StatType;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {

    @Inject(at = @At("TAIL"), method = "clientTick")
    private static void clientTick(World world, BlockPos pos, BlockState state, CampfireBlockEntity campfire, CallbackInfo ci) {
        if (world.isClient()) {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;

            if (player.getBlockPos().isWithinDistance(pos, 20)) {
                player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.COZY_FIRE), 99999);
            } else {
                player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(TerrariaMod.COZY_FIRE), 0);
            }
        }
    }
}