package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entity.hostile.slimes.EntityBlueSlime;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ModelBlueSlime extends GeoModel<EntityBlueSlime> {
	
	@Override
	public Identifier getAnimationResource(EntityBlueSlime animatable) {
		return new Identifier(TerrariaMod.MODID, "animations/slime.animation.json");
	}

	@Override
	public Identifier getModelResource(EntityBlueSlime object) {
		return new Identifier(TerrariaMod.MODID, "geo/slime.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntityBlueSlime object) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/blue_slime.png");
	}

}