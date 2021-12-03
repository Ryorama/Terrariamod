package com.ryorama.terrariamod.mixins;

import com.ryorama.terrariamod.utils.ColorSettings;
import com.ryorama.terrariamod.world.WorldDataT;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LightmapTextureManager.class)
public abstract class LightTextureMixin {
    
    public ColorSettings lastColorSettings = new ColorSettings("", 0, "", 0);
    private int ticks = 0;

    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3f;lerp(Lnet/minecraft/util/math/Vec3f;F)V", ordinal = 0), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    public void update(float delta, CallbackInfo ci, ClientWorld clientworld, float f, float f1, float f3, Vec3f skyVector) {

        ticks++;

        if (WorldDataT.bloodMoon) {
            ColorSettings colorSettings = new ColorSettings("FF0000", 1, "FF0000", 0.4);
            lastColorSettings = new ColorSettings("", 0, "", 0);

            Vec3f targetColor = lastColorSettings.getGLSkyLightColor().copy();
            ;
            targetColor.lerp(colorSettings.getGLSkyLightColor(), 1.0f);
            skyVector.lerp(targetColor, (float) MathHelper.lerp(1.0f, lastColorSettings.getSkyLightBlendStrength(), colorSettings.getSkyLightBlendStrength()));

        } else {
            ColorSettings colorSettings = new ColorSettings("", 0, "", 0);
            lastColorSettings = new ColorSettings("FF0000", 0.6, "FF0000", 1);

            Vec3f targetColor = lastColorSettings.getGLSkyLightColor().copy();
            ;
            targetColor.lerp(colorSettings.getGLSkyLightColor(), 1.0f);
            skyVector.lerp(targetColor, (float) MathHelper.lerp(1.0f, lastColorSettings.getSkyLightBlendStrength(), colorSettings.getSkyLightBlendStrength()));
        }

        if(clientworld !=null) {
            if (ticks % 100 == 0) {
                for (int x = -15; x < 15; ++x) {
                    for (int y = -15; y < 15; ++y) {
                        for (int z = -15; z < 15; ++z) {
                            BlockPos pos2 = new BlockPos(x, y, z);
                            if (clientworld.isChunkLoaded(pos2)) {

                                Block block = clientworld.getBlockState(pos2).getBlock();

                            }
                        }
                    }
                }
            }
        }
    }
}
