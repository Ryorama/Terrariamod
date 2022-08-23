package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.hostile.EntitySporeZombie;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Random;

public class ModelSporeZombie extends AnimatedGeoModel<EntitySporeZombie> {
	
	public Random rand = new Random(); 
	
	@Override
	public Identifier getAnimationResource(EntitySporeZombie entity) {
		return new Identifier(TerrariaMod.MODID, "animations/zombie.animation.json");
	}
	
	@Override
	public Identifier getModelResource(EntitySporeZombie entity) {
		return new Identifier(TerrariaMod.MODID, "geo/zombie.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntitySporeZombie entity) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/sporezombie.png");
	}
}