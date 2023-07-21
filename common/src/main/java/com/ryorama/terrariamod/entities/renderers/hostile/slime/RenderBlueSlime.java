package com.ryorama.terrariamod.entities.renderers.hostile.slime;

import com.ryorama.terrariamod.entities.models.hostile.slime.ModelBlueSlime;

import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityBlueSlime;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

public class RenderBlueSlime extends DynamicGeoEntityRenderer<EntityBlueSlime> {

    public RenderBlueSlime(EntityRendererFactory.Context context) {
        super(context, new ModelBlueSlime());
    }
}