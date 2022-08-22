package com.ryorama.terrariamod.mixins;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.TerrariaModConfig;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.chunk.ChunkNoiseSampler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.BlockSource;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.StructureWeightSampler;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin {
	
	@Inject(at = @At("HEAD"), method = "getBlockState", cancellable = true)
	protected void getBlockState(ChunkNoiseSampler chunkNoiseSampler, int x, int y, int z, BlockState state, CallbackInfoReturnable<BlockState> cir) {
		if (TerrariaMod.CONFIG.customWorldGen) {
			cir.setReturnValue(Blocks.AIR.getDefaultState());
		}
	}
	
	
	@Inject(at = @At("HEAD"), method = "getHeight", cancellable = true)
	public void getHeight(int x, int z, Type heightmap, HeightLimitView world, CallbackInfoReturnable<Integer> info) {
		if (TerrariaMod.CONFIG.customWorldGen) {
			info.setReturnValue(0);
		}
	}
	
	private Chunk populateNoise(StructureAccessor accessor, Chunk chunk, int minY, int noiseSizeY) {
		return chunk;
	}
	
	@Inject(at = @At("HEAD"), method = "buildSurface", cancellable = true)
	public void buildSurface(ChunkRegion region, StructureAccessor structures, Chunk chunk, CallbackInfo info) {
		if (TerrariaMod.CONFIG.customWorldGen) {
			info.cancel();
		}
	}
}