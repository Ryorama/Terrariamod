package com.ryorama.terrariamod.world.structures.methods;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LineGen {

    public static void generateLineX(World world, BlockPos pos, Block inputBlock, int length) {
        for (int l = 0; l <= length; l++) {
            world.setBlockState(new BlockPos(pos.getX() + l, pos.getY(), pos.getZ()), inputBlock.getDefaultState());
        }
    }

    public static void generateLineZ(World world, BlockPos pos, Block inputBlock, int length) {
        for (int l = 0; l <= length; l++) {
            world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + l), inputBlock.getDefaultState());
        }
    }

    public static void generateLineY(World world, BlockPos pos, Block inputBlock, int length) {
        for (int l = 0; l <= length; l++) {
            world.setBlockState(new BlockPos(pos.getX(), pos.getY() + l, pos.getZ()), inputBlock.getDefaultState());
        }
    }
}
