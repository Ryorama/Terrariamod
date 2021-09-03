package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Random;

public class ModelSporeSkeleton extends AnimatedGeoModel {
	
	public Random rand = new Random(); 
	
	@Override
	public Identifier getAnimationFileLocation(Object entity) {
		return new Identifier(TerrariaMod.MODID, "animations/zombie.animation.json");
	}
	
	@Override
	public Identifier getModelLocation(Object entity) {
		return new Identifier(TerrariaMod.MODID, "geo/zombie.geo.json");
	}

	@Override
	public Identifier getTextureLocation(Object entity) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/sporeskeleton.png");
	}
}