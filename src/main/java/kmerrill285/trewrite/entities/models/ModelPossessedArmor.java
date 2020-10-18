package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.EntityPossessedArmor;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

public class ModelPossessedArmor extends EntityModel<EntityPossessedArmor> {
	private final RendererModel bb_main;

	public ModelPossessedArmor() {
		textureWidth = 128;
		textureHeight = 128;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 38, 38, -6.0F, -9.0F, -2.0F, 12, 9, 5, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 36, 0, -6.0F, -3.0F, -4.0F, 12, 3, 2, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 24, -6.0F, -24.0F, -3.0F, 12, 15, 7, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -6.0F, -36.0F, -5.5F, 12, 12, 12, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 46, -8.0F, -23.0F, -3.0F, 2, 12, 7, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 41, 17, 6.0F, -23.0F, -3.0F, 2, 12, 7, 0.0F, false));
	}

	@Override
	public void render(EntityPossessedArmor entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}