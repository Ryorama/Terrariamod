package kmerrill285.trewrite.entities.models.plantera;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelPlanteraPhase2 extends EntityModel {
	private final RendererModel bone;
	private final RendererModel bb_main;
	private final RendererModel bone2;
	private final RendererModel bone3;
	private final RendererModel bone4;
	private final RendererModel bone5;

	public ModelPlanteraPhase2() {
		textureWidth = 1024;
		textureHeight = 1024;

		bone = new RendererModel(this);
		bone.setRotationPoint(60.0F, 11.0F, -26.0F);
		setRotationAngle(bone, 0.0F, 1.5708F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 228, 394, -128.0F, -114.0F, -116.0F, 6, 108, 108, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 564, 569, -134.0F, -108.0F, -108.0F, 8, 92, 92, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bb_main, 0.0F, 1.5708F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -96.0F, -134.0F, -62.0F, 120, 120, 120, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 342, 24.0F, -128.0F, -54.0F, 6, 108, 108, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 292, 240, 58.9999F, -53.0F, -21.9998F, 16, 16, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 292, 292, 38.9999F, -53.0F, -21.9999F, 16, 16, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 272, 38.9998F, -53.0F, 0.0001F, 16, 16, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 240, 38.9997F, -53.0F, 18.0001F, 16, 16, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 48, 80, 59.9997F, -53.0F, 18.0002F, 16, 16, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 64, 59.9997F, -116.0F, 18.0002F, 16, 16, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 48, 16, 59.9999F, -116.0F, -21.9998F, 16, 16, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 48, 48, 41.9999F, -116.0F, -21.9999F, 16, 16, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 32, 41.9997F, -116.0F, 15.0001F, 16, 16, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 41.9998F, -116.0F, -3.9999F, 16, 16, 16, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 304, 62.9999F, -61.0F, -18.9999F, 8, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 292, 272, 42.9999F, -61.0F, -18.9999F, 8, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 64, 288, 42.9998F, -61.0F, 3.0001F, 8, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 48, 272, 42.9997F, -61.0F, 21.0001F, 8, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 64, 256, 63.9997F, -61.0F, 21.0001F, 8, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 48, 240, 63.9997F, -101.0F, 22.0001F, 8, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 24, 104, 63.9999F, -101.0F, -17.9999F, 8, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 96, 45.9999F, -101.0F, -17.9999F, 8, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 80, 0, 45.9997F, -101.0F, 19.0001F, 8, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 48, 0, 45.9998F, -101.0F, 0.0001F, 8, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 364, 569, 30.0F, -122.0F, -46.0F, 8, 92, 92, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 292, 292, -86.0F, -140.0F, -52.0F, 98, 6, 96, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 240, -86.0F, -14.0F, -52.0F, 98, 6, 96, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 614, 113, -86.0F, -130.0F, 58.0F, 98, 108, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 584, 261, -86.0F, -130.0F, -70.0F, 98, 108, 8, 0.0F, false));

		bone2 = new RendererModel(this);
		bone2.setRotationPoint(13.0F, -5.0F, 0.0F);
		bb_main.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.2618F);
		bone2.cubeList.add(new ModelBox(bone2, 360, 0, 30.0F, -113.0F, -46.0F, 68, 21, 92, 0.0F, false));

		bone3 = new RendererModel(this);
		bone3.setRotationPoint(-32.0F, 59.0F, -0.0001F);
		bb_main.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, 0.1745F);
		bone3.cubeList.add(new ModelBox(bone3, 388, 148, 30.0F, -113.0F, -46.0F, 67, 21, 92, 0.0F, false));

		bone4 = new RendererModel(this);
		bone4.setRotationPoint(-32.0005F, -77.0F, 134.9999F);
		bb_main.addChild(bone4);
		setRotationAngle(bone4, 1.5708F, -0.2182F, 0.0F);
		bone4.cubeList.add(new ModelBox(bone4, 456, 456, 30.0F, -113.0F, -46.0F, 63, 21, 92, 0.0F, false));

		bone5 = new RendererModel(this);
		bone5.setRotationPoint(16.9997F, -77.0F, 77.0001F);
		bb_main.addChild(bone5);
		setRotationAngle(bone5, 1.5708F, 0.2182F, 0.0F);
		bone5.cubeList.add(new ModelBox(bone5, 0, 558, 31.2987F, -118.8578F, -46.0F, 59, 21, 92, 0.0F, false));
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