package com.ryorama.terrariamod.entities.models.hostile;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entities.terraria.hostile.EntityDemonEye;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ModelDemonEye extends GeoModel<EntityDemonEye> {
	
	@Override
	public ResourceLocation getAnimationResource(EntityDemonEye animatable) {
		return new ResourceLocation(TerrariaMod.MOD_ID, "animations/demon_eye.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(EntityDemonEye object) {
		return new ResourceLocation(TerrariaMod.MOD_ID, "geo/demon_eye.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(EntityDemonEye object) {
		return new ResourceLocation(TerrariaMod.MOD_ID, "textures/entity/eyes/eye.png");
	}

}