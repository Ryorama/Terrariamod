package com.ryorama.terrariamod.blocks.furniture;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.api.BlockT;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CampfireT extends BlockT {

    public CampfireT(FabricBlockSettings properties, float hardness, float difficulty) {
        super(properties, hardness, difficulty);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (world.isClient()) {
            ClientPlayerEntity playerEntity = MinecraftClient.getInstance().player;
            playerEntity.getStatHandler().setStat(playerEntity, Stats.CUSTOM.getOrCreateStat(TerrariaMod.COZY_FIRE), 0);
        }
    }
}
