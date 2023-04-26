package com.ryorama.terrariamod.fabric.mixin;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(at = @At("HEAD"), method = "renderStatusBars", cancellable = true)
    private void renderStatusBars(MatrixStack matrices, CallbackInfo ci) {
        /*
        if (TerrariaMod.CONFIG.useVanillaHud) {
            return;
        } else {
            ci.cancel();
        }
        */
        ci.cancel();
    }
}
