package com.ryorama.terrariamod.entities.terraria.block;

import com.ryorama.terrariamod.entities.EntitiesT;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class WaterChestBlockEntity extends ChestBlockEntity {

    public WaterChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(EntitiesT.WATER_CHEST.get(), blockPos, blockState);
    }
}
