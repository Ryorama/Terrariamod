package com.ryorama.terrariamod.blocks.impl;

import com.ryorama.terrariamod.client.TAudio;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Random;

public class TreeSegment extends BlockT {

    public TreeSegment(AbstractBlock.Settings properties, float hardness, float difficulty) {
        super(properties.nonOpaque().sounds(TAudio.DIRT).notSolid(), hardness, difficulty);
    }

    public boolean isFullCube(BlockView world, BlockPos pos) {
        return false;
    }

    public VoxelShape getOutlineShape(BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }
}