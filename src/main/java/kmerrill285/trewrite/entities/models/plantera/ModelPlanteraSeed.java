package kmerrill285.trewrite.entities.models.plantera;

import kmerrill285.trewrite.entities.monsters.bosses.plantera.PlanteraSeed;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelPlanteraSeed extends EntityModel<PlanteraSeed> {
	private final RendererModel bb_main;

	public ModelPlanteraSeed() {
		textureWidth = 16;
		textureHeight = 16;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.0F, -12.0F, -3.0F, 2, 1, 4, 0.0F, false));
	}

	@Override
	public void render(PlanteraSeed entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}