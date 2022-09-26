package com.ryorama.terrariamod.world.structures.methods;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.block.Block;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MiscGen {
    public static void generateChestWithLoot(World world, BlockPos pos, Block container, String lootTableName) {
        world.setBlockState(pos, container.getDefaultState());
        LootableContainerBlockEntity.setLootTable(world, world.getRandom(), pos, new Identifier(TerrariaMod.MODID, "chests/" + lootTableName));
    }

    public static void scatterBlocks(World world, BlockPos pos, Block inputBlock, int lengthX, int lengthZ, int weight) {
        for (int i = 0; i < lengthX; i++) {
            for (int j = 0; j < lengthZ; j++) {
                if (world.getRandom().nextInt(weight) == 0) {
                    world.setBlockState(pos, inputBlock.getDefaultState());
                }
            }
        }
    }
}
