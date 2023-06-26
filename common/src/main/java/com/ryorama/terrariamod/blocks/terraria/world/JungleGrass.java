package com.ryorama.terrariamod.blocks.terraria.world;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.blocks.impl.BlockT;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class JungleGrass extends BlockT {
    public JungleGrass(Settings settings) {
        super(settings.ticksRandomly(), 15, 15);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient()) {
            if (!world.isChunkLoaded(pos)) return;
            BlockPos pos2 = pos.add(0, 1, 0);
            if (world.getBlockState(pos2).isSolid() && world.getBlockState(pos2).getBlock() instanceof AirBlock == false) {
                world.setBlockState(pos, BlocksT.MUD.get().getDefaultState());
                return;
            }
            if (world.getBlockState(pos2).getBlock().getDefaultState() == Blocks.AIR.getDefaultState()) {
                if (random.nextInt(100 * 10) <= 2) {
                    world.setBlockState(pos2, BlocksT.GRASS.get().getDefaultState());
                    if (random.nextInt(15) == 0) {
                        world.setBlockState(pos2, BlocksT.MUSHROOM.get().getDefaultState());
                    }
                }
            }
            for (int i = 0; i < 4; ++i) {
                BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (blockpos.getY() == pos.getY() - 1) {
                    return;
                }
                if (world.getBlockState(blockpos.up()).getBlock() == Blocks.AIR) {
                    if (world.getBlockState(blockpos).getBlock() == BlocksT.MUD) {
                        world.setBlockState(blockpos, this.getDefaultState());
                    }
                }
            }
        }
    }
}
