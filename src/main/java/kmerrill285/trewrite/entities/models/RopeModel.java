package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.EntityRope;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class RopeModel extends EntityModel<EntityRope> {
	private final RendererModel bone;

	public RopeModel() {
		textureWidth = 16;
		textureHeight = 16;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, -12.0F, -2.0F, 4, 12, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, -2.0F, -16.0F, -2.0F, 4, 4, 4, 0.0F, false));
	}

	@Override
	public void render(EntityRope entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}
	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}