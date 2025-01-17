package com.ryorama.terrariamod.client.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

public class UIRenderer {

    public static int scaledWidth;
    public static int scaledHeight;

    public static final Minecraft client = Minecraft.getInstance();

    public static void renderOverlay(ResourceLocation texture, float opacity, float width, float height, float x, float y, int zPos) {

        scaledWidth = client.getWindow().getGuiScaledWidth();
        scaledHeight = client.getWindow().getGuiScaledHeight();

        PoseStack matrixStack = RenderSystem.getModelViewStack();

        matrixStack.pushPose();
        matrixStack.translate(x, y, zPos);
        RenderSystem.applyModelViewMatrix();
        RenderSystem.setShaderTexture(0, texture);

        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, opacity);
        RenderSystem.setShaderTexture(0, texture);
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.color(0, 0, 0, opacity);
        bufferBuilder.vertex(0, (double)height, zPos).uv(0.0F, 1.0F).endVertex();
        bufferBuilder.vertex((double)width, (double)height, zPos).uv(1.0F, 1.0F).endVertex();
        bufferBuilder.vertex((double)width, 0, zPos).uv(1.0F, 0.0F).endVertex();
        bufferBuilder.vertex(0, 0, zPos).uv(0.0F, 0.0F).endVertex();
        tessellator.end();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.popPose();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.disableColorLogicOp();
    }
}