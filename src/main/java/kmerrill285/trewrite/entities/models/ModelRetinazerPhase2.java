package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.bosses.twins.Ratinizer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelRetinazerPhase2 extends EntityModel<Ratinizer> {
	private final RendererModel bone;
	private final RendererModel bb_main;

	public ModelRetinazerPhase2() {
		textureWidth = 512;
		textureHeight = 512;

		bone = new RendererModel(this);
		bone.setRotationPoint(30.0F, 17.0F, -13.0F);
		setRotationAngle(bone, 0.0F, 1.5708F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 114, 197, -64.0F, -57.0F, -58.0F, 3, 54, 54, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 240, 0, -67.0F, -54.0F, -54.0F, 4, 46, 46, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bb_main, 0.0F, 1.5708F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -48.0F, -67.0F, -31.0F, 60, 60, 60, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 171, 12.0F, -64.0F, -27.0F, 3, 54, 54, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 228, 228, 15.0F, -61.0F, -23.0F, 4, 46, 46, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 60, 178, 18.9999F, -49.0F, -9.0F, 30, 19, 19, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 180, 0, 48.9999F, -50.0F, -9.9999F, 16, 21, 22, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 146, 146, -43.0F, -70.0F, -26.0F, 49, 3, 48, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 120, -43.0F, -7.0F, -26.0F, 49, 3, 48, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 282, 197, -43.0F, -65.0F, 29.0F, 49, 54, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 279, -43.0F, -65.0F, -35.0F, 49, 54, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 282, 255, -101.0F, -57.0F, 10.0F, 47, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 248, 128, -101.0F, -57.0F, -20.0F, 47, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 248, 108, -101.0F, -26.0F, -20.0F, 47, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 240, 92, -101.0F, -26.0F, 10.0F, 47, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 146, 120, -101.0F, -43.0F, -5.0F, 47, 8, 8, 0.0F, false));
	}

	@Override
	public void render(Ratinizer entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}