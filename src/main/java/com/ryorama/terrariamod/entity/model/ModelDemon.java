package com.ryorama.terrariamod.entity.model;

import java.util.Random;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entity.hostile.EntityDemon;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ModelDemon extends GeoModel<EntityDemon> {
	
	public Random rand = new Random(); 
	
	@Override
	public Identifier getAnimationResource(EntityDemon entity) {
		return new Identifier(TerrariaMod.MODID, "animations/demon.animation.json");
	}
	
	@Override
	public Identifier getModelResource(EntityDemon entity) {
		return new Identifier(TerrariaMod.MODID, "geo/demon.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntityDemon entity) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/demon.png");
	}
}