package com.ryorama.terrariamod.fluid;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidFillable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FlowHoney {
	
	 public static void flowhoney(WorldAccess world, BlockPos fluidPos, FluidState state) {
	        if (world.getBlockState(fluidPos).getBlock() instanceof FluidFillable) {
	            return;
	        }
	        if ((world.getBlockState(fluidPos.down()).canBucketPlace(TerrariaMod.STILL_HONEY)) && (getHoneyLevel(fluidPos.down(), world) != 8)) {
	            int centerlevel = getHoneyLevel(fluidPos, world);
	            world.setBlockState(fluidPos, Blocks.AIR.getDefaultState(), 11);
	            addHoney(centerlevel, fluidPos.down(), world);
	        } else {
	            ArrayList<BlockPos> blocks = new ArrayList<BlockPos>(4);
	            for (Direction dir : Direction.Type.HORIZONTAL) {
	                blocks.add(fluidPos.offset(dir));
	            }
				blocks.removeIf(pos -> !world.getBlockState(pos).canBucketPlace(TerrariaMod.STILL_HONEY));
	            Collections.shuffle(blocks);
	            equalizeHoney(blocks, fluidPos, world);
	        }
	 }
	 
	 	public static int getHoneyLevel(BlockPos pos, WorldAccess world) {
	        BlockState blockstate = world.getBlockState(pos);
	        FluidState fluidstate = blockstate.getFluidState();
	        int honeylevel = 0;
	        if (fluidstate.getFluid() instanceof HoneyFluid.Still){
				honeylevel = 8;
	        } else if (fluidstate.getFluid() instanceof HoneyFluid.Flowing) {
				honeylevel = fluidstate.getLevel();
	        }
	        return honeylevel;
	    }

	    public static void setHoneyLevel(int level, BlockPos pos, WorldAccess world) {
	        if (level == 8) {
	            if (!(world.getBlockState(pos).getBlock() instanceof FluidFillable)) { // Don't fill kelp etc
	                world.setBlockState(pos, TerrariaMod.HONEY.getDefaultState(), 11);
	            }
	        } else if (level == 0) {
	            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
	        } else if (level < 8) {
	            world.setBlockState(pos, TerrariaMod.FLOWING_HONEY.getFlowing(level, false).getBlockState(), 11);
	        } else {
	            System.out.println("Can't set honey >8 something went very wrong!");
	        }
	    }

	    public static void addHoney(int level, BlockPos pos, WorldAccess world) {
	        int existinghoney = getHoneyLevel(pos, world);
	        int totalhoney = existinghoney + level;
	        if (totalhoney > 8) {
				setHoneyLevel(totalhoney - 8, pos.up(), world);
				setHoneyLevel(8, pos, world);
	        } else {
				setHoneyLevel(totalhoney, pos, world);
	        }
	    }

	    public static void equalizeHoney(ArrayList<BlockPos> blocks, BlockPos center, WorldAccess world) {
	        int[] honeylevels = new int[4];
	        Arrays.fill(honeylevels, -1);
	        int centerhoneylevel = getHoneyLevel(center, world);
	        for (BlockPos block : blocks) {
				honeylevels[blocks.indexOf(block)] = getHoneyLevel(block, world);
	        }

	        int waterlevelsnum = honeylevels.length;
	        int didnothings = 0;
	        int honeylevel;
	        while (didnothings < waterlevelsnum) {
	            didnothings = 0;
	            for (int i = 0; i < 4; i++) {
					honeylevel = honeylevels[i];
	                if (honeylevel != -1) {
	                    if ((centerhoneylevel >= (honeylevel + 2))) {
							honeylevel += 1;
							honeylevels[i] = honeylevel;
							centerhoneylevel -= 1;
	                    } else {
	                        didnothings += 1;
	                    }
	                } else {
	                    didnothings += 1;
	                }
	            }
	        }
	        for (BlockPos block : blocks) {
	            int newhoneylevel = honeylevels[blocks.indexOf(block)];
				setHoneyLevel(newhoneylevel, block, world);
	        }
			setHoneyLevel(centerhoneylevel, center, world);
	    }
}
