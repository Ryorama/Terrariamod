package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.carver.CarverContext;
import net.minecraft.world.gen.carver.RavineCarver;
import net.minecraft.world.gen.carver.RavineCarverConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RavineCarver.class)
public class RavineCarverMixin {

    @Inject(at = @At("HEAD"), method = "createHorizontalStretchFactors", cancellable = true)
    private void createHorizontalStretchFactors(CarverContext context, RavineCarverConfig config, Random random, CallbackInfoReturnable<float[]> info) {
        int i = context.getHeight();
        float[] fs = new float[i];
        float f = 1.0F;

        for (int j = 0; j < i; ++j) {
            if (j == 0 || random.nextInt(config.shape.widthSmoothness) == 0) {
                f = 1.0F + random.nextFloat() * random.nextFloat();
            }

            fs[j] = f * f * 2;
        }

        info.setReturnValue(fs);
    }

    @Inject(at = @At("HEAD"), method = "getVerticalScale", cancellable = true)
    private void getVerticalScale(RavineCarverConfig config, Random random, double pitch, float branchCount, float branchIndex, CallbackInfoReturnable<Double> info) {
        float f = 1.0F - MathHelper.abs(0.5F - branchIndex / branchCount) * 2.0F;
        float g = config.shape.verticalRadiusDefaultFactor + config.shape.verticalRadiusDefaultFactor * f;
        info.setReturnValue((double) g * pitch * (double) MathHelper.nextBetween(random, 0.75F, 1.0F) * 2);
    }
}