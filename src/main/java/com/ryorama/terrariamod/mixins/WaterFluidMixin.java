package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.fluid.WaterFluid;

@Mixin(WaterFluid.class)
public class WaterFluidMixin {
	@Inject(at = @At("HEAD"), method = "isInfinite", cancellable = true)
    private void isInfinite(CallbackInfoReturnable<Boolean> bruh) {
        bruh.setReturnValue(false);
    }
}
