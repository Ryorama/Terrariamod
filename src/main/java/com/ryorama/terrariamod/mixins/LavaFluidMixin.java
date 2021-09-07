package com.ryorama.terrariamod.mixins;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin extends FlowableFluid {
    @Inject(at = @At("HEAD"), method = "isInfinite", cancellable = true)
    private void isInfinite(CallbackInfoReturnable<Boolean> bruh) {
        bruh.setReturnValue(false);
    }
}
