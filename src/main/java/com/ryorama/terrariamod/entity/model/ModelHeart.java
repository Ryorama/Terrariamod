package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.collectables.HeartEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelHeart extends AnimatedGeoModel<HeartEntity> {

    @Override
    public Identifier getAnimationResource(HeartEntity entity) {
        return new Identifier(TerrariaMod.MODID, "animations/heart.animation.json");
    }

    @Override
    public Identifier getModelResource(HeartEntity entity) {
        return new Identifier(TerrariaMod.MODID, "geo/heart.geo.json");
    }

    @Override
    public Identifier getTextureResource(HeartEntity entity) {
        return new Identifier(TerrariaMod.MODID, "textures/entity/heart.png");
    }
}
