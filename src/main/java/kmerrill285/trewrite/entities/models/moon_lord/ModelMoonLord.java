package kmerrill285.trewrite.entities.models.moon_lord;

import kmerrill285.trewrite.entities.monsters.bosses.moon_lord.MoonLord;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.ModelBox;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.14
// Paste this class into your mod and generate all required imports


public class ModelMoonLord extends EntityModel<MoonLord> {
	private final RendererModel bone;
	private final RendererModel bone2;
	private final RendererModel bone6;
	private final RendererModel bone4;
	private final RendererModel bone3;
	private final RendererModel bone5;
	private final RendererModel bone7;
	private final RendererModel bone8;
	private final RendererModel bone9;
	private final RendererModel bone10;
	private final RendererModel bb_main;

	public ModelMoonLord() {
		textureWidth = 1024;
		textureHeight = 1024;

		bone = new RendererModel(this);
		bone.setRotationPoint(89.0F, 142.0F, 97.0F);
		bone.cubeList.add(new ModelBox(bone, 723, 190, -239.0F, -188.0F, -97.0F, 1, 21, 100, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 372, 651, -239.0F, -150.0F, -97.0F, 1, 21, 100, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 474, 708, -88.0F, -188.0F, -97.0F, 1, 21, 100, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 628, 423, -88.0F, -150.0F, -97.0F, 1, 21, 100, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 660, 180, -238.0F, -188.0F, -97.0F, 61, 21, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 194, 474, -238.0F, -150.0F, -97.0F, 61, 21, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 320, 651, -238.0F, -188.0F, 0.0F, 61, 21, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 194, 450, -238.0F, -150.0F, 0.0F, 61, 21, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 660, 156, -149.0F, -188.0F, -97.0F, 61, 21, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 194, 426, -149.0F, -150.0F, -97.0F, 61, 21, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 660, 132, -177.0F, -188.0F, 0.0F, 89, 21, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 428, 290, -177.0F, -150.0F, 0.0F, 89, 21, 3, 0.0F, false));

		bone2 = new RendererModel(this);
		bone2.setRotationPoint(-89.0F, -78.0F, 10.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.1309F);
		bone2.cubeList.add(new ModelBox(bone2, 420, 130, -121.0F, -80.0F, 0.0F, 80, 80, 80, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 626, 656, -115.4638F, -0.2486F, 11.0F, 52, 81, 52, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 426, -116.9889F, 27.8437F, -79.0F, 52, 53, 90, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 730, 423, -116.9889F, 6.8437F, -97.0F, 52, 73, 18, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 703, -117.4637F, -27.715F, -97.0F, 12, 35, 18, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 372, 675, -104.3139F, -27.429F, -97.0F, 12, 35, 18, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 668, 423, -90.1726F, -27.2735F, -97.0F, 12, 35, 18, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 707, 0, -77.1533F, -26.9789F, -97.0F, 12, 34, 18, 0.0F, false));

		bone6 = new RendererModel(this);
		bone6.setRotationPoint(-20.7703F, -27.5244F, 0.0F);
		bone2.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.3927F);
		bone6.cubeList.add(new ModelBox(bone6, 354, 772, -67.3567F, 24.2311F, -97.0F, 42, 13, 18, 0.0F, false));

		bone4 = new RendererModel(this);
		bone4.setRotationPoint(0.3802F, -10.1363F, 2.1F);
		bone2.addChild(bone4);
		setRotationAngle(bone4, 0.1309F, 0.0F, 0.0F);
		bone4.cubeList.add(new ModelBox(bone4, 782, 635, -117.622F, -51.9013F, -97.0F, 12, 30, 18, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 778, 48, -104.4722F, -51.6152F, -97.0F, 12, 30, 18, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 778, 0, -90.3309F, -51.4597F, -97.0F, 12, 30, 18, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 0, 756, -77.3116F, -51.1651F, -97.0F, 12, 29, 18, 0.0F, false));

