package com.ryorama.terrariamod.blocks.impl;

import com.ryorama.terrariamod.client.TAudio;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TreeSegment extends BlockT {

    public TreeSegment(BlockBehaviour.Properties properties, float hardness, float difficulty) {
        super(properties.noOcclusion().sound(TAudio.DIRT).notSolid(), hardness, difficulty);
    }

    public boolean isFullCube(BlockGetter world, BlockPos pos) {
        return false;
    }

    public VoxelShape getOutlineShape(BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    public boolean isTranslucent(BlockState state, BlockGetter world, BlockPos pos) {
        return true;
    }
}