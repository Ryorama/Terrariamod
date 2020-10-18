package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.bosses.destroyer.EntityDestroyerHead;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports


public class ModelDestroyerHead extends EntityModel<EntityDestroyerHead> {
	private final RendererModel bb_main;

	public ModelDestroyerHead() {
		textureWidth = 64;
		textureHeight = 64;

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -6.0F, -13.0F, -7.0F, 12, 7, 14, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 20, 21, -4.0F, -8.0F, 7.0F, 1, 1, 9, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 21, 3.0F, -8.0F, 7.0F, 1, 1, 9, 0.0F, false));
	}

	@Override
	public void render(EntityDestroyerHead entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}