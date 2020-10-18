package kmerrill285.trewrite.entities.models.plantera;

import kmerrill285.trewrite.entities.monsters.bosses.plantera.Spore;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelSpore extends EntityModel<Spore> {
	private final RendererModel bone;
	private final RendererModel bb_main;

	public ModelSpore() {
		textureWidth = 16;
		textureHeight = 16;

		bone = new RendererModel(this);
		bone.setRotationPoint(-0.5F, 18.7F, -0.8F);
		setRotationAngle(bone, -0.2182F, 0.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 4, -1.0F, -1.0F, 0.0F, 1, 2, 1, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.0F, -10.0F, -1.0F, 2, 2, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 4, 4, -1.5F, -8.0F, -0.6F, 1, 2, 1, 0.0F, false));
	}

	@Override
	public void render(Spore entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}