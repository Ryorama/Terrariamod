package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.hostile.slimes.EntityBlueSlime;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class RenderBlueSlime extends LivingEntityRenderer<EntityBlueSlime, ModelSlime<EntityBlueSlime>> {
 
    public RenderBlueSlime(Context conetext) {
        super(conetext, new ModelSlime<EntityBlueSlime>(128, 128), 0.5f);
    }
 
    @Override
    public Identifier getTexture(EntityBlueSlime entity) {
        return new Identifier("terrariamod", "textures/entity/blueslime.png");
    }
}