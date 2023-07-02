package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.carver.CaveCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CaveCarver.class)
public class CaveCarverMixin {

    @Inject(at = @At("HEAD"), method = "getTunnelSystemWidth", cancellable = true)
    protected void getTunnelSystemWidth(Random random, CallbackInfoReturnable<Float> info) {
        float f = random.nextFloat() * 2.0F + random.nextFloat();
        if (random.nextInt(10) == 0) {
            f *= random.nextFloat() * random.nextFloat() * 3.0F + 1.0F;
        }

        info.setReturnValue(f * 2);
    }
}