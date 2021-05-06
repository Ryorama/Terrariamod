package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.hostile.slimes.EntityGreenSlime;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class RenderSlime extends LivingEntityRenderer<EntityGreenSlime, ModelSlime<EntityGreenSlime>> {
 
    public RenderSlime(Context conetext) {
        super(conetext, new ModelSlime<EntityGreenSlime>(128, 128), 0.5f);
    }
 
    @Override
    public Identifier getTexture(EntityGreenSlime entity) {
        return new Identifier("terrariamod", "textures/entity/green_slime.png");
    }
}