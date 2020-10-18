package kmerrill285.trewrite.entities.models.skeletron;

import kmerrill285.trewrite.entities.monsters.bosses.skeletron.EntitySkeletronRightArm2;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelSkeletronRightArm2 extends EntityModel<EntitySkeletronRightArm2> {
	private final RendererModel bone2;
	private final RendererModel bone;
	private final RendererModel bone3;

	public ModelSkeletronRightArm2() {
		textureWidth = 256;
		textureHeight = 256;

		bone2 = new RendererModel(this);
		bone2.setRotationPoint(92.0F, 70.0F, 14.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, -1.9635F);
		bone2.cubeList.add(new ModelBox(bone2, 18, 62, -4.2397F, -95.5347F, -20.0F, 5, 31, 4, 0.0F, false));

		bone = new RendererModel(this);
		bone.setRotationPoint(25.3F, 35.0F, 14.0F);
		setRotationAngle(bone, 3.1416F, 0.0F, -0.9599F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, -58.0F, -59.0F, 11.0F, 63, 8, 11, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 19, -58.2439F, -45.4007F, 11.0F, 57, 8, 11, 0.0F, false));

		bone3 = new RendererModel(this);
		bone3.setRotationPoint(98.0F, 84.0F, 14.0F);
		setRotationAngle(bone3, 0.0F, 3.1416F, -2.4871F);
		bone3.cubeList.add(new ModelBox(bone3, 0, 38, -50.0F, -64.0F, 11.0F, 24, 13, 11, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 62, -31.9677F, -91.1211F, 15.0F, 5, 43, 4, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 18, 62, -48.3689F, -90.1408F, 15.0F, 5, 42, 4, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 36, 62, -39.7716F, -91.4353F, 15.0F, 5, 40, 4, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 59, 59, -50.5692F, -6.5483F, 11.0F, 23, 8, 11, 0.0F, false));
	}

	@Override
	public void render(EntitySkeletronRightArm2 entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone2.render(f5);
		bone.render(f5);
		bone3.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}