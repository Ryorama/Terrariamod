package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.EntityDemon;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelDemon extends EntityModel<EntityDemon> {
	private final RendererModel bone2;
	private final RendererModel bone;
	private final RendererModel bb_main;

	public ModelDemon() {
		textureWidth = 64;
		textureHeight = 64;

		bone2 = new RendererModel(this);
		bone2.setRotationPoint(-1.5F, 3.1F, -1.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.3142F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, 3.6F, -4.0F, 0.0F, 1, 4, 2, 0.0F, false));

		bone = new RendererModel(this);
		bone.setRotationPoint(-2.3F, 2.0F, -1.0F);
		setRotationAngle(bone, 0.0F, 0.0F, 0.192F);
		bone.cubeList.add(new ModelBox(bone, 18, 0, -0.6871F, -3.8664F, 0.0F, 1, 4, 2, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 30, 11, -2.0F, -7.0F, -1.0F, 1, 5, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 28, 29, 1.0F, -7.0F, -1.0F, 1, 5, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 11, -3.0F, -16.0F, -2.0F, 6, 9, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -3.0F, -21.0F, -3.0F, 6, 5, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 12, 18, 2.0F, -15.0F, 2.0F, 0, 7, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 18, -2.0F, -15.0F, 2.0F, 0, 7, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 0, -2.7F, -25.0F, -1.0F, 1, 5, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 24, 24, 1.6F, -25.0F, -1.0F, 1, 5, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 20, 13, -4.0F, -13.0F, -5.0F, 1, 2, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 18, 5, 3.0F, -13.0F, -5.0F, 1, 2, 6, 0.0F, false));
	}

	@Override
	public void render(EntityDemon entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone2.render(f5);
		bone.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}