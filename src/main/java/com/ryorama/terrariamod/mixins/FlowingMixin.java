package com.ryorama.terrariamod.mixins;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.fluid.FlowHoney;
import com.ryorama.terrariamod.fluid.FlowLava;
import com.ryorama.terrariamod.fluid.HoneyFluid;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.*;
import net.minecraft.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ryorama.terrariamod.fluid.FlowWater;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

@Mixin(FlowableFluid.class)
public abstract class FlowingMixin extends Fluid {

	@Inject(at = @At("HEAD"), method = "canFlowThrough", cancellable = true)
	private void canFlowThrough(BlockView world, Fluid fluid, BlockPos pos, BlockState state, Direction face, BlockPos fromPos, BlockState fromState, FluidState fluidState, CallbackInfoReturnable<Boolean> bruh) {
		if (fluid instanceof WaterFluid || fluid instanceof LavaFluid || fluid instanceof HoneyFluid) {
			bruh.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "canFlow", cancellable = true)
	private void canFlow(BlockView world, BlockPos fluidPos, BlockState fluidBlockState, Direction flowDirection, BlockPos flowTo, BlockState flowToBlockState, FluidState fluidState, Fluid fluid, CallbackInfoReturnable<Boolean> bruh) {
		if (fluid instanceof WaterFluid || fluid instanceof LavaFluid || fluid instanceof HoneyFluid) {
			bruh.setReturnValue(false);
		}
	}

	@Inject(at = @At("HEAD"), method = "tryFlow", cancellable = true)
	private void tryFlow(WorldAccess world, BlockPos fluidPos, FluidState state, CallbackInfo bruh) {
		if ((state.getFluid() instanceof WaterFluid.Flowing || state.getFluid() instanceof LavaFluid.Flowing || state.getFluid() instanceof HoneyFluid.Flowing) || (state.getFluid() instanceof WaterFluid.Still || state.getFluid() instanceof LavaFluid.Still || state.getFluid() instanceof HoneyFluid.Still)) {
			if (state.getFluid() instanceof WaterFluid) {
				FlowWater.flowwater(world, fluidPos, state);
			} else if (state.getFluid() instanceof LavaFluid) {
				FlowLava.flowlava(world, fluidPos, state);
			} else if (state.getFluid() instanceof HoneyFluid) {
				FlowHoney.flowhoney(world, fluidPos, state);
			}

			bruh.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "getUpdatedState", cancellable = true)
	private void getUpdatedState(WorldView world, BlockPos pos, BlockState state, CallbackInfoReturnable<FluidState> bruh) {
		FluidState fluidstate = state.getFluidState();
		if (fluidstate.getFluid() instanceof WaterFluid.Flowing || fluidstate.getFluid() instanceof LavaFluid.Flowing || fluidstate.getFluid() instanceof HoneyFluid.Flowing) {

			if (fluidstate.getFluid() instanceof WaterFluid) {
				bruh.setReturnValue(Fluids.FLOWING_WATER.getFlowing(state.getFluidState().getLevel(), false));
			} else if (fluidstate.getFluid() instanceof LavaFluid) {
				bruh.setReturnValue(Fluids.FLOWING_LAVA.getFlowing(state.getFluidState().getLevel(), false));
			} else if (fluidstate.getFluid() instanceof HoneyFluid) {
				bruh.setReturnValue(TerrariaMod.FLOWING_HONEY.getFlowing(state.getFluidState().getLevel(), false));
			}
		}
	}

	@Inject(at = @At("HEAD"), method = "flow", cancellable = true)
	public void flow(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci) {
		if (direction == Direction.DOWN) {
			FluidState fluidState2 = world.getFluidState(pos);
		}

		if (direction == Direction.DOWN) {
			FluidState fluidState2 = world.getFluidState(pos);
		}
	}

	private void playExtinguishEvent(WorldAccess world, BlockPos pos) {
		world.syncWorldEvent(1501, pos, 0);
	}
}