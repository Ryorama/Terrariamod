package com.ryorama.terrariamod.world.structures.underground;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.world.structures.methods.LineGen;
import com.ryorama.terrariamod.world.structures.methods.MiscGen;
import com.ryorama.terrariamod.world.structures.methods.PlaneGen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;

public class AbandonHouse {

    public static void generateStructure(StructureWorldAccess world, BlockPos pos) {
        LineGen.generateLineY(world, pos, BlocksT.WOODEN_BEAM, 5);
        LineGen.generateLineY(world, new BlockPos(pos.getX() + 5, pos.getY(), pos.getZ()), BlocksT.WOODEN_BEAM, 5);
        LineGen.generateLineY(world, new BlockPos(pos.getX() + 5, pos.getY(), pos.getZ() + 5), BlocksT.WOODEN_BEAM, 5);
        LineGen.generateLineY(world, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 5), BlocksT.WOODEN_BEAM, 5);

        PlaneGen.generateCube(world, new BlockPos(pos.getX(), pos.getY() + 5, pos.getZ()), BlocksT.WOOD, 5,  1, 5);
        PlaneGen.generateCube(world, new BlockPos(pos.getX(), pos.getY() + 6, pos.getZ()), BlocksT.WOOD, 5, 7, 0);
        PlaneGen.generateCube(world, new BlockPos(pos.getX(), pos.getY() + 6, pos.getZ()), BlocksT.WOOD, 0, 7, 5);
        PlaneGen.generateCube(world, new BlockPos(pos.getX(), pos.getY() + 6, pos.getZ() - 5), BlocksT.WOOD, 5, 7, 0);
        PlaneGen.generateCube(world, new BlockPos(pos.getX() - 5, pos.getY() + 6, pos.getZ()), BlocksT.WOOD, 0, 7, 5);

        world.setBlockState(new BlockPos(pos.getX() + 2, pos.getY() + 7, pos.getZ()), Blocks.AIR.getDefaultState(), 0);
        world.setBlockState(new BlockPos(pos.getX() + 2, pos.getY() + 8, pos.getZ()), Blocks.AIR.getDefaultState(), 0);

        MiscGen.scatterBlocks(world, new BlockPos(pos.getX(), pos.getY() + 14, pos.getZ()), Blocks.AIR, 5, 5, 3);
        MiscGen.generateChestWithLoot(world, new BlockPos(pos.getX() + 2, pos.getY() + 7, pos.getZ() + 2), BlocksT.GOLD_CHEST, "underground_house");
    }
}
