package com.ryorama.terrariamod.entity.model;

import java.util.Random;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entity.hostile.EntityGranityElemental;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ModelGraniteElemental extends GeoModel<EntityGranityElemental> {
	
	public Random rand = new Random(); 
	
	@Override
	public Identifier getAnimationResource(EntityGranityElemental entity) {
		return new Identifier(TerrariaMod.MODID, "animations/granite_elemental.animation.json");
	}
	
	@Override
	public Identifier getModelResource(EntityGranityElemental entity) {
		return new Identifier(TerrariaMod.MODID, "geo/granite_elemental.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntityGranityElemental entity) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/granite_elemental.png");
	}
}