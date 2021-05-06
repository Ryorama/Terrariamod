package com.ryorama.terrariamod.entity.model.bosses;

import com.ryorama.terrariamod.TerrariaMod;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class ModelKingSlime extends AnimatedGeoModel {
	
	@Override
	public Identifier getAnimationFileLocation(Object animatable) {
		return new Identifier(TerrariaMod.modid, "animations/kingslime.animation.json");
	}

	@Override
	public Identifier getModelLocation(Object object) {
		return new Identifier(TerrariaMod.modid, "geo/kingslime.geo.json");
	}

	@Override
	public Identifier getTextureLocation(Object object) {
		return new Identifier(TerrariaMod.modid, "textures/entity/kingslime.png");
	}

}
