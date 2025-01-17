package com.ryorama.terrariamod.entities.terraria.block;

import com.ryorama.terrariamod.entities.EntitiesT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WaterChestBlockEntity extends ChestBlockEntity {

    public WaterChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(EntitiesT.WATER_CHEST.get(), blockPos, blockState);
    }
}
