package com.ryorama.terrariamod.world.structures.methods;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;

public class LineGen {

    public static void generateLineX(StructureWorldAccess world, BlockPos pos, Block inputBlock, int length) {
        for (int l = 0; l < length; l++) {
            world.setBlockState(new BlockPos(pos.getX() + l, pos.getY(), pos.getZ()), inputBlock.getDefaultState(), 0);
        }
    }

    public static void generateLineZ(StructureWorldAccess world, BlockPos pos, Block inputBlock, int length) {
        for (int l = 0; l < length; l++) {
            world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + l), inputBlock.getDefaultState(), 0);
        }
    }

    public static void generateLineY(StructureWorldAccess world, BlockPos pos, Block inputBlock, int length) {
        for (int l = 0; l < length; l++) {
            world.setBlockState(new BlockPos(pos.getX(), pos.getY() + l, pos.getZ()), inputBlock.getDefaultState(), 0);
        }
    }
}
