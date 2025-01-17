package com.ryorama.terrariamod.blocks.impl;

import com.ryorama.terrariamod.client.TAudio;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.ToIntFunction;

public class PlantT extends BlockT {
    public PlantT(BlockBehaviour.Properties properties, float hardness, float difficulty) {
        super(properties.noOcclusion().sound(TAudio.GRASS_GRP).noCollision().notSolid(), hardness, difficulty);
    }

    public PlantT(BlockBehaviour.Properties properties, float hardness, float difficulty, int luminance) {
        super(properties.noOcclusion().sound(TAudio.GRASS_GRP).noCollision().luminance(new ToIntFunction<BlockState>() {
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
