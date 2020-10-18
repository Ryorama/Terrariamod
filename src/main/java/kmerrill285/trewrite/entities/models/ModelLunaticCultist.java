package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.bosses.LunaticCultist;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports


public class ModelLunaticCultist extends EntityModel<LunaticCultist> {
	private final RendererModel bone;
	private final RendererModel bone2;
	private final RendererModel bb_main;

	public ModelLunaticCultist() {
		textureWidth = 64;
		textureHeight = 64;

		bone = new RendererModel(this);
		bone.setRotationPoint(-11.4F, 15.2F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, -0.3927F);
		bone.cubeList.add(new ModelBox(bone, 30, 0, 0.3066F, -10.5412F, -3.0F, 6, 3, 6, 0.0F, false));

		bone2 = new RendererModel(this);
		bone2.setRotationPoint(13.0F, 16.8F, 1.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.2443F);
		bone2.cubeList.add(new ModelBox(bone2, 40, 9, -5.0F, -8.0F, -1.6F, 7, 2, 1, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 24, -5.0F, -7.0F, -5.0F, 10, 7, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 34, 35, -10.0F, -21.0F, -3.0F, 6, 3, 6, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -5.0F, -21.0F, -5.0F, 10, 14, 10, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 32, 16, 5.0F, -20.0F, -4.0F, 1, 8, 8, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 40, 12, 6.0F, -16.0F, -0.6F, 5, 2, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 14, 41, -3.0F, -15.0F, 5.0F, 6, 10, 1, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 41, -3.0F, -15.0F, -6.0F, 6, 10, 1, 0.0F, false));
	}

	@Override
	public void render(LunaticCultist entity, float f, float f1, float f2, float f3, float f4, float f5) {
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