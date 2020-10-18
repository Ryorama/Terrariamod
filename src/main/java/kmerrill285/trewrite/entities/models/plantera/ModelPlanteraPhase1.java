package kmerrill285.trewrite.entities.models.plantera;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelPlanteraPhase1 extends EntityModel {
	private final RendererModel bone;
	private final RendererModel bb_main;

	public ModelPlanteraPhase1() {
		textureWidth = 1024;
		textureHeight = 1024;

		bone = new RendererModel(this);
		bone.setRotationPoint(60.0F, 11.0F, -26.0F);
		setRotationAngle(bone, 0.0F, 1.5708F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 228, 394, -128.0F, -114.0F, -116.0F, 6, 108, 108, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 480, 0, -134.0F, -108.0F, -108.0F, 8, 92, 92, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bb_main, 0.0F, 1.5708F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -96.0F, -134.0F, -62.0F, 120, 120, 120, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 342, 24.0F, -128.0F, -54.0F, 6, 108, 108, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 456, 456, 30.0F, -122.0F, -46.0F, 8, 92, 92, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 584, 184, 38.0F, -110.0F, -38.0F, 17, 68, 68, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 598, 598, 55.0F, -106.0F, -32.9999F, 17, 58, 58, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 120, 342, 72.0F, -102.0F, -29.9999F, 17, 50, 50, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 292, 292, -86.0F, -140.0F, -52.0F, 98, 6, 96, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 240, -86.0F, -14.0F, -52.0F, 98, 6, 96, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 564, 394, -86.0F, -130.0F, 58.0F, 98, 108, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 558, -86.0F, -130.0F, -70.0F, 98, 108, 8, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}