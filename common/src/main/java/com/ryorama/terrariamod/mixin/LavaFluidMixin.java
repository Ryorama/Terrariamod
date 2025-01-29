package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.LavaFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LavaFluid.class)
public abstract class LavaFluidMixin extends FlowingFluid {
    @Inject(at = @At("HEAD"), method = "canConvertToSource", cancellable = true)
    private void canConvertToSource(CallbackInfoReturnable<Boolean> info) {
        if (!TerrariaMod.CONFIG.useVanillaFluidPhysics) {
            info.setReturnValue(false);
        } else {
            info.setReturnValue(true);
        }
    }
}