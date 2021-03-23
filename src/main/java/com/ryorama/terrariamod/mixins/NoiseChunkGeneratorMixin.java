package com.ryorama.terrariamod.mixins;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.AquiferSampler;
import net.minecraft.world.gen.BlockInterpolator;
import net.minecraft.world.gen.StructureWeightSampler;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin {
    
    @Inject(at = @At("HEAD"), method = "getBlockState", cancellable = true)
    protected void getBlockState(StructureWeightSampler structures, @Nullable AquiferSampler aquiferSampler,
            BlockInterpolator blockInterpolator, int x, int y, int z, double noise, CallbackInfoReturnable<BlockState> info) {
        double d = MathHelper.clamp(noise / 200.0D, -1.0D, 1.0D);
        d = d / 2.0D - d * d * d / 24.0D;
        
        if (y < -150 - Math.abs(d * 200) && y > -254 + Math.abs(d * 300)) {
            info.setReturnValue(Blocks.CAVE_AIR.getDefaultState());
        }
    }
}