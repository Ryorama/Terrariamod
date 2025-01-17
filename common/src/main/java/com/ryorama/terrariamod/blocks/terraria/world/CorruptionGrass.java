package com.ryorama.terrariamod.blocks.terraria.world;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.blocks.impl.BlockT;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class CorruptionGrass extends BlockT {
    public CorruptionGrass(BlockBehaviour.Properties settings) {
        super(settings.randomTicks(), BlocksT.GROUND_HARDNESS, 10);
    }

    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (!world.isClientSide()) {
            if (!world.hasChunkAt(pos)) return;
            BlockPos pos2 = pos.offset(0, 1, 0);
            if (world.getBlockState(pos2).isSolid()) {
                world.setBlock(pos, BlocksT.DIRT_BLOCK.get().defaultBlockState(), 0);
                return;
            }
            if (world.getBlockState(pos2).getBlock().defaultBlockState() == Blocks.AIR.defaultBlockState()) {
                if (random.nextInt(100 * 10) <= 2) {
                    world.setBlock(pos2, BlocksT.DEAD_GRASS.get().defaultBlockState(), 0);
                    if (random.nextInt(15) == 0) {
                        world.setBlock(pos2, BlocksT.VILE_MUSHROOM.get().defaultBlockState(), 0);
                    }
                }
            }
            for (int i = 0; i < 4; ++i) {
                BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (blockpos.getY() == pos.getY() - 1) {
                    return;
                }
                if (world.getBlockState(blockpos.above()).getBlock() == Blocks.AIR) {
                    if (world.getBlockState(blockpos).getBlock() == BlocksT.DIRT_BLOCK.get()) {
                        world.setBlock(blockpos, BlocksT.CORRUPTED_GRASS_BLOCK.get().defaultBlockState(), 0);
                    }
                }
            }
        }
    }
}
