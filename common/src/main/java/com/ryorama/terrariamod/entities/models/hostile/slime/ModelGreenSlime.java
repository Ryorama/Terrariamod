package com.ryorama.terrariamod.entities.models.hostile.slime;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityGreenSlime;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelGreenSlime extends GeoModel<EntityGreenSlime> {
	
	@Override
	public ResourceLocation getAnimationResource(EntityGreenSlime animatable) {
		return new ResourceLocation(TerrariaMod.MOD_ID, "animations/slime.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(EntityGreenSlime object) {
		return new ResourceLocation(TerrariaMod.MOD_ID, "geo/slime.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityGreenSlime object) {
		return new ResourceLocation(TerrariaMod.MOD_ID, "textures/entity/green_slime.png");
	}

}