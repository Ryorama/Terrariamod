package com.ryorama.terrariamod.entity.model.bosses;

import com.ryorama.terrariamod.TerrariaMod;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelKingSlime extends AnimatedGeoModel {
	
	@Override
	public Identifier getAnimationFileLocation(Object animatable) {
		return new Identifier(TerrariaMod.MODID, "animations/kingslime.animation.json");
	}

	@Override
	public Identifier getModelLocation(Object object) {
		return new Identifier(TerrariaMod.MODID, "geo/kingslime.geo.json");
	}

	@Override
	public Identifier getTextureLocation(Object object) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/kingslime.png");
	}

}
