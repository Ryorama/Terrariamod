package com.ryorama.terrariamod.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.WaterFluid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FlowWater {

    public static void flowwater(LevelAccessor world, BlockPos fluidPos, FluidState state) {
        if (world.getBlockState(fluidPos).getBlock() instanceof LiquidBlockContainer) {
            return;
        }
        if ((world.getBlockState(fluidPos.below()).canBeReplaced(Fluids.WATER)) && (getWaterLevel(fluidPos.below(), world) != 8)) {
            int centerlevel = getWaterLevel(fluidPos, world);
            world.setBlock(fluidPos, Blocks.AIR.defaultBlockState(), 11);
            addWater(centerlevel, fluidPos.below(), world);
        } else {
            ArrayList<BlockPos> blocks = new ArrayList<BlockPos>(4);
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                blocks.add(fluidPos.offset(dir.getNormal()));
            }
            blocks.removeIf(pos -> !world.getBlockState(pos).canBeReplaced(Fluids.WATER));
            Collections.shuffle(blocks);
            equalizeWater(blocks, fluidPos, world);
        }
    }

    public static int getWaterLevel(BlockPos pos, LevelAccessor world) {
        BlockState blockstate = world.getBlockState(pos);
        FluidState fluidstate = blockstate.getFluidState();
        int waterlevel = 0;
        if (fluidstate.getType() instanceof WaterFluid.Source){
            waterlevel = 8;
        } else if (fluidstate.getType() instanceof WaterFluid.Flowing) {
            waterlevel = fluidstate.getAmount();
        }
        return waterlevel;
    }

    public static void setWaterLevel(int level, BlockPos pos, LevelAccessor world) {
        if (level == 8) {
            if (!(world.getBlockState(pos).getBlock() instanceof LiquidBlockContainer)) { // Don't fill kelp etc
                world.setBlock(pos, Fluids.WATER.defaultFluidState().createLegacyBlock(), 11);
            }
        } else if (level == 0) {
            world.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
        } else if (level < 8) {
            world.setBlock(pos, Fluids.FLOWING_WATER.getFlowing(level, false).createLegacyBlock(), 11);
        } else {
            System.out.println("Can't set water >8 something went very wrong!");
        }
    }

    public static void addWater(int level, BlockPos pos, LevelAccessor world) {
        int existingwater = getWaterLevel(pos, world);
        int totalwater = existingwater + level;
        if (totalwater > 8) {
            setWaterLevel(totalwater - 8, pos.above(), world);
            setWaterLevel(8, pos, world);
        } else {
            setWaterLevel(totalwater, pos, world);
        }
    }

    public static void equalizeWater(ArrayList<BlockPos> blocks, BlockPos center, LevelAccessor world) {
        int[] waterlevels = new int[4];
        Arrays.fill(waterlevels, -1);
        int centerwaterlevel = getWaterLevel(center, world);
        for (BlockPos block : blocks) {
            waterlevels[blocks.indexOf(block)] = getWaterLevel(block, world);
        }

        int waterlevelsnum = waterlevels.length;
        int didnothings = 0;
        int waterlevel;
        while (didnothings < waterlevelsnum) {
            didnothings = 0;
            for (int i = 0; i < 4; i++) {
                waterlevel = waterlevels[i];
                if (waterlevel != -1) {
                    if ((centerwaterlevel >= (waterlevel + 2))) {
                        waterlevel += 1;
                        waterlevels[i] = waterlevel;
                        centerwaterlevel -= 1;
                    } else {
                        didnothings += 1;
                    }
                } else {
                    didnothings += 1;
                }
            }
        }
        for (BlockPos block : blocks) {
            int newwaterlevel = waterlevels[blocks.indexOf(block)];
            setWaterLevel(newwaterlevel, block, world);
        }
        setWaterLevel(centerwaterlevel, center, world);
    }
}