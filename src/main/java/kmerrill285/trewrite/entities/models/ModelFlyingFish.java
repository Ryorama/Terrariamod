package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.EntityFlyingFish;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelFlyingFish extends EntityModel<EntityFlyingFish> {
	private final RendererModel bb_main;

	public ModelFlyingFish() {
		textureWidth = 16;
		textureHeight = 16;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -8.0F, -3.0F, 2, 2, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 5, 1.0F, -10.0F, -2.3F, 0, 2, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -1.0F, -10.0F, -2.3F, 0, 2, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 0.0F, -7.0F, -4.3F, 0, 1, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 0.0F, -6.0F, -4.3F, 0, 1, 1, 0.0F, false));
	}

	@Override
	public void render(EntityFlyingFish entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}