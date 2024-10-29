package com.ryorama.terrariamod.entities.renderers.hostile.slime;

import com.ryorama.terrariamod.entities.models.hostile.slime.ModelGreenSlime;
import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityGreenSlime;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

public class RenderGreenSlime extends DynamicGeoEntityRenderer<EntityGreenSlime> {
    public RenderGreenSlime(EntityRendererFactory.Context context) {
        super(context, new ModelGreenSlime());
    }

    @Override
    public RenderLayer getRenderType(EntityGreenSlime animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }
}