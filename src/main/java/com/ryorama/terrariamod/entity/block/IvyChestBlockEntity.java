package com.ryorama.terrariamod.entity.block;

import com.ryorama.terrariamod.entity.EntitiesT;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class IvyChestBlockEntity extends ChestBlockEntity {

    public IvyChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(EntitiesT.IVY_CHEST, blockPos, blockState);
    }
}
