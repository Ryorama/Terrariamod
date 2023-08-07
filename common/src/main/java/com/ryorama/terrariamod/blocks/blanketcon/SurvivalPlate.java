package com.ryorama.terrariamod.blocks.blanketcon;

import net.minecraft.block.BlockSetType;
import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

public class SurvivalPlate extends PressurePlateBlock {

    public SurvivalPlate(Settings settings) {
        super(ActivationRule.EVERYTHING, settings.strength(9999999, 9999999), BlockSetType.STONE);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        System.out.println("E");

        if (!world.isClient()) {
            if (entity instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity) entity).interactionManager.changeGameMode(GameMode.SURVIVAL);
            }
        }
    }
}
