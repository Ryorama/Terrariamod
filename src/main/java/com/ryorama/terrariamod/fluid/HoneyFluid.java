package com.ryorama.terrariamod.fluid;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.items.ItemsT;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;

public abstract class HoneyFluid extends BaseFluid {

    @Override
    public Fluid getStill() {
        return TerrariaMod.STILL_HONEY;
    }

    @Override
    public Fluid getFlowing() {
        return TerrariaMod.FLOWING_HONEY;
    }

    @Override
    public Item getBucketItem() {
        return TerrariaMod.HONEY_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return TerrariaMod.HONEY.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
    }

    public static class Flowing extends HoneyFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends HoneyFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }
    }
}