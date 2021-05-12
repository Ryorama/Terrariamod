package com.ryorama.terrariamod.ui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.option.AttackIndicator;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class UIRenderer extends DrawableHelper {
	
	public static UIRenderer instance = new UIRenderer();
	
	public static int scaledWidth;
	public static int scaledHeight;
	
	public static final MinecraftClient client = MinecraftClient.getInstance();
	
	public void renderOverlay(Identifier texture, float opacity, float width, float height, float x, float y, int zPos) {
		
		/*
		scaledWidth = client.getWindow().getScaledWidth();
	    scaledHeight = client.getWindow().getScaledHeight();

	    MatrixStack matrixStack = RenderSystem.getModelViewStack();
	    
        matrixStack.push();
        matrixStack.translate(x, y, zPos);
        RenderSystem.applyModelViewMatrix();
        RenderSystem.setShaderTexture(0, texture);
        
	    RenderSystem.disableDepthTest();
	    RenderSystem.depthMask(false);
	    RenderSystem.defaultBlendFunc();
	    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, opacity);
	    RenderSystem.setShaderTexture(0, texture);
	    Tessellator tessellator = Tessellator.getInstance();
	    BufferBuilder bufferBuilder = tessellator.getBuffer();
	    bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
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
        */
		
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
	    RenderSystem.setShader(GameRenderer::getPositionTexShader);
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
