package kmerrill285.trewrite.entities.models.golem;

import kmerrill285.trewrite.entities.monsters.bosses.golem.GolemHead;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelGolemHead extends EntityModel<GolemHead> {
	private final RendererModel bb_main;

	public ModelGolemHead() {
		textureWidth = 256;
		textureHeight = 256;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -40.0F, -47.0F, 0.0F, 40, 40, 40, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 80, -37.0F, -52.0F, 3.0F, 33, 5, 35, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 108, 108, -34.0F, -62.0F, 6.0F, 27, 10, 28, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 80, -35.0F, -21.0F, -4.0F, 29, 5, 35, 0.0F, false));
	}

	@Override
	public void render(GolemHead entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}