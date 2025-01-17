package com.ryorama.terrariamod.entities.terraria.block;

import com.ryorama.terrariamod.entities.EntitiesT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FrozenChestBlockEntity extends ChestBlockEntity {

    public FrozenChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(EntitiesT.FROZEN_CHEST.get(), blockPos, blockState);
    }
}
