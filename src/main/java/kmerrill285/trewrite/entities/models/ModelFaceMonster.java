package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.EntityFaceMonster;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports


public class ModelFaceMonster extends EntityModel<EntityFaceMonster> {
	private final RendererModel Head;
	private final RendererModel Body;
	private final RendererModel Legs;

	public ModelFaceMonster() {
		textureWidth = 64;
		textureHeight = 64;

		Head = new RendererModel(this);
		Head.setRotationPoint(0.0F, 24.0F, 0.0F);
		Head.cubeList.add(new ModelBox(Head, 40, 7, -3.0F, -18.0F, -1.0F, 4, 1, 3, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 41, 42, -3.0F, -23.0F, 3.0F, 4, 5, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 40, -5.0F, -23.0F, -2.0F, 1, 5, 4, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 22, 36, 2.0F, -23.0F, -2.0F, 1, 5, 4, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 12, 30, -4.0F, -23.0F, -4.0F, 6, 4, 2, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 46, 36, -3.0F, -20.0F, -5.0F, 4, 1, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 40, 30, -3.0F, -19.0F, -3.0F, 4, 5, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 16, 0, -3.0F, -18.0F, -2.0F, 4, 3, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 43, 11, -3.0F, -14.0F, -4.0F, 4, 4, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 44, 16, -4.0F, -19.0F, -5.0F, 1, 5, 2, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 13, 1.0F, -19.0F, -5.0F, 1, 5, 2, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 20, -2.0F, -10.0F, -5.0F, 2, 1, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 28, 27, -1.0F, -11.0F, -5.0F, 1, 1, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 13, 24, 0.0F, -19.0F, -5.0F, 1, 1, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 28, 24, 0.0F, -18.0F, -6.0F, 1, 2, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 0, -3.0F, -19.0F, -5.0F, 1, 2, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 25, -1.0F, -19.0F, -4.0F, 1, 1, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 35, 30, 1.0F, -14.0F, -5.0F, 1, 2, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 28, 30, -4.0F, -14.0F, -5.0F, 1, 2, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 7, 29, -3.0F, -12.0F, -5.0F, 1, 2, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 29, 0.0F, -12.0F, -5.0F, 1, 2, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 22, -4.0F, -22.0F, -5.0F, 2, 2, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 20, 4, 0.0F, -22.0F, -5.0F, 2, 2, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 20, 10, -4.0F, -24.0F, -5.0F, 6, 2, 1, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 14, 14, -4.0F, -23.0F, -2.0F, 6, 5, 5, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 0, 49, -4.0F, -23.0F, -4.0F, 6, -1, 7, 0.0F, false));
		Head.cubeList.add(new ModelBox(Head, 15, 24, -3.0F, -25.0F, -3.0F, 4, 1, 5, 0.0F, false));

		Body = new RendererModel(this);
		Body.setRotationPoint(0.0F, 24.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 33, 0, -4.0F, -17.0F, 3.0F, 6, 6, 1, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 0, -4.0F, -17.0F, -1.0F, 6, 9, 4, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 29, -5.0F, -17.0F, -1.0F, 1, 6, 5, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 29, 42, -8.0F, -17.0F, 0.0F, 3, 4, 3, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 20, 0, -8.0F, -13.0F, -4.0F, 3, 3, 7, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 0, 19, 3.0F, -13.0F, -4.0F, 3, 3, 7, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 10, 42, 3.0F, -17.0F, 0.0F, 3, 4, 3, 0.0F, false));
		Body.cubeList.add(new ModelBox(Body, 28, 28, 2.0F, -17.0F, -1.0F, 1, 6, 5, 0.0F, false));

		Legs = new RendererModel(this);
		Legs.setRotationPoint(0.0F, 24.0F, 0.0F);
		Legs.cubeList.add(new ModelBox(Legs, 31, 10, -4.0F, -8.0F, -1.0F, 3, 6, 3, 0.0F, false));
		Legs.cubeList.add(new ModelBox(Legs, 8, 36, -4.0F, -2.0F, -2.0F, 3, 2, 4, 0.0F, false));
		Legs.cubeList.add(new ModelBox(Legs, 36, 36, -1.0F, -2.0F, -1.0F, 3, 2, 4, 0.0F, false));
		Legs.cubeList.add(new ModelBox(Legs, 35, 21, -1.0F, -8.0F, 0.0F, 3, 6, 3, 0.0F, false));
	}

	@Override
	public void render(EntityFaceMonster entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Head.render(f5);
		Body.render(f5);
		Legs.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}