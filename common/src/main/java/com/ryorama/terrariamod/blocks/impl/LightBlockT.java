package com.ryorama.terrariamod.blocks.impl;

import net.minecraft.block.BlockState;

import java.util.function.ToIntFunction;

public class LightBlockT extends BlockT {

    public LightBlockT(Settings properties, float hardness, float difficulty, int luminance) {
        super(properties.luminance(new ToIntFunction<BlockState>() {
            @Override
            public int applyAsInt(BlockState value) {
                return luminance;
            }
        }), hardness, difficulty);
    }
}
