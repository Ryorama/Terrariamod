package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.collectables.HeartEntity;
import com.ryorama.terrariamod.entity.collectables.ManaStarEntity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelManaStar extends AnimatedGeoModel<ManaStarEntity> {

    @Override
    public Identifier getAnimationResource(ManaStarEntity entity) {
        return new Identifier(TerrariaMod.MODID, "animations/heart.animation.json");
    }

    @Override
    public Identifier getModelResource(ManaStarEntity entity) {
        return new Identifier(TerrariaMod.MODID, "geo/mana_star.geo.json");
    }

    @Override
    public Identifier getTextureResource(ManaStarEntity entity) {
        return new Identifier(TerrariaMod.MODID, "textures/entity/mana_star.png");
    }
}
