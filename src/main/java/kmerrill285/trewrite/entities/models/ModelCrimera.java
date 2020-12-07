package kmerrill285.trewrite.entities.models;

import kmerrill285.trewrite.entities.monsters.EntityCrimera;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports


public class ModelCrimera extends EntityModel<EntityCrimera> {
	private final RendererModel body;
	private final RendererModel body1;
	private final RendererModel body2;
	private final RendererModel body3;
	private final RendererModel body4;
	private final RendererModel body5;
	private final RendererModel body6;
	private final RendererModel body7;
	private final RendererModel body8;
	private final RendererModel body9;
	private final RendererModel body10;
	private final RendererModel body11;
	private final RendererModel fins;
	private final RendererModel arm1;
	private final RendererModel arm2;

	public ModelCrimera() {
		textureWidth = 128;
		textureHeight = 128;

		body = new RendererModel(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body1 = new RendererModel(this);
		body1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(body1);
		body1.cubeList.add(new ModelBox(body1, 0, 14, -6.0F, -6.0F, 1.0F, 11, 1, 6, 0.0F, false));
		body1.cubeList.add(new ModelBox(body1, 24, 35, -5.0F, -6.0F, -2.0F, 9, 1, 3, 0.0F, false));
		body1.cubeList.add(new ModelBox(body1, 0, 39, -3.0F, -6.0F, -9.0F, 5, 1, 5, 0.0F, false));
		body1.cubeList.add(new ModelBox(body1, 32, 49, -2.0F, -6.0F, -14.0F, 3, 1, 5, 0.0F, false));
		body1.cubeList.add(new ModelBox(body1, 33, 51, -1.0F, -6.0F, -15.0F, 1, 1, 1, 0.0F, false));
		body1.cubeList.add(new ModelBox(body1, 48, 52, -4.0F, -6.0F, -4.0F, 7, 1, 2, 0.0F, false));
		body1.cubeList.add(new ModelBox(body1, 42, 42, -5.0F, -6.0F, 7.0F, 9, 1, 2, 0.0F, false));
		body1.cubeList.add(new ModelBox(body1, 37, 45, 1.0F, -6.0F, 9.0F, 2, 1, 1, 0.0F, false));
		body1.cubeList.add(new ModelBox(body1, 17, 45, -4.0F, -6.0F, 9.0F, 2, 1, 1, 0.0F, false));
		body1.cubeList.add(new ModelBox(body1, 27, 51, 1.0F, -6.0F, 10.0F, 1, 1, 1, 0.0F, false));
		body1.cubeList.add(new ModelBox(body1, 17, 51, -3.0F, -6.0F, 10.0F, 1, 1, 1, 0.0F, false));

		body2 = new RendererModel(this);
		body2.setRotationPoint(0.0F, -1.0F, 0.0F);
		body.addChild(body2);
		body2.cubeList.add(new ModelBox(body2, 0, 7, -6.0F, -6.0F, 1.0F, 11, 1, 6, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 0, 35, -5.0F, -6.0F, -2.0F, 9, 1, 3, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 28, 55, -2.0F, -6.0F, -9.0F, 3, 1, 4, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 49, 55, -1.0F, -6.0F, -14.0F, 1, 1, 5, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 48, 22, -4.0F, -6.0F, -5.0F, 7, 1, 3, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 20, 42, -5.0F, -6.0F, 7.0F, 9, 1, 2, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 15, 42, 1.0F, -6.0F, 9.0F, 2, 1, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 28, 18, -4.0F, -6.0F, 9.0F, 2, 1, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 11, 51, 1.0F, -6.0F, 10.0F, 1, 1, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 0, 51, -1.0F, -6.0F, 10.0F, 1, 1, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 33, 49, -3.0F, -6.0F, 10.0F, 1, 1, 1, 0.0F, false));

		body3 = new RendererModel(this);
		body3.setRotationPoint(0.0F, -1.0F, 0.0F);
		body.addChild(body3);
		body3.cubeList.add(new ModelBox(body3, 16, 49, -2.0F, -10.0F, 2.0F, 3, 1, 5, 0.0F, false));

		body4 = new RendererModel(this);
		body4.setRotationPoint(0.0F, -1.0F, 0.0F);
		body.addChild(body4);
		body4.cubeList.add(new ModelBox(body4, 28, 1, -5.0F, -7.0F, 1.0F, 9, 1, 6, 0.0F, false));
		body4.cubeList.add(new ModelBox(body4, 48, 15, -4.0F, -7.0F, -2.0F, 7, 1, 3, 0.0F, false));
		body4.cubeList.add(new ModelBox(body4, 44, 55, -1.0F, -7.0F, -8.0F, 1, 1, 4, 0.0F, false));
		body4.cubeList.add(new ModelBox(body4, 54, 19, -3.0F, -7.0F, -4.0F, 5, 1, 2, 0.0F, false));
		body4.cubeList.add(new ModelBox(body4, 51, 31, -4.0F, -7.0F, 7.0F, 7, 1, 2, 0.0F, false));
		body4.cubeList.add(new ModelBox(body4, 54, 29, -3.0F, -7.0F, 9.0F, 5, 1, 1, 0.0F, false));
		body4.cubeList.add(new ModelBox(body4, 65, 28, -2.0F, -7.0F, 10.0F, 3, 1, 1, 0.0F, false));

		body5 = new RendererModel(this);
		body5.setRotationPoint(0.0F, -1.0F, 0.0F);
		body.addChild(body5);
		body5.cubeList.add(new ModelBox(body5, 0, 28, -5.0F, -8.0F, 1.0F, 9, 1, 6, 0.0F, false));
		body5.cubeList.add(new ModelBox(body5, 48, 8, -4.0F, -8.0F, -2.0F, 7, 1, 3, 0.0F, false));
		body5.cubeList.add(new ModelBox(body5, 65, 22, -2.0F, -8.0F, -3.0F, 3, 1, 1, 0.0F, false));
		body5.cubeList.add(new ModelBox(body5, 43, 49, -4.0F, -8.0F, 7.0F, 7, 1, 2, 0.0F, false));
		body5.cubeList.add(new ModelBox(body5, 65, 15, -2.0F, -8.0F, 9.0F, 3, 1, 1, 0.0F, false));

		body6 = new RendererModel(this);
		body6.setRotationPoint(0.0F, 1.0F, 0.0F);
		body.addChild(body6);
		body6.cubeList.add(new ModelBox(body6, 0, 0, -6.0F, -6.0F, 1.0F, 11, 1, 6, 0.0F, false));
		body6.cubeList.add(new ModelBox(body6, 30, 31, -5.0F, -6.0F, -2.0F, 9, 1, 3, 0.0F, false));
		body6.cubeList.add(new ModelBox(body6, 14, 55, -2.0F, -6.0F, -9.0F, 3, 1, 4, 0.0F, false));
		body6.cubeList.add(new ModelBox(body6, 37, 55, -1.0F, -6.0F, -14.0F, 1, 1, 5, 0.0F, false));
		body6.cubeList.add(new ModelBox(body6, 40, 45, -4.0F, -6.0F, -5.0F, 7, 1, 3, 0.0F, false));
		body6.cubeList.add(new ModelBox(body6, 15, 39, -5.0F, -6.0F, 7.0F, 9, 1, 2, 0.0F, false));
		body6.cubeList.add(new ModelBox(body6, 28, 11, 1.0F, -6.0F, 9.0F, 2, 1, 1, 0.0F, false));
		body6.cubeList.add(new ModelBox(body6, 24, 28, -4.0F, -6.0F, 9.0F, 2, 1, 1, 0.0F, false));
		body6.cubeList.add(new ModelBox(body6, 27, 49, 1.0F, -6.0F, 10.0F, 1, 1, 1, 0.0F, false));
		body6.cubeList.add(new ModelBox(body6, 17, 49, -3.0F, -6.0F, 10.0F, 1, 1, 1, 0.0F, false));
		body6.cubeList.add(new ModelBox(body6, 11, 49, 0.0F, -6.0F, 10.0F, 1, 1, 1, 0.0F, false));
		body6.cubeList.add(new ModelBox(body6, 0, 49, -2.0F, -6.0F, 10.0F, 1, 1, 1, 0.0F, false));

		body7 = new RendererModel(this);
		body7.setRotationPoint(0.0F, -1.0F, 0.0F);
		body.addChild(body7);
		body7.cubeList.add(new ModelBox(body7, 28, 15, -4.0F, -9.0F, 1.0F, 7, 1, 6, 0.0F, false));
		body7.cubeList.add(new ModelBox(body7, 52, 5, -3.0F, -9.0F, 7.0F, 5, 1, 1, 0.0F, false));
		body7.cubeList.add(new ModelBox(body7, 65, 8, -2.0F, -9.0F, 8.0F, 3, 1, 1, 0.0F, false));
		body7.cubeList.add(new ModelBox(body7, 50, 61, -2.0F, -9.0F, -1.0F, 3, 1, 2, 0.0F, false));

		body8 = new RendererModel(this);
		body8.setRotationPoint(0.0F, 3.0F, 0.0F);
		body.addChild(body8);
		body8.cubeList.add(new ModelBox(body8, 24, 24, -5.0F, -7.0F, 1.0F, 9, 1, 6, 0.0F, false));
		body8.cubeList.add(new ModelBox(body8, 20, 45, -4.0F, -7.0F, -2.0F, 7, 1, 3, 0.0F, false));
		body8.cubeList.add(new ModelBox(body8, 27, 49, -1.0F, -7.0F, -8.0F, 1, 1, 4, 0.0F, false));
		body8.cubeList.add(new ModelBox(body8, 54, 12, -3.0F, -7.0F, -4.0F, 5, 1, 2, 0.0F, false));
		body8.cubeList.add(new ModelBox(body8, 48, 26, -4.0F, -7.0F, 7.0F, 7, 1, 2, 0.0F, false));
		body8.cubeList.add(new ModelBox(body8, 36, 22, -3.0F, -7.0F, 9.0F, 5, 1, 1, 0.0F, false));
		body8.cubeList.add(new ModelBox(body8, 64, 51, -2.0F, -7.0F, 10.0F, 3, 1, 1, 0.0F, false));

		body9 = new RendererModel(this);
		body9.setRotationPoint(0.0F, 5.0F, 0.0F);
		body.addChild(body9);
		body9.cubeList.add(new ModelBox(body9, 0, 21, -5.0F, -8.0F, 1.0F, 9, 1, 6, 0.0F, false));
		body9.cubeList.add(new ModelBox(body9, 0, 45, -4.0F, -8.0F, -2.0F, 7, 1, 3, 0.0F, false));
		body9.cubeList.add(new ModelBox(body9, 64, 26, -2.0F, -8.0F, -3.0F, 3, 1, 1, 0.0F, false));
		body9.cubeList.add(new ModelBox(body9, 45, 35, -4.0F, -8.0F, 7.0F, 7, 1, 2, 0.0F, false));
		body9.cubeList.add(new ModelBox(body9, 49, 64, -2.0F, -8.0F, 9.0F, 3, 1, 1, 0.0F, false));

		body10 = new RendererModel(this);
		body10.setRotationPoint(0.0F, 7.0F, 0.0F);
		body.addChild(body10);
		body10.cubeList.add(new ModelBox(body10, 28, 8, -4.0F, -9.0F, 1.0F, 7, 1, 6, 0.0F, false));
		body10.cubeList.add(new ModelBox(body10, 24, 22, -3.0F, -9.0F, 7.0F, 5, 1, 1, 0.0F, false));
		body10.cubeList.add(new ModelBox(body10, 21, 64, -2.0F, -9.0F, 8.0F, 3, 1, 1, 0.0F, false));
		body10.cubeList.add(new ModelBox(body10, 40, 61, -2.0F, -9.0F, -1.0F, 3, 1, 2, 0.0F, false));

		body11 = new RendererModel(this);
		body11.setRotationPoint(0.0F, 9.0F, 0.0F);
		body.addChild(body11);
		body11.cubeList.add(new ModelBox(body11, 0, 49, -2.0F, -10.0F, 2.0F, 3, 1, 5, 0.0F, false));

		fins = new RendererModel(this);
		fins.setRotationPoint(0.0F, 24.0F, 0.0F);
		fins.cubeList.add(new ModelBox(fins, 0, 31, 1.0F, -6.0F, -14.0F, 1, 1, 2, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 50, 55, 2.0F, -6.0F, -9.0F, 1, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 35, 39, 3.0F, -6.0F, -11.0F, 1, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 0, 39, -5.0F, -6.0F, -11.0F, 1, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 48, 19, 1.0F, -6.0F, -10.0F, 3, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 48, 12, -5.0F, -6.0F, -10.0F, 3, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 28, 15, 4.0F, -6.0F, -7.0F, 1, 1, 2, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 28, 0, -6.0F, -6.0F, -7.0F, 1, 1, 2, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 38, 55, 4.0F, -6.0F, -1.0F, 1, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 63, 0, 3.0F, -6.0F, -3.0F, 4, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 43, 52, 4.0F, -6.0F, -2.0F, 2, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 60, 60, 5.0F, -6.0F, 1.0F, 4, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 65, 54, 5.0F, -6.0F, 2.0F, 3, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 0, 41, 5.0F, -6.0F, 3.0F, 1, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 21, 35, -3.0F, -6.0F, -14.0F, 1, 1, 2, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 16, 64, -1.0F, -6.0F, -18.0F, 1, 1, 3, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 11, 49, -2.0F, -6.0F, -19.0F, 1, 1, 4, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 28, 8, -4.0F, -6.0F, -15.0F, 1, 1, 2, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 28, 3, 2.0F, -6.0F, -15.0F, 1, 1, 2, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 14, 57, -4.0F, -6.0F, -9.0F, 1, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 43, 64, -5.0F, -6.0F, -6.0F, 2, 1, 2, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 24, 31, 2.0F, -6.0F, -6.0F, 2, 1, 2, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 44, 55, -6.0F, -6.0F, -1.0F, 1, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 63, 6, -8.0F, -6.0F, -3.0F, 4, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 11, 55, -7.0F, -6.0F, -2.0F, 2, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 30, 61, -10.0F, -6.0F, 1.0F, 4, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 65, 56, -9.0F, -6.0F, 2.0F, 3, 1, 1, 0.0F, false));
		fins.cubeList.add(new ModelBox(fins, 40, 42, -7.0F, -6.0F, 3.0F, 1, 1, 1, 0.0F, false));

		arm1 = new RendererModel(this);
		arm1.setRotationPoint(0.0F, 24.0F, 0.0F);
		arm1.cubeList.add(new ModelBox(arm1, 8, 64, -7.0F, -7.0F, 7.0F, 2, 2, 2, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 0, 55, -8.0F, -7.0F, 9.0F, 4, 2, 3, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 0, 64, -7.0F, -8.0F, 9.0F, 1, 1, 3, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 35, 63, -7.0F, -5.0F, 9.0F, 1, 1, 3, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 20, 60, -8.0F, -7.0F, 12.0F, 3, 2, 2, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 24, 24, -7.0F, -8.0F, 12.0F, 1, 1, 2, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 0, 17, -7.0F, -5.0F, 12.0F, 1, 1, 2, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 59, 46, -7.0F, -7.0F, 14.0F, 3, 2, 3, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 63, 2, -6.0F, -8.0F, 14.0F, 1, 1, 3, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 62, 63, -6.0F, -5.0F, 14.0F, 1, 1, 3, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 10, 60, -8.0F, -7.0F, 17.0F, 3, 2, 2, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 0, 24, -7.0F, -8.0F, 17.0F, 1, 1, 2, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 0, 21, -7.0F, -5.0F, 17.0F, 1, 1, 2, 0.0F, false));
		arm1.cubeList.add(new ModelBox(arm1, 0, 28, -8.0F, -7.0F, 19.0F, 2, 2, 1, 0.0F, false));

		arm2 = new RendererModel(this);
		arm2.setRotationPoint(-1.0F, 24.0F, 0.0F);
		arm2.cubeList.add(new ModelBox(arm2, 63, 34, 5.0F, -7.0F, 7.0F, 2, 2, 2, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 52, 0, 4.0F, -7.0F, 9.0F, 4, 2, 3, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 27, 63, 6.0F, -8.0F, 9.0F, 1, 1, 3, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 57, 62, 6.0F, -5.0F, 9.0F, 1, 1, 3, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 0, 60, 5.0F, -7.0F, 12.0F, 3, 2, 2, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 0, 14, 6.0F, -8.0F, 12.0F, 1, 1, 2, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 0, 10, 6.0F, -5.0F, 12.0F, 1, 1, 2, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 56, 55, 4.0F, -7.0F, 14.0F, 3, 2, 3, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 61, 42, 5.0F, -8.0F, 14.0F, 1, 1, 3, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 24, 55, 5.0F, -5.0F, 14.0F, 1, 1, 3, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 59, 38, 5.0F, -7.0F, 17.0F, 3, 2, 2, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 0, 7, 6.0F, -8.0F, 17.0F, 1, 1, 2, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 0, 3, 6.0F, -5.0F, 17.0F, 1, 1, 2, 0.0F, false));
		arm2.cubeList.add(new ModelBox(arm2, 0, 0, 6.0F, -7.0F, 19.0F, 2, 2, 1, 0.0F, false));
	}

	@Override
	public void render(EntityCrimera entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
		fins.render(f5);
		arm1.render(f5);
		arm2.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}