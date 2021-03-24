package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.hostile.EntityDemonEye;

public class ModelDemonEye extends CustomModel<EntityDemonEye> {
	private final CubePart shape1;
	private final CubePart shape2;
	private final CubePart shape2_1;
	private final CubePart shape2_2;
	private final CubePart shape2_3;
	private final CubePart shape2_4;

	public ModelDemonEye(int textureHeight, int textureWidth) {
		super(textureHeight, textureWidth);
		textureWidth = 128;
		textureHeight = 128;

		shape1 = new CubePart(this);
		shape1.setRotationPoint(-4.0F, 8.0F, -4.0F);
		shape1.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		shape2 = new CubePart(this);
		shape2.setRotationPoint(-3.0F, 14.0F, 4.0F);
		shape2.setTextureOffset(32, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);

		shape2_1 = new CubePart(this);
		shape2_1.setRotationPoint(2.0F, 14.0F, 4.0F);
		shape2_1.setTextureOffset(32, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);

		shape2_2 = new CubePart(this);
		shape2_2.setRotationPoint(-0.5F, 11.5F, 4.0F);
		shape2_2.setTextureOffset(32, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);

		shape2_3 = new CubePart(this);
		shape2_3.setRotationPoint(-3.0F, 9.0F, 4.0F);
		shape2_3.setTextureOffset(32, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);

		shape2_4 = new CubePart(this);
		shape2_4.setRotationPoint(2.0F, 9.0F, 4.0F);
		shape2_4.setTextureOffset(32, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
	
		this.getTexturedModelData();
	}

	public void setRotationAngle(CubePart modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}