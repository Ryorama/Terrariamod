package com.ryorama.terrariamod.entities.models.hostile.slime;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityGreenSlime;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ModelGreenSlime extends GeoModel<EntityGreenSlime> {
	
	@Override
	public Identifier getAnimationResource(EntityGreenSlime animatable) {
		return new Identifier(TerrariaMod.MOD_ID, "animations/slime.animation.json");
	}

	@Override
	public Identifier getModelResource(EntityGreenSlime object) {
		return new Identifier(TerrariaMod.MOD_ID, "geo/slime.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntityGreenSlime object) {
		return new Identifier(TerrariaMod.MOD_ID, "textures/entity/green_slime.png");
	}

}