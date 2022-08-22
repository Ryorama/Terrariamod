package com.ryorama.terrariamod.mixins;

import java.util.Random;

import com.ryorama.terrariamod.TerrariaMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.gen.carver.CaveCarver;

@Mixin(CaveCarver.class)
public class CaveCarverMixin {
	
	@Inject(at = @At("HEAD"), method = "getTunnelSystemWidth", cancellable = true)
	protected void getTunnelSystemWidth(Random random, CallbackInfoReturnable<Float> info) {
		if (TerrariaMod.CONFIG.customWorldGen) {
			float f = random.nextFloat() * 2.0F + random.nextFloat();
			if (random.nextInt(10) == 0) {
				f *= random.nextFloat() * random.nextFloat() * 3.0F + 1.0F;
			}

			info.setReturnValue(f * 2);
		}
	}
}