		bone3 = new RendererModel(this);
		bone3.setRotationPoint(106.0F, -94.0F, 10.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.0873F);
		bone3.cubeList.add(new ModelBox(bone3, 348, 348, -121.0F, -80.0F, 0.0F, 80, 80, 80, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 164, 651, -102.2844F, -0.1652F, 11.0F, 52, 81, 52, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 194, 508, -102.2844F, 27.8348F, -79.0F, 52, 53, 90, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 569, -104.2843F, -27.6316F, -96.0F, 12, 35, 18, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 490, 0, -91.1345F, -27.3455F, -96.0F, 12, 35, 18, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 0, 426, -76.9932F, -27.19F, -96.0F, 12, 35, 18, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 102, 703, -63.9739F, -26.8954F, -96.0F, 12, 34, 18, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 678, 544, -103.8095F, 6.9272F, -96.0F, 52, 73, 18, 0.0F, false));

		bone5 = new RendererModel(this);
		bone5.setRotationPoint(-194.2076F, 4.5948F, 4.0F);
		bone3.addChild(bone5);
		setRotationAngle(bone5, 0.1047F, 0.0F, 0.0F);
		bone5.cubeList.add(new ModelBox(bone5, 102, 755, 90.5574F, -67.8178F, -96.0F, 12, 30, 18, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 668, 476, 130.8678F, -67.0817F, -96.0F, 12, 29, 18, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 594, 320, 117.8485F, -67.3763F, -96.0F, 12, 30, 18, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 740, 204, 103.7072F, -67.5318F, -96.0F, 12, 30, 18, 0.0F, false));

		bone7 = new RendererModel(this);
		bone7.setRotationPoint(-137.308F, -47.8241F, 0.0F);
		bone3.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 0.0F, 0.48F);
		bone7.cubeList.add(new ModelBox(bone7, 723, 311, 30.2296F, 43.146F, -96.1F, 42, 13, 18, 0.0F, false));

		bone8 = new RendererModel(this);
		bone8.setRotationPoint(0.0F, -250.0F, 0.0F);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.0436F);
		bone8.cubeList.add(new ModelBox(bone8, 588, 290, -133.0F, -84.0F, 8.0F, 27, 52, 81, 0.0F, false));

		bone9 = new RendererModel(this);
		bone9.setRotationPoint(85.0F, -241.0F, 0.0F);
		setRotationAngle(bone9, 0.0F, 0.0F, 0.0436F);
		bone9.cubeList.add(new ModelBox(bone9, 0, 570, -133.0F, -84.0F, 8.0F, 27, 52, 81, 0.0F, false));

		bone10 = new RendererModel(this);
		bone10.setRotationPoint(151.0F, 142.0F, 0.0F);
		bone10.cubeList.add(new ModelBox(bone10, 388, 508, -211.0F, -150.0F, 0.0F, 61, 21, 3, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 135, 569, -300.0F, -150.0F, 0.0F, 61, 21, 3, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 0, 703, -301.0F, -150.0F, 0.0F, 1, 21, 100, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 388, 532, -300.0F, -150.0F, 97.0F, 61, 21, 3, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 428, 314, -239.0F, -150.0F, 97.0F, 89, 21, 3, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 676, 11, -150.0F, -150.0F, 0.0F, 1, 21, 100, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -150.0F, -188.0F, 0.0F, 150, 110, 100, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 478, 508, -101.0F, -144.0F, 22.0F, 50, 150, 50, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 210, -133.0F, -304.0F, 0.0F, 114, 116, 100, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 400, 0, -125.0F, -229.0F, -23.0F, 20, 72, 25, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 328, 210, -98.0F, -229.0F, -23.0F, 20, 72, 25, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 210, -69.0F, -229.0F, -23.0F, 20, 72, 25, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -44.0F, -229.0F, -23.0F, 20, 72, 25, 0.0F, false));
		bb_main.cubeList.add(new ModelBox(bb_main, 500, 0, -113.0F, -346.0F, 14.0F, 69, 42, 69, 0.0F, false));
	}

	@Override
	public void render(MoonLord entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bone2.render(f5);
		bone3.render(f5);
		bone8.render(f5);
		bone9.render(f5);
		bone10.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}