package kmerrill285.trewrite.entities.models.wyvern;

import kmerrill285.trewrite.entities.wyvern.EntityWyvernHead;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelWyvernHead extends EntityModel<EntityWyvernHead> {
	private final RendererModel bb_main;

	public ModelWyvernHead() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -8.0F, -10.0F, -4.0F, 18, 2, 9, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 17, -8.0F, -13.0F, -4.0F, 10, 3, 9, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 11, -8.0F, -8.0F, -2.0F, 17, 1, 5, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 29, -5.0F, -11.0F, -2.0F, 14, 1, 5, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 29, 17, -8.0F, -17.0F, 0.3F, 8, 4, 0, 0.0F, false));
	}

	@Override
	public void render(EntityWyvernHead entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}