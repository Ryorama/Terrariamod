package kmerrill285.trewrite.entities.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelPillar<T extends Entity> extends EntityModel<T> {
	private final RendererModel bb_main;

	public ModelPillar() {
		textureWidth = 256;
		textureHeight = 256;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -50.0F, -158.0F, 0.0F, 50, 150, 50, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 150, 0, -36.0F, -8.0F, 16.0F, 20, 20, 20, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 200, 71, -31.0F, -183.0F, 0.0F, 10, 25, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 200, 40, -46.0F, -183.0F, 0.0F, 10, 25, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 156, 200, -14.0F, -183.0F, 0.0F, 10, 25, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 124, 200, -46.0F, -183.0F, 44.0F, 10, 25, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 92, 200, -31.0F, -183.0F, 44.0F, 10, 25, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 60, 200, -14.0F, -183.0F, 44.0F, 10, 25, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 30, 200, -6.0F, -183.0F, 28.0F, 6, 25, 9, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 200, -6.0F, -183.0F, 12.0F, 6, 25, 9, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 191, 191, -50.0F, -183.0F, 12.0F, 6, 25, 9, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -50.0F, -183.0F, 28.0F, 6, 25, 9, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}