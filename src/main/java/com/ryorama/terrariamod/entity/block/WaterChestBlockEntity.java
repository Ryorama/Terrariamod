package com.ryorama.terrariamod.entity.block;

import com.ryorama.terrariamod.entity.EntitiesT;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class WaterChestBlockEntity extends ChestBlockEntity {

    public WaterChestBlockEntity(BlockEntityType<? extends WaterChestBlockEntity> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(EntitiesT.WATER_CHEST, blockPos, blockState);
    }
}
