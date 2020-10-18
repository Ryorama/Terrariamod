package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.bosses.EntityEyeOfCthulhu;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelEyeOfCthulhuPhase2 extends EntityModel<EntityEyeOfCthulhu> {
	private final RendererModel bone;
	private final RendererModel bb_main;

	public ModelEyeOfCthulhuPhase2() {
		textureWidth = 512;
		textureHeight = 512;

		bone = new RendererModel(this);
		bone.setRotationPoint(30.0F, 17.0F, -12.0F);
		setRotationAngle(bone, 0.0F, 1.5708F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 114, 197, -63.0F, -57.0F, -58.0F, 3, 54, 54, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 228, 228, -66.0F, -54.0F, -54.0F, 4, 46, 46, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bb_main, 0.0F, 1.5708F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -48.0F, -67.0F, -31.0F, 60, 60, 60, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 146, 146, -43.0F, -70.0F, -26.0F, 49, 3, 48, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 120, -43.0F, -7.0F, -26.0F, 49, 3, 48, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 282, 197, -43.0F, -65.0F, 29.0F, 49, 54, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 279, -43.0F, -65.0F, -35.0F, 49, 54, 4, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 292, 173, -101.0F, -57.0F, 10.0F, 47, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 292, 157, -101.0F, -57.0F, -20.0F, 47, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 292, 141, -101.0F, -26.0F, -20.0F, 47, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 282, 255, -101.0F, -26.0F, 10.0F, 47, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 60, 171, -101.0F, -43.0F, -5.0F, 47, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 171, 12.0F, -64.0F, -26.0F, 3, 52, 51, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 256, 5, 15.0F, -24.0F, -26.0F, 5, 12, 52, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 180, 0, 20.0F, -19.0F, -26.0F, 12, 5, 52, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 188, 68, 15.0F, -64.0F, -25.0F, 5, 16, 50, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 250, 84, 20.0F, -62.0F, -25.0F, 10, 3, 50, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 180, 20, 21.0F, -27.0F, -26.0F, 11, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 203, 24.0F, -33.0F, -24.0F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 180, 0, 13.0F, -32.0F, -26.0F, 12, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 142, 201, 16.0F, -38.0F, -24.0F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 171, 13.0F, -32.0F, 14.0F, 12, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 198, 198, 16.0F, -38.0F, 16.0F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 146, 124, 20.0F, -27.0F, 14.0F, 12, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 174, 197, 23.0F, -33.0F, 16.0F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 24, 191, 20.0F, -33.0F, -10.2F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 146, 146, 17.0F, -27.0F, -12.2F, 12, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 191, 20.0F, -33.0F, 3.0F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 140, 17.0F, -27.0F, 1.0F, 12, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 120, 13.0F, -57.0F, -26.0F, 12, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 92, 187, 16.0F, -49.0F, -24.0F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 104, 187, 20.0F, -59.0F, -26.0F, 10, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 180, 40, 23.0F, -51.0F, -24.0F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 170, 171, 23.0F, -51.0F, 16.0F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 60, 187, 20.0F, -59.0F, 14.0F, 10, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 36, 40, 16.0F, -49.0F, 16.0F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 40, 13.0F, -57.0F, 14.0F, 12, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 36, 20, 20.0F, -51.0F, -9.7F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 20, 17.0F, -59.0F, -11.7F, 12, 8, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 36, 0, 20.0F, -51.0F, 3.0F, 6, 6, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, 17.0F, -59.0F, 1.0F, 12, 8, 12, 0.0F, false));
	}

	@Override
	public void render(EntityEyeOfCthulhu entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}