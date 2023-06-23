package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin extends FlowableFluid {
    @Inject(at = @At("HEAD"), method = "isInfinite", cancellable = true)
    private void isInfinite(CallbackInfoReturnable<Boolean> info) {
        if (!TerrariaMod.CONFIG.useVanillaFluidPhysics) {
            info.setReturnValue(false);
        } else {
            info.setReturnValue(true);
        }
    }
}