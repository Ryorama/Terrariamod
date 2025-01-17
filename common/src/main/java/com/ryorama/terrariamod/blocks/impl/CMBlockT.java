package com.ryorama.terrariamod.blocks.impl;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.ToIntFunction;

public class CMBlockT extends BlockT {
    public CMBlockT(BlockBehaviour.Properties properties, float hardness, float difficulty) {
        super(properties.noOcclusion().noCollission().forceSolidOff(), hardness, difficulty);
    }

    public CMBlockT(BlockBehaviour.Properties properties, float hardness, float difficulty, int luminance) {
        super(properties.noOcclusion().noCollission().lightLevel(new ToIntFunction<BlockState>() {
            @Override
            public int applyAsInt(BlockState value) {
                return luminance;
            }
        }), hardness, difficulty);
    }

    public boolean isFullCube(BlockGetter world, BlockPos pos) {
        return false;
    }

    public int getOpacity(BlockState state, BlockGetter world, BlockPos pos) {
        return 0;
    }

    public VoxelShape getOutlineShape(BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }
}
