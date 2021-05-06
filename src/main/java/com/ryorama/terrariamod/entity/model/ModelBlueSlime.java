package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.hostile.slimes.EntitySlimeBase;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelBlueSlime extends AnimatedGeoModel {
	
	@Override
	public Identifier getAnimationFileLocation(Object animatable) {
		return new Identifier(TerrariaMod.modid, "animations/slime.animation.json");
	}

	@Override
	public Identifier getModelLocation(Object object) {
		return new Identifier(TerrariaMod.modid, "geo/slime.geo.json");
	}

	@Override
	public Identifier getTextureLocation(Object object) {
		return new Identifier(TerrariaMod.modid, "textures/entity/blue_slime.png");
	}

}