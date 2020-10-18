package kmerrill285.trewrite.entities.models.boc;

import kmerrill285.trewrite.entities.monsters.bosses.boc.EntityBrainOfCthulhu;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelBrainOfCthulhu extends EntityModel<EntityBrainOfCthulhu> {
	private final RendererModel bone;
	private final RendererModel bone2;
	private final RendererModel bb_main;

	public ModelBrainOfCthulhu() {
		textureWidth = 512;
		textureHeight = 512;

		bone = new RendererModel(this);
		bone.setRotationPoint(51.0F, 40.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, -0.2182F);
		

		bone2 = new RendererModel(this);
		bone2.setRotationPoint(6.0F, 54.0F, 0.0F);
		setRotationAngle(bone2, -0.0873F, 0.0F, 0.1309F);
		

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 142, 44, -66.0F, -60.0F, 0.0F, 42, 5, 58, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 140, 146, -23.0F, -60.0F, 0.0F, 41, 5, 58, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -66.0F, -55.0F, 0.0F, 42, 44, 58, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 102, -23.0F, -55.0F, 0.0F, 41, 44, 58, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 204, -24.0F, -58.0F, 1.0F, 1, 45, 55, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 57, 204, -32.0F, -11.0F, 18.0F, 15, 34, 15, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 203, 116, -35.0F, -7.0F, 15.0F, 21, 9, 21, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 142, 0, -35.0F, 4.0F, 15.0F, 21, 9, 21, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 140, 107, -35.0F, 14.0F, 15.0F, 21, 9, 21, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 40, -27.0F, 23.0F, 23.0F, 5, 5, 5, 0.0F, false));
	}

	@Override
	public void render(EntityBrainOfCthulhu entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bone2.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}