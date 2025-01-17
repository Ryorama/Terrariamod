package com.ryorama.terrariamod.entities.terraria.block;

import com.ryorama.terrariamod.entities.EntitiesT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WoodChestBlockEntity extends ChestBlockEntity {

    public WoodChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(EntitiesT.WOOD_CHEST.get(), blockPos, blockState);
    }
}
