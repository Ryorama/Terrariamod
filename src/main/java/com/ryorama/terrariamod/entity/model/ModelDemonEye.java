package com.ryorama.terrariamod.entity.model;

import java.util.Random;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entity.hostile.EntityDemonEye;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelDemonEye extends AnimatedGeoModel<EntityDemonEye> {
	
	public Random rand = new Random(); 
	
	@Override
	public Identifier getAnimationResource(EntityDemonEye entity) {
		return new Identifier(TerrariaMod.MODID, "animations/demon_eye.animation.json");
	}
	
	@Override
	public Identifier getModelResource(EntityDemonEye entity) {
		return new Identifier(TerrariaMod.MODID, "geo/demon_eye.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntityDemonEye entity) {
		return new Identifier(TerrariaMod.MODID, "textures/test");
	}
}