package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ryorama.terrariamod.world.features.CopperOreFeature;
import com.ryorama.terrariamod.world.features.CopperOreFeatureSurface;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

	@Inject(method = "addDefaultOres(Lnet/minecraft/world/biome/GenerationSettings$Builder;)V", at = @At("TAIL"))
	private static void addDefaultOres(GenerationSettings.Builder builder, CallbackInfo ci) {
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, CopperOreFeature.COPPER_ORE_FEATURE);
	    builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, CopperOreFeatureSurface.COPPER_ORE_FEATURE);
	}
	
}