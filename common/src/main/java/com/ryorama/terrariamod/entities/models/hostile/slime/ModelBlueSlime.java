package com.ryorama.terrariamod.entities.models.hostile.slime;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityBlueSlime;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ModelBlueSlime extends GeoModel<EntityBlueSlime> {
	
	@Override
	public Identifier getAnimationResource(EntityBlueSlime animatable) {
		return new Identifier(TerrariaMod.MOD_ID, "animations/slime.animation.json");
	}

	@Override
	public Identifier getModelResource(EntityBlueSlime object) {
		return new Identifier(TerrariaMod.MOD_ID, "geo/slime.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntityBlueSlime object) {
		return new Identifier(TerrariaMod.MOD_ID, "textures/entity/blue_slime.png");
	}

}