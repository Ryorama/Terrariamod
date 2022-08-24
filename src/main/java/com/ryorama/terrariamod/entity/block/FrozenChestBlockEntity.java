package com.ryorama.terrariamod.entity.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class FrozenChestBlockEntity extends ChestBlockEntity {

    public FrozenChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }
}
