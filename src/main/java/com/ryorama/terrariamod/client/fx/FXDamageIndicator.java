package com.ryorama.terrariamod.client.fx;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.systems.RenderSystem;
import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.client.core.ExtendedTexture;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.VertexFormat.DrawMode;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;

public class FXDamageIndicator extends SpriteBillboardParticle {

	public static Identifier sprite = new Identifier(TerrariaMod.modid, "textures/particle/damage_one.png");
	
	public FXDamageIndicator(ClientWorld world, double x, double y, double z) {
		super(world, x, y, z);
	}

	@Override
	public void tick() {
		this.velocityY = 0.01f;
		
		this.setVelocity(velocityX, velocityY, velocityZ);
	}
	
	@Override
	public ParticleTextureSheet getType() {
		return NORMAL_RENDER;
	}
	
	private static void beginRenderCommon(BufferBuilder bufferBuilder, TextureManager textureManager) {
		MatrixStack matrixStack = RenderSystem.getModelViewStack();
        matrixStack.push();

        RenderSystem.applyModelViewMatrix();
        RenderSystem.setShaderTexture(0, sprite);
        RenderSystem.enableBlend();
	    RenderSystem.disableDepthTest();
	    RenderSystem.depthMask(false);
	    RenderSystem.defaultBlendFunc();
	    RenderSystem.setShader(GameRenderer::getPositionTexShader);
	
	    RenderSystem.setShaderTexture(0, sprite);
	    Tessellator tessellator = Tessellator.getInstance();
	    bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
	    bufferBuilder.vertex(0, 16, 0).texture(0.0F, 1.0F).next();
	    bufferBuilder.vertex(16, 16, 0).texture(1.0F, 1.0F).next();
	    bufferBuilder.vertex(16, 0, 0).texture(1.0F, 0.0F).next();
	    bufferBuilder.vertex(0, 0, 0).texture(0.0F, 0.0F).next();
	    tessellator.draw();
	    RenderSystem.depthMask(true);
	    RenderSystem.enableDepthTest();
	    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.disableColorLogicOp();
	}

	private static void endRenderCommon() {
		MatrixStack matrixStack = RenderSystem.getModelViewStack();
        matrixStack.push();

        RenderSystem.applyModelViewMatrix();
        RenderSystem.setShaderTexture(0, sprite);
        RenderSystem.enableBlend();
	    RenderSystem.disableDepthTest();
	    RenderSystem.depthMask(false);
	    RenderSystem.defaultBlendFunc();
	    RenderSystem.setShader(GameRenderer::getPositionTexShader);
	
	    RenderSystem.setShaderTexture(0, sprite);
	    Tessellator tessellator = Tessellator.getInstance();
	    BufferBuilder bufferBuilder = tessellator.getBuffer();
	    bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
	    bufferBuilder.vertex(0, 16, 0).texture(0.0F, 1.0F).next();
	    bufferBuilder.vertex(16, 16, 0).texture(1.0F, 1.0F).next();
	    bufferBuilder.vertex(16, 0, 0).texture(1.0F, 0.0F).next();
	    bufferBuilder.vertex(0, 0, 0).texture(0.0F, 0.0F).next();
	    tessellator.draw();
	    RenderSystem.depthMask(true);
	    RenderSystem.enableDepthTest();
	    RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.disableColorLogicOp();
	}
	
	public static final ParticleTextureSheet NORMAL_RENDER = new ParticleTextureSheet() {
		@Override
		public void begin(BufferBuilder bufferBuilder, TextureManager textureManager) {
			beginRenderCommon(bufferBuilder, textureManager);
		}

		@Override
		public void draw(Tessellator tessellator) {
			endRenderCommon();
		}

		@Override
		public String toString() {
			return "terrariamod:damage_indicator";
		}
	};
}
