package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.hostile.EntityDemonEye;
import com.ryorama.terrariamod.entity.hostile.slimes.EntityBlueSlime;

import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.util.Identifier;

public class RenderDemonEye extends LivingEntityRenderer<EntityDemonEye, ModelDemonEye> {
 
    public RenderDemonEye(Context conetext) {
        super(conetext, new ModelDemonEye(64, 64), 0.5f);
    }
 
    @Override
    public Identifier getTexture(EntityDemonEye entity) {
        return new Identifier("terrariamod", "textures/entity/eyes/eye.png");
    }
}