package kmerrill285.trewrite.entities.models.skeletron;

import kmerrill285.trewrite.entities.monsters.bosses.skeletron.EntitySkeletronRightArm;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelSkeletronArmRight extends EntityModel<EntitySkeletronRightArm> {
	private final RendererModel bone;
	private final RendererModel bone3;

	public ModelSkeletronArmRight() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new RendererModel(this);
		bone.setRotationPoint(-53.0F, 70.0F, 14.0F);
		setRotationAngle(bone, 0.0F, 0.0F, 0.2618F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, 50.3178F, -81.2618F, 2.0F, 51, 8, 11, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 19, 50.7025F, -93.1961F, 2.0F, 51, 8, 11, 0.0F, false));

		bone3 = new RendererModel(this);
		bone3.setRotationPoint(46.0F, 19.0F, 14.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, -1.0908F);
		bone3.cubeList.add(new ModelBox(bone3, 0, 57, -1.6348F, 15.2001F, 2.0F, 24, 8, 11, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 38, -9.7953F, -35.7745F, 2.0F, 24, 8, 11, 0.0F, false));
	}

	@Override
	public void render(EntitySkeletronRightArm entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bone3.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}