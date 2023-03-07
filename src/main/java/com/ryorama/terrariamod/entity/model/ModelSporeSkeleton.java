package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.hostile.EntitySporeSkeleton;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

import java.util.Random;

public class ModelSporeSkeleton extends GeoModel<EntitySporeSkeleton> {
	
	public Random rand = new Random(); 
	
	@Override
	public Identifier getAnimationResource(EntitySporeSkeleton entity) {
		return new Identifier(TerrariaMod.MODID, "animations/zombie.animation.json");
	}
	
	@Override
	public Identifier getModelResource(EntitySporeSkeleton entity) {
		return new Identifier(TerrariaMod.MODID, "geo/zombie.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntitySporeSkeleton entity) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/sporeskeleton.png");
	}
}