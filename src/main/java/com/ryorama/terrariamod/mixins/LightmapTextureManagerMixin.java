package com.ryorama.terrariamod.mixins;

import com.ryorama.ryolib.core.client.world.ModifyWorldColor;
import com.ryorama.ryolib.utils.graphics.ColorSettings;
import com.ryorama.terrariamod.world.WorldDataT;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LightmapTextureManager.class)
public abstract class LightmapTextureManagerMixin {

    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3f;lerp(Lnet/minecraft/util/math/Vec3f;F)V", ordinal = 0), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    public void update(float delta, CallbackInfo ci, ClientWorld clientworld, float f, float f1, float f2, Vec3f skyVector) {

        if (WorldDataT.bloodMoon) {
            ColorSettings colorSettings = new ColorSettings("FF0000", 1, "FF0000", 0.4f);
            Vec3f targetColor = ModifyWorldColor.lastColorSettings.getGLSkyLightColor().copy();
            targetColor.lerp(colorSettings.getGLSkyLightColor(), 1.0F);
            skyVector.lerp(targetColor, (float) MathHelper.lerp(1.0D, ModifyWorldColor.lastColorSettings.getSkyLightBlendStrength(), colorSettings.getSkyLightBlendStrength()));
        } else {
            ColorSettings colorSettings = ModifyWorldColor.lastColorSettings;
            Vec3f targetColor = ModifyWorldColor.lastColorSettings.getGLSkyLightColor().copy();
            targetColor.lerp(colorSettings.getGLSkyLightColor(), 1.0F);
            skyVector.lerp(targetColor, (float) MathHelper.lerp(1.0D, ModifyWorldColor.lastColorSettings.getSkyLightBlendStrength(), colorSettings.getSkyLightBlendStrength()));
        }
   }
}