package com.ryorama.terrariamod.fluids;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.LavaFluid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FlowLava {

    public static void flowlava(LevelAccessor world, BlockPos fluidPos, FluidState state) {
        if (world.getBlockState(fluidPos).getBlock() instanceof LiquidBlockContainer) {
            return;
        }
        if ((world.getBlockState(fluidPos.below()).canBeReplaced(Fluids.LAVA)) && (getLavaLevel(fluidPos.below(), world) != 8)) {
            int centerlevel = getLavaLevel(fluidPos, world);
            world.setBlock(fluidPos, Blocks.AIR.defaultBlockState(), 11);
            addLava(centerlevel, fluidPos.below(), world);
        } else {
            ArrayList<BlockPos> blocks = new ArrayList<BlockPos>(4);
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                blocks.add(fluidPos.offset(dir.getNormal()));
            }
            blocks.removeIf(pos -> !world.getBlockState(pos).canBeReplaced(Fluids.LAVA));
            Collections.shuffle(blocks);
            equalizeLava(blocks, fluidPos, world);
        }
    }

    public static int getLavaLevel(BlockPos pos, LevelAccessor world) {
        BlockState blockstate = world.getBlockState(pos);
        FluidState fluidstate = blockstate.getFluidState();
        int lavalevel = 0;
        if (fluidstate.getType() instanceof LavaFluid.Source){
            lavalevel = 8;
        } else if (fluidstate.getType() instanceof LavaFluid.Flowing) {
            lavalevel = fluidstate.getAmount();
        }
        return lavalevel;
    }

    public static void setLavaLevel(int level, BlockPos pos, LevelAccessor world) {
        if (level == 8) {
            if (!(world.getBlockState(pos).getBlock() instanceof LiquidBlockContainer)) { // Don't fill kelp etc
                world.setBlock(pos, Fluids.LAVA.defaultFluidState().createLegacyBlock(), 11);
            }
        } else if (level == 0) {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        } else if (level < 8) {
            world.setBlock(pos, Fluids.FLOWING_LAVA.getFlowing(level, false).createLegacyBlock(), 11);
        } else {
            System.out.println("Can't set lava >8 something went very wrong!");
        }
    }

    public static void addLava(int level, BlockPos pos, LevelAccessor world) {
        int existinglava = getLavaLevel(pos, world);
        int totallava = existinglava + level;
        if (totallava > 8) {
            setLavaLevel(totallava - 8, pos.above(), world);
            setLavaLevel(8, pos, world);
        } else {
            setLavaLevel(totallava, pos, world);
        }
    }

    public static void equalizeLava(ArrayList<BlockPos> blocks, BlockPos center, LevelAccessor world) {
        int[] lavalevels = new int[4];
        Arrays.fill(lavalevels, -1);
        int centerlavalevel = getLavaLevel(center, world);
        for (BlockPos block : blocks) {
            lavalevels[blocks.indexOf(block)] = getLavaLevel(block, world);
        }

        int lavalevelsnum = lavalevels.length;
        int didnothings = 0;
        int lavalevel;
        while (didnothings < lavalevelsnum) {
            didnothings = 0;
            for (int i = 0; i < 4; i++) {
                lavalevel = lavalevels[i];
                if (lavalevel != -1) {
                    if ((centerlavalevel >= (lavalevel + 2))) {
                        lavalevel += 1;
                        lavalevels[i] = lavalevel;
                        centerlavalevel -= 1;
                    } else {
                        didnothings += 1;
                    }
                } else {
                    didnothings += 1;
                }
            }
        }
        for (BlockPos block : blocks) {
            int newlavalevel = lavalevels[blocks.indexOf(block)];
            setLavaLevel(newlavalevel, block, world);
        }
        setLavaLevel(centerlavalevel, center, world);
    }
}