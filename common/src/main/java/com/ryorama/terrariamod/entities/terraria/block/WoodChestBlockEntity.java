package com.ryorama.terrariamod.entities.terraria.block;

import com.ryorama.terrariamod.entities.EntitiesT;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class WoodChestBlockEntity extends ChestBlockEntity {

    public WoodChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(EntitiesT.WOOD_CHEST.get(), blockPos, blockState);
    }
}
