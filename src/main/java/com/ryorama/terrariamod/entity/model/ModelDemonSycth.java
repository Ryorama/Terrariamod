package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entity.hostile.projectiles.DemonScythProjectile;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelDemonSycth extends AnimatedGeoModel<DemonScythProjectile> {
		
	@Override
	public Identifier getAnimationResource(DemonScythProjectile entity) {
		return new Identifier(TerrariaMod.MODID, "animations/demon_scythe.animation.json");
	}
	
	@Override
	public Identifier getModelResource(DemonScythProjectile entity) {
		return new Identifier(TerrariaMod.MODID, "geo/demon_scythe.geo.json");
	}

	@Override
	public Identifier getTextureResource(DemonScythProjectile entity) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/demon_scythe.png");
	}
}