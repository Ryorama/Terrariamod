package com.ryorama.terrariamod.blocks.terraria.world.ores;

import com.ryorama.terrariamod.blocks.impl.HotBlockT;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

public class HellstoneOre extends HotBlockT {
    public HellstoneOre(Settings properties, float hardness, float difficulty) {
        super(properties, hardness, difficulty);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);

        world.setBlockState(pos, Blocks.LAVA.getDefaultState(), 0);
    }
}
