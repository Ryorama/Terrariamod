package com.ryorama.terrariamod.entity.model.bosses;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityEyeOfCthulhu;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelEyeOfCthulhu extends AnimatedGeoModel<EntityEyeOfCthulhu> {
	
	@Override
	public Identifier getAnimationFileLocation(EntityEyeOfCthulhu animatable) {
		if (animatable.phase == 1) {
			return new Identifier(TerrariaMod.MODID, "animations/eye_of_cthulhu_phase1.animation.json");
		} else {
			return new Identifier(TerrariaMod.MODID, "animations/eye_of_cthulhu_phase2.animation.json");
		}
	}

	@Override
	public Identifier getModelLocation(EntityEyeOfCthulhu object) {
		if (object.phase == 1) {
			return new Identifier(TerrariaMod.MODID, "geo/eye_of_cthulhu_phase1.geo.json");
		} else {
			return new Identifier(TerrariaMod.MODID, "geo/eye_of_cthulhu_phase2.geo.json");
		}
	}

	@Override
	public Identifier getTextureLocation(EntityEyeOfCthulhu object) {
		if (object.phase == 1) {
			return new Identifier(TerrariaMod.MODID, "textures/entity/eyes/eye_of_cthulhu_phase1.png");
		} else {
			return new Identifier(TerrariaMod.MODID, "textures/entity/eyes/eye_of_cthulhu_phase2.png");
		}
	}
}
