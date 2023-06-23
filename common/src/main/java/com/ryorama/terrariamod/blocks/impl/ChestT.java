package com.ryorama.terrariamod.blocks.impl;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ChestT extends ChestBlock {

    public ChestT(Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier) {
        super(settings, supplier);
    }

    public Identifier getTexture() {
        return null;
    }
}