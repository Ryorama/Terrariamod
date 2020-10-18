package kmerrill285.trewrite.entities.models.skeletron;

import kmerrill285.trewrite.entities.monsters.bosses.skeletron.EntitySkeletronLeftArm;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports


public class ModelSkeletronArmLeft extends EntityModel<EntitySkeletronLeftArm> {
	private final RendererModel bone;
	private final RendererModel bone3;

	public ModelSkeletronArmLeft() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new RendererModel(this);
		bone.setRotationPoint(-53.0F, 70.0F, 14.0F);
		setRotationAngle(bone, 0.0F, 0.0F, -0.3927F);
		bone.cubeList.add(new ModelBox(bone, 0, 0, 15.5152F, -36.4259F, 11.0F, 51, 8, 11, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 19, 15.8999F, -48.3602F, 11.0F, 51, 8, 11, 0.0F, false));

		bone3 = new RendererModel(this);
		bone3.setRotationPoint(-7.0F, -30.0F, 14.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, -1.789F);
		bone3.cubeList.add(new ModelBox(bone3, 0, 57, -50.5015F, -8.5882F, 11.0F, 24, 8, 11, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 38, -58.662F, -59.5627F, 11.0F, 24, 8, 11, 0.0F, false));
	}

	@Override
	public void render(EntitySkeletronLeftArm entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bone3.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}