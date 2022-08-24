package com.ryorama.terrariamod.entity.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class SkywareChestBlockEntity extends ChestBlockEntity {

    public SkywareChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }
}
