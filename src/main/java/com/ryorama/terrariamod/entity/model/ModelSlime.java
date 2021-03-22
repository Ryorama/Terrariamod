package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.EntitySlimeBase;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.8.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ModelSlime extends CustomModel<EntitySlimeBase> {
	private final CubePart bb_main;

	public ModelSlime(int textureHeight, int textureWidth) {
		super(textureHeight, textureWidth);
		textureWidth = 128;
		textureHeight = 128;

		bb_main = new CubePart(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(0, 36).addBox(-6.0F, -9.0F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);
		bb_main.setTextureOffset(36, 36).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		bb_main.setTextureOffset(36, 20).addBox(-3.0F, -11.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 20).addBox(-6.0F, -4.0F, -6.0F, 12.0F, 4.0F, 12.0F, 0.0F, false);
		bb_main.setTextureOffset(0, 0).addBox(-7.0F, -7.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);
	}

	public void setRotationAngle(CubePart modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}