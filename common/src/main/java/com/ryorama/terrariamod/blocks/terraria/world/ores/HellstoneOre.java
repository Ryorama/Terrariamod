package com.ryorama.terrariamod.blocks.terraria.world.ores;

import com.ryorama.terrariamod.blocks.impl.HotBlockT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class HellstoneOre extends HotBlockT {
    public HellstoneOre(BlockBehaviour.Properties properties, float hardness, float difficulty) {
        super(properties, hardness, difficulty);
    }

    @Override
    public void onBroken(LevelAccessor world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);

        world.setBlock(pos, Blocks.LAVA.defaultBlockState(), 0);
    }
}
