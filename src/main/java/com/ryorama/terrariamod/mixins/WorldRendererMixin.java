package com.ryorama.terrariamod.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import com.ryorama.ryolib.core.client.world.ModifyWorldColor;
import com.ryorama.ryolib.utils.graphics.ColorSettings;
import com.ryorama.ryolib.utils.graphics.ColorUtil;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Shadow
    private ClientWorld world;

    @Inject(method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/util/math/Matrix4f;FLjava/lang/Runnable;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getMoonPhase()I"))
    private void renderSky(MatrixStack poseStack, Matrix4f matrix4f, float partialTicks, Runnable runnable, CallbackInfo ci) {
        Vec3f glColor = ColorUtil.glColor(ColorUtil.unpack(ColorSettings.tryParseColor(ModifyWorldColor.moonTextureHexColor)));

        RenderSystem.setShaderColor(glColor.getX(), glColor.getY(), glColor.getZ(), 1.0F - this.world.getRainGradient(partialTicks));
    }
}