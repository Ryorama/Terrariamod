package com.ryorama.terrariamod.blocks.impl;

import com.ryorama.terrariamod.client.TAudio;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.ToIntFunction;

public class PlantT extends BlockT {
    public PlantT(AbstractBlock.Settings properties, float hardness, float difficulty) {
        super(properties.nonOpaque().sounds(TAudio.GRASS_GRP).noCollision(), hardness, difficulty);
    }

    public PlantT(AbstractBlock.Settings properties, float hardness, float difficulty, int luminance) {
        super(properties.nonOpaque().sounds(TAudio.GRASS_GRP).noCollision().luminance(new ToIntFunction<BlockState>() {
            @Override
            public int applyAsInt(BlockState value) {
                return luminance;
            }
        }), hardness, difficulty);
    }

    public boolean isFullCube(BlockState state) {
        return false;
    }

    public int getOpacity(BlockState state, World worldIn, BlockPos pos) {
        return 0;
    }

    public boolean isOpaqueCube(BlockState state) {
        return false;
    }

    public VoxelShape getVisualShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }
}
