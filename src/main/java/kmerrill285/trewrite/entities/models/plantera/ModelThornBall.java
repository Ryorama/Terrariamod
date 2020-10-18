package kmerrill285.trewrite.entities.models.plantera;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

public class ModelThornBall extends EntityModel {
	private final RendererModel bone;
	private final RendererModel bone2;
	private final RendererModel bone3;
	private final RendererModel bone4;
	private final RendererModel bone5;
	private final RendererModel bone6;
	private final RendererModel bone7;
	private final RendererModel bone8;
	private final RendererModel bb_main;

	public ModelThornBall() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new RendererModel(this);
		bone.setRotationPoint(-1.0F, 27.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, -0.3927F);
		bone.cubeList.add(new ModelBox(bone, 34, 20, 5.389F, -3.9281F, -2.0F, 2, 5, 3, 0.0F, false));

		bone2 = new RendererModel(this);
		bone2.setRotationPoint(-5.0949F, -8.6047F, 0.0F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.7418F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 33, 9.5985F, -14.0907F, -2.0F, 2, 5, 3, 0.0F, false));

		bone3 = new RendererModel(this);
		bone3.setRotationPoint(1.0F, 27.0F, 0.0F);
		setRotationAngle(bone3, 3.1416F, 0.0F, -2.7489F);
		bone3.cubeList.add(new ModelBox(bone3, 19, 32, 5.389F, -3.9281F, -2.0F, 2, 5, 3, 0.0F, false));

		bone4 = new RendererModel(this);
		bone4.setRotationPoint(-5.0949F, -8.6047F, 0.0F);
		bone3.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.7418F);
		bone4.cubeList.add(new ModelBox(bone4, 31, 31, 9.5985F, -14.0907F, -2.0F, 2, 5, 3, 0.0F, false));

		bone5 = new RendererModel(this);
		bone5.setRotationPoint(-1.0F, 27.0F, -1.0F);
		setRotationAngle(bone5, 1.4572F, -1.1758F, -1.4659F);
		bone5.cubeList.add(new ModelBox(bone5, 30, 0, 5.389F, -3.9281F, -2.0F, 2, 5, 3, 0.0F, false));

		bone6 = new RendererModel(this);
		bone6.setRotationPoint(-5.0949F, -8.6047F, 0.0F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.7418F);
		bone6.cubeList.add(new ModelBox(bone6, 9, 28, 9.5985F, -14.0907F, -2.0F, 2, 5, 3, 0.0F, false));

		bone7 = new RendererModel(this);
		bone7.setRotationPoint(1.0F, 27.0F, 1.0F);
		setRotationAngle(bone7, -1.6844F, 1.1758F, -1.6757F);
		bone7.cubeList.add(new ModelBox(bone7, 24, 24, 5.389F, -3.9281F, -2.0F, 2, 5, 3, 0.0F, false));

		bone8 = new RendererModel(this);
		bone8.setRotationPoint(-5.0949F, -8.6047F, 0.0F);
		bone7.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, 0.7418F);
		bone8.cubeList.add(new ModelBox(bone8, 0, 0, 9.5985F, -14.0907F, -2.0F, 2, 5, 3, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -5.0F, -11.0F, -5.0F, 10, 10, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 12, 20, -1.4F, -16.0F, -1.7F, 3, 5, 3, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 20, -1.4F, -3.0F, -1.7F, 3, 6, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bone3.render(f5);
		bone5.render(f5);
		bone7.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}