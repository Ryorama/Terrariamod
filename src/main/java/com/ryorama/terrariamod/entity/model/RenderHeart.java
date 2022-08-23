package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.collectables.HeartEntity;
import com.ryorama.terrariamod.entity.hostile.EntitySporeZombie;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderHeart extends GeoEntityRenderer<HeartEntity> {
    public RenderHeart(EntityRendererFactory.Context context) {
        super(context, new ModelHeart());
    }

    @Override
    public RenderLayer getRenderType(HeartEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                     Identifier textureLocation) {
        return RenderLayer.getEntityAlpha(getTextureResource(animatable));
    }

    @Override
    public void renderEarly(HeartEntity animatable, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer,
                            VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue,
                            float partialTicks) {

    }
}
