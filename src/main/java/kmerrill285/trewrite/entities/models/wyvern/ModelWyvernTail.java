package kmerrill285.trewrite.entities.models.wyvern;

import kmerrill285.trewrite.entities.wyvern.EntityWyvernTail;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelWyvernTail extends EntityModel<EntityWyvernTail> {
	private final RendererModel bone;
	private final RendererModel bb_main;

	public ModelWyvernTail() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new RendererModel(this);
		bone.setRotationPoint(-13.0F, 14.0F, 0.0F);
		setRotationAngle(bone, -1.5708F, 0.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 24, -4.0F, -3.0F, 0.0F, 9, 6, 0, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -5.0F, -13.0F, -4.0F, 14, 5, 9, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 14, -4.0F, -8.0F, -2.0F, 13, 1, 5, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 20, -5.0F, -17.0F, 0.3F, 14, 4, 0, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 30, -7.0F, -12.0F, -1.7F, 2, 3, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -11.0F, -1.0F, 1, 1, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 18, 24, -17.0F, -13.0F, 0.0F, 9, 6, 0, 0.0F, false));
	}

	@Override
	public void render(EntityWyvernTail entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}