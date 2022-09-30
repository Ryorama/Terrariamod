package com.ryorama.terrariamod.world.structures.methods;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;

public class PlaneGen {

    public static void generateCube(StructureWorldAccess world, BlockPos pos, Block inputBlock, int lengthX, int lengthY, int lengthZ) {
        for (int h = 0; h < lengthY; h++) {
            for (int i = 0; i <= lengthX; i++) {
                for (int j = 0; j <= lengthZ; j++) {
                    world.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + h, pos.getZ() + j), inputBlock.getDefaultState(), 0);
                }
            }
        }
    }
}
