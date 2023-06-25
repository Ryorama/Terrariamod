package com.ryorama.terrariamod.blocks.impl;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import java.util.Random;

public class TreeSegment extends BlockT {

    public static Random rand = new Random();

    public TreeSegment(AbstractBlock.Settings properties, float hardness, float difficulty) {
        super(properties.nonOpaque(), hardness, difficulty);
    }

    public boolean isFullCube(BlockState state) {
        return false;
    }

    public boolean isOpaqueCube(BlockState state) {
        return false;
    }

    public VoxelShape getVisualShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }
}