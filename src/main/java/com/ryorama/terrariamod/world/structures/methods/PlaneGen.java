package com.ryorama.terrariamod.world.structures.methods;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlaneGen {

    public static void generatePlane(World world, BlockPos pos, Block inputBlock, int lengthX, int lengthY, int lengthZ) {
        for (int h = 0; h < lengthY; h++) {
            for (int i = 0; i <= lengthX; i++) {
                for (int j = 0; j <= lengthZ; j++) {
                    world.setBlockState(pos, inputBlock.getDefaultState());
                }
            }
        }
    }

    public static void generatePlaneWithDepth(World world, BlockPos pos, Block inputBlock, int lengthX, int lengthY, int lengthZ) {
        for (int h = 0; h < lengthY; h++) {
            for (int i = 0; i <= lengthX; i++) {
                for (int j = 0; j <= lengthZ; j++) {
                    world.setBlockState(pos, inputBlock.getDefaultState());
                }
            }
        }
    }
}
