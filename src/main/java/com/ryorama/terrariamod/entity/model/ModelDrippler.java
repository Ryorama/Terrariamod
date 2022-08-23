package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.hostile.EntityDrippler;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Random;

public class ModelDrippler extends AnimatedGeoModel<EntityDrippler> {
	
	public Random rand = new Random(); 
	
	@Override
	public Identifier getAnimationResource(EntityDrippler entity) {
		return new Identifier(TerrariaMod.MODID, "animations/drippler.animation.json");
	}
	
	@Override
	public Identifier getModelResource(EntityDrippler entity) {
		return new Identifier(TerrariaMod.MODID, "geo/drippler.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntityDrippler entity) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/drippler.png");
	}
}