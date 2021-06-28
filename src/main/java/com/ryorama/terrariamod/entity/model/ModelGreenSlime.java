package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelGreenSlime extends AnimatedGeoModel {
	
	@Override
	public Identifier getAnimationFileLocation(Object animatable) {
		return new Identifier(TerrariaMod.MODID, "animations/slime.animation.json");
	}

	@Override
	public Identifier getModelLocation(Object object) {
		return new Identifier(TerrariaMod.MODID, "geo/slime.geo.json");
	}

	@Override
	public Identifier getTextureLocation(Object object) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/green_slime.png");
	}

}