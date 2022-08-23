package com.ryorama.terrariamod.mixins;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.source.util.VanillaTerrainParametersCreator;

import net.minecraft.world.gen.densityfunction.DensityFunctions;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.GenerationShapeConfig;

@Mixin(ChunkGeneratorSettings.class)
public class ChunkGeneratorSettingsMixin {
	
	@Inject(at = @At("HEAD"), method = "createSurfaceSettings", cancellable = true)
	private static void createSurfaceSettings(boolean amplified, boolean largeBiomes, CallbackInfoReturnable<ChunkGeneratorSettings> info) {

		Constructor<?> construct = ChunkGeneratorSettings.class.getDeclaredConstructors()[0];
		
		construct.setAccessible(true);
		
		ChunkGeneratorSettings settings;

		try {
			settings = (ChunkGeneratorSettings) construct.newInstance(GenerationShapeConfig.SURFACE, Blocks.STONE.getDefaultState(), Blocks.WATER.getDefaultState(), DensityFunctions.createSurfaceNoiseRouter(BuiltinRegistries.DENSITY_FUNCTION, largeBiomes, amplified), VanillaSurfaceRules.createOverworldSurfaceRule(), 63, false, true, true, false);

			info.setReturnValue(settings);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}