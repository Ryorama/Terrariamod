package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.WaterFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterFluid.class)
public abstract class WaterFluidMixin extends FlowingFluid {
    @Inject(at = @At("HEAD"), method = "canConvertToSource", cancellable = true)
    private void canConvertToSource(CallbackInfoReturnable<Boolean> info) {
        if (!TerrariaMod.CONFIG.useVanillaFluidPhysics) {
            info.setReturnValue(false);
        } else {
            info.setReturnValue(true);
        }
    }
}