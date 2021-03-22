package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.EntitySlimeBase;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class RenderSlime extends LivingEntityRenderer<EntitySlimeBase, ModelSlime> {
 
    public RenderSlime(Context conetext) {
        super(conetext, new ModelSlime(128, 128), 0.5f);
    }
 
    @Override
    public Identifier getTexture(EntitySlimeBase entity) {
        return new Identifier("terrariamod", "textures/entity/green_slime.png");
    }
}