package com.ryorama.terrariamod.entity.model.bosses;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.entity.hostile.bosses.EntityKingSlime;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ModelKingSlime extends GeoModel<EntityKingSlime> {
	
	@Override
	public Identifier getAnimationResource(EntityKingSlime animatable) {
		return new Identifier(TerrariaMod.MODID, "animations/kingslime.animation.json");
	}

	@Override
	public Identifier getModelResource(EntityKingSlime object) {
		return new Identifier(TerrariaMod.MODID, "geo/kingslime.geo.json");
	}

	@Override
	public Identifier getTextureResource(EntityKingSlime object) {
		return new Identifier(TerrariaMod.MODID, "textures/entity/kingslime.png");
	}

}
