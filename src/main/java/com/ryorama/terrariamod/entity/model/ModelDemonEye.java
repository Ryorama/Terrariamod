package com.ryorama.terrariamod.entity.model;

import java.util.Random;

import com.ryorama.terrariamod.TerrariaMod;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelDemonEye extends AnimatedGeoModel {
	
	public Random rand = new Random(); 
	
	@Override
	public Identifier getAnimationFileLocation(Object entity) {
		return new Identifier(TerrariaMod.modid, "animations/demon_eye.animation.json");
	}
	
	@Override
	public Identifier getModelLocation(Object entity) {
		return new Identifier(TerrariaMod.modid, "geo/demon_eye.geo.json");
	}

	@Override
	public Identifier getTextureLocation(Object entity) {
		return new Identifier(TerrariaMod.modid, "textures/test");
	}
}