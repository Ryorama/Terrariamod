package com.ryorama.terrariamod.entities.renderers.hostile.slime;

import com.ryorama.terrariamod.entities.models.hostile.slime.ModelGreenSlime;
import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityGreenSlime;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

public class RenderGreenSlime extends DynamicGeoEntityRenderer<EntityGreenSlime> {
    public RenderGreenSlime(EntityRendererFactory.Context context) {
        super(context, new ModelGreenSlime());
    }
}