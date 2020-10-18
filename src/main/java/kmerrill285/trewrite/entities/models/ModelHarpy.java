package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.EntityHarpy;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelHarpy extends EntityModel<EntityHarpy> {
	private final RendererModel bone;
	private final RendererModel bb_main;

	public ModelHarpy() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new RendererModel(this);
		bone.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(bone, -0.6981F, 0.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -3.0F, -7.0F, -10.0F, 7, 4, 9, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 26, 26, -3.0F, -6.6693F, -1.4238F, 3, 3, 6, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 23, 0, 1.0F, -6.6693F, -1.4238F, 3, 3, 6, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 16, 17, 4.0F, -10.9284F, -7.7019F, 0, 6, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 17, -3.0F, -10.9284F, -7.7019F, 0, 6, 8, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 13, -3.0F, -21.6679F, -8.1549F, 7, 6, 6, 0.0F, false));
	}

	@Override
	public void render(EntityHarpy entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}