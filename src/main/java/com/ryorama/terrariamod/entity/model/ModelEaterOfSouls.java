package com.ryorama.terrariamod.entity.model;

import java.util.Random;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entity.hostile.EntityEaterOfSouls;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ModelEaterOfSouls extends GeoModel<EntityEaterOfSouls> {
	
	public Random rand = new Random(); 
	
	@Override
	public Identifier getAnimationResource(EntityEaterOfSouls entity) {
		return new Identifier(TerrariaMod.MODID, "animations/eater_of_souls.animation.json");
	}
	
	@Override
	public Identifier getModelResource(EntityEaterOfSouls entity) {
		return new Identifier(TerrariaMod.MODID, "geo/eater_of_souls.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntityEaterOfSouls entity) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/eater_of_souls.png");
	}
}