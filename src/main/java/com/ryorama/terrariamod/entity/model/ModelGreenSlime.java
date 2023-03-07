package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entity.hostile.slimes.EntityGreenSlime;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ModelGreenSlime extends GeoModel<EntityGreenSlime> {
	
	@Override
	public Identifier getAnimationResource(EntityGreenSlime animatable) {
		return new Identifier(TerrariaMod.MODID, "animations/slime.animation.json");
	}

	@Override
	public Identifier getModelResource(EntityGreenSlime object) {
		return new Identifier(TerrariaMod.MODID, "geo/slime.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntityGreenSlime object) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/green_slime.png");
	}

}