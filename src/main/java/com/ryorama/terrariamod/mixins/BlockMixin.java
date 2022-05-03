package com.ryorama.terrariamod.mixins;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(at = @At("TAIL"), method = "onBreak")
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo info) {
        if (state == Blocks.CAMPFIRE.getDefaultState()) {
            if (world.isClient()) {
                ClientPlayerEntity playerEntity = MinecraftClient.getInstance().player;
                playerEntity.getStatHandler().setStat(playerEntity, Stats.CUSTOM.getOrCreateStat(TerrariaMod.COZY_FIRE), 0);
            }
        }
    }
}
