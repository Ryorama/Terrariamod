package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ModelHeart extends AnimatedGeoModel {

    @Override
    public Identifier getAnimationFileLocation(Object entity) {
        return new Identifier(TerrariaMod.MODID, "animations/heart.animation.json");
    }

    @Override
    public Identifier getModelLocation(Object entity) {
        return new Identifier(TerrariaMod.MODID, "geo/heart.geo.json");
    }

    @Override
    public Identifier getTextureLocation(Object entity) {
        return new Identifier(TerrariaMod.MODID, "textures/entity/heart.png");
    }
}
