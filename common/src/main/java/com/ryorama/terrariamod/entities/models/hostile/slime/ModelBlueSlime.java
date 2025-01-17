package com.ryorama.terrariamod.entities.models.hostile.slime;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityBlueSlime;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelBlueSlime extends GeoModel<EntityBlueSlime> {
	
	@Override
	public ResourceLocation getAnimationResource(EntityBlueSlime animatable) {
		return new ResourceLocation(TerrariaMod.MOD_ID, "animations/slime.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(EntityBlueSlime object) {
		return new ResourceLocation(TerrariaMod.MOD_ID, "geo/slime.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityBlueSlime object) {
		return new ResourceLocation(TerrariaMod.MOD_ID, "textures/entity/blue_slime.png");
	}

}