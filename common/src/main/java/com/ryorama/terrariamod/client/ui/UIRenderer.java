package com.ryorama.terrariamod.client.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class UIRenderer {

    public static int scaledWidth;
    public static int scaledHeight;

    public static final MinecraftClient client = MinecraftClient.getInstance();

    public static void renderOverlay(Identifier texture, float opacity, float width, float height, float x, float y, int zPos) {

        scaledWidth = client.getWindow().getScaledWidth();
        scaledHeight = client.getWindow().getScaledHeight();

        MatrixStack matrixStack = RenderSystem.getModelViewStack();

        matrixStack.push();
        matrixStack.translate(x, y, zPos);
        RenderSystem.applyModelViewMatrix();
        RenderSystem.setShaderTexture(0, texture);

        RenderSystem.enableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, opacity);
        RenderSystem.setShaderTexture(0, texture);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        bufferBuilder.color(0, 0, 0, opacity);
        bufferBuilder.vertex(0, (double)height, zPos).texture(0.0F, 1.0F).next();
        bufferBuilder.vertex((double)width, (double)height, zPos).texture(1.0F, 1.0F).next();
        bufferBuilder.vertex((double)width, 0, zPos).texture(1.0F, 0.0F).next();
        bufferBuilder.vertex(0, 0, zPos).texture(0.0F, 0.0F).next();
        tessellator.draw();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.disableColorLogicOp();
    }
}