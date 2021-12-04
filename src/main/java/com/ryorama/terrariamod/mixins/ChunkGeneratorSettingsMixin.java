package com.ryorama.terrariamod.mixins;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import net.minecraft.world.biome.source.util.VanillaTerrainParameters;
import net.minecraft.world.biome.source.util.VanillaTerrainParametersCreator;
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
import net.minecraft.world.gen.chunk.StructuresConfig;

@Mixin(ChunkGeneratorSettings.class)
public class ChunkGeneratorSettingsMixin {
	
	@Inject(at = @At("HEAD"), method = "createSurfaceSettings", cancellable = true)
	private static void createSurfaceSettings(boolean amplified, boolean largeBiomes, CallbackInfoReturnable<ChunkGeneratorSettings> info) {

		Constructor<?> construct = ChunkGeneratorSettings.class.getDeclaredConstructors()[0];
		
		construct.setAccessible(true);
		
		ChunkGeneratorSettings settings;
		try {
			settings = (ChunkGeneratorSettings) construct.newInstance(new StructuresConfig(true),
					GenerationShapeConfig.create(-256, 384,
							new NoiseSamplingConfig(0.9999999814507745D, 0.9999999814507745D, 80.0D, 160.0D),
							new SlideConfig(-10, 3, 0), new SlideConfig(15, 3, 0), 1, 2, false,
							amplified, true, VanillaTerrainParametersCreator.createSurfaceParameters(amplified)),
					BlocksT.STONE_BLOCK.getDefaultState(), Blocks.WATER.getDefaultState(), -2147483648, -255, 63, 40, false, true,
					true, true);
			
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