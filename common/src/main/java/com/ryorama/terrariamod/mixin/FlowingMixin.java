package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.fluids.FlowLava;
import com.ryorama.terrariamod.fluids.FlowWater;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlowingFluid.class)
public abstract class FlowingMixin extends Fluid {

    @Inject(at = @At("HEAD"), method = "canPassThrough", cancellable = true)
    private void canPassThrough(BlockGetter world, Fluid fluid, BlockPos pos, BlockState state, Direction face, BlockPos fromPos, BlockState fromState, FluidState fluidState, CallbackInfoReturnable<Boolean> bruh) {
        if (!TerrariaMod.CONFIG.useVanillaFluidPhysics) {
            if (fluid instanceof WaterFluid || fluid instanceof LavaFluid) {
                bruh.setReturnValue(false);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canSpreadTo", cancellable = true)
    private void canSpreadTo(BlockGetter world, BlockPos fluidPos, BlockState fluidBlockState, Direction flowDirection, BlockPos flowTo, BlockState flowToBlockState, FluidState fluidState, Fluid fluid, CallbackInfoReturnable<Boolean> bruh) {
        if (!TerrariaMod.CONFIG.useVanillaFluidPhysics) {
            if (fluid instanceof WaterFluid || fluid instanceof LavaFluid) {
                bruh.setReturnValue(false);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "spread", cancellable = true)
    private void spread(Level world, BlockPos fluidPos, FluidState state, CallbackInfo info) {
        if (!TerrariaMod.CONFIG.useVanillaFluidPhysics) {
            if ((state.getType() instanceof WaterFluid.Flowing || state.getType() instanceof LavaFluid.Flowing || (state.getType() instanceof WaterFluid.Source || state.getType() instanceof LavaFluid.Source))) {
                if (state.getType() instanceof WaterFluid) {
                    FlowWater.flowwater(world, fluidPos, state);
                } else if (state.getType() instanceof LavaFluid) {
                    FlowLava.flowlava(world, fluidPos, state);
                }

                info.cancel();
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "getNewLiquid", cancellable = true)
    private void getNewLiquid(Level world, BlockPos pos, BlockState state, CallbackInfoReturnable<FluidState> info) {
        if (!TerrariaMod.CONFIG.useVanillaFluidPhysics) {
            FluidState fluidstate = state.getFluidState();
            if (fluidstate.getType() instanceof WaterFluid.Flowing || fluidstate.getType() instanceof LavaFluid.Flowing) {

                if (fluidstate.getType() instanceof WaterFluid) {
                    info.setReturnValue(Fluids.FLOWING_WATER.getFlowing(state.getFluidState().getAmount(), false));
                } else if (fluidstate.getType() instanceof LavaFluid) {
                    info.setReturnValue(Fluids.FLOWING_LAVA.getFlowing(state.getFluidState().getAmount(), false));
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "spreadTo", cancellable = true)
    public void spreadTo(LevelAccessor world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci) {
        if (!TerrariaMod.CONFIG.useVanillaFluidPhysics) {
            if (direction == Direction.DOWN) {
                FluidState fluidState2 = world.getFluidState(pos);
            }

            if (direction == Direction.DOWN) {
                FluidState fluidState2 = world.getFluidState(pos);
            }
        }
    }
}