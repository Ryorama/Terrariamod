package com.ryorama.terrariamod.mixins;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import net.minecraft.world.biome.source.util.VanillaTerrainParameters;
import net.minecraft.world.biome.source.util.VanillaTerrainParametersCreator;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctionTypes;
import net.minecraft.world.gen.densityfunction.DensityFunctions;
import net.minecraft.world.gen.noise.NoiseRouter;
import net.minecraft.world.gen.noise.SimpleNoiseRouter;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.ryorama.terrariamod.blocks.BlocksT;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.GenerationShapeConfig;
import net.minecraft.world.gen.chunk.NoiseSamplingConfig;
import net.minecraft.world.gen.chunk.SlideConfig;

@Mixin(ChunkGeneratorSettings.class)
public class ChunkGeneratorSettingsMixin {
	
	@Inject(at = @At("HEAD"), method = "createSurfaceSettings", cancellable = true)
	private static void createSurfaceSettings(boolean amplified, boolean largeBiomes, CallbackInfoReturnable<ChunkGeneratorSettings> info) {

		Constructor<?> construct = ChunkGeneratorSettings.class.getDeclaredConstructors()[0];
		
		construct.setAccessible(true);
		
		ChunkGeneratorSettings settings;
		GenerationShapeConfig generationShapeConfig = GenerationShapeConfig.create(-256, 384,
				new NoiseSamplingConfig(1.0, 1.0, 80.0, 160.0), new SlideConfig(-0.078125, 2, amplified ? 0 : 8), new SlideConfig(amplified ? 0.4 : 0.1171875, 3, 0),1, 2, VanillaTerrainParametersCreator.createSurfaceParameters(amplified));
		try {
			settings = (ChunkGeneratorSettings) construct.newInstance(generationShapeConfig, Blocks.STONE.getDefaultState(), Blocks.WATER.getDefaultState(), DensityFunctions.method_41103(generationShapeConfig, largeBiomes), VanillaSurfaceRules.createOverworldSurfaceRule(), 63, false, true, true, false);

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