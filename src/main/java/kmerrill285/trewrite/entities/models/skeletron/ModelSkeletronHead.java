package kmerrill285.trewrite.entities.models.skeletron;

import kmerrill285.trewrite.entities.monsters.bosses.skeletron.EntitySkeletronHead;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelSkeletronHead extends EntityModel<EntitySkeletronHead> {
	private final RendererModel bb_main;

	public ModelSkeletronHead() {
		textureWidth = 512;
		textureHeight = 512;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -64.0F, -82.0F, 0.0F, 64, 64, 64, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 156, 156, -58.0F, -19.0F, 7.0F, 50, 6, 50, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 186, -47.0F, -13.0F, 17.0F, 30, 6, 30, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 128, -58.0F, -87.0F, 6.0F, 51, 6, 52, 0.0F, false));
	}

	@Override
	public void render(EntitySkeletronHead entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}