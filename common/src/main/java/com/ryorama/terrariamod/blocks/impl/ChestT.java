package com.ryorama.terrariamod.blocks.impl;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class ChestT extends ChestBlock {

    public ChestT(BlockBehaviour.Properties settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier) {
        super(settings, supplier);
    }

    public ResourceLocation getTexture() {
        return null;
    }
}