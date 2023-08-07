package com.ryorama.terrariamod.entities.models.hostile;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entities.terraria.hostile.EntityDemonEye;
import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityBlueSlime;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ModelDemonEye extends GeoModel<EntityDemonEye> {
	
	@Override
	public Identifier getAnimationResource(EntityDemonEye animatable) {
		return new Identifier(TerrariaMod.MOD_ID, "animations/demon_eye.animation.json");
	}

	@Override
	public Identifier getModelResource(EntityDemonEye object) {
		return new Identifier(TerrariaMod.MOD_ID, "geo/demon_eye.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntityDemonEye object) {
		return new Identifier(TerrariaMod.MOD_ID, "textures/entity/eyes/eye.png");
	}

}