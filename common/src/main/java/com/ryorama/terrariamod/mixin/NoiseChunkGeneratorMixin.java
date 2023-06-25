package com.ryorama.terrariamod.mixin;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkNoiseSampler;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin {

    @Inject(at = @At("HEAD"), method = "getBlockState", cancellable = true)
    protected void getBlockState(ChunkNoiseSampler chunkNoiseSampler, int x, int y, int z, BlockState state, CallbackInfoReturnable<BlockState> info) {
        if (TerrariaMod.CONFIG.customWorldGen) {
            info.setReturnValue(Blocks.AIR.getDefaultState());
        }
    }


    @Inject(at = @At("HEAD"), method = "getHeight", cancellable = true)
    public void getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world, NoiseConfig noiseConfig, CallbackInfoReturnable<Integer> info) {
        if (TerrariaMod.CONFIG.customWorldGen) {
            info.setReturnValue(0);
        }
    }

    private Chunk populateNoise(StructureAccessor accessor, Chunk chunk, int minY, int noiseSizeY) {
        return chunk;
    }

    @Inject(at = @At("HEAD"), method = "buildSurface", cancellable = true)
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk, CallbackInfo info) {
        if (TerrariaMod.CONFIG.customWorldGen) {
            info.cancel();
        }
    }
}