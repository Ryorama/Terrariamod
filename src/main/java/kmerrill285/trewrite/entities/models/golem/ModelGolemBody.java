package kmerrill285.trewrite.entities.models.golem;



import kmerrill285.trewrite.entities.monsters.bosses.golem.GolemBody;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelGolemBody extends EntityModel<GolemBody> {
	private final RendererModel bb_main;

	public ModelGolemBody() {
		textureWidth = 128;
		textureHeight = 128;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 45, 55, -18.0F, -6.0F, 0.0F, 12, 6, 7, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 39, 42, -43.0F, -6.0F, 0.0F, 12, 6, 7, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 71, 76, -37.0F, -14.0F, 0.0F, 6, 8, 7, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 52, 68, -18.0F, -14.0F, 0.0F, 6, 8, 7, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -37.0F, -43.0F, -3.0F, 25, 29, 13, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 26, 68, -44.0F, -34.0F, 1.0F, 7, 18, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 62, -12.0F, -34.0F, 1.0F, 7, 18, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 42, -31.0F, -14.0F, -3.0F, 13, 7, 13, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 62, -5.0F, -38.0F, 1.0F, 4, 14, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 62, -43.0F, -36.0F, 1.0F, 4, 12, 6, 0.0F, false));
	}

	@Override
	public void render(GolemBody entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}