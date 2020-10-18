package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.EntityToxicSludge;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelToxicSludge extends EntityModel<EntityToxicSludge> {
	private final RendererModel bb_main;

	public ModelToxicSludge() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -7.0F, -2.0F, -6.0F, 14, 2, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 16, 30, 4.0F, -12.0F, -5.0F, 2, 10, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 14, -6.0F, -12.0F, 1.0F, 12, 10, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 28, -6.0F, -12.0F, -5.0F, 2, 10, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 26, -4.0F, -12.0F, -5.0F, 8, 4, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 3, -3.0F, -8.0F, -5.0F, 1, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 2.0F, -8.0F, -5.0F, 1, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, -5.0F, -5.0F, 8, 3, 10, 0.0F, false));
	}

	@Override
	public void render(EntityToxicSludge entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}