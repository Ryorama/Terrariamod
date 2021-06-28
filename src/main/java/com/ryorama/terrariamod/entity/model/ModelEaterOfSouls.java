package com.ryorama.terrariamod.entity.model;

import java.util.Random;

import com.ryorama.terrariamod.TerrariaMod;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelEaterOfSouls extends AnimatedGeoModel {
	
	public Random rand = new Random(); 
	
	@Override
	public Identifier getAnimationFileLocation(Object entity) {
		return new Identifier(TerrariaMod.MODID, "animations/eater_of_souls.animation.json");
	}
	
	@Override
	public Identifier getModelLocation(Object entity) {
		return new Identifier(TerrariaMod.MODID, "geo/eater_of_souls.geo.json");
	}

	@Override
	public Identifier getTextureLocation(Object entity) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/eater_of_souls.png");
	}
}