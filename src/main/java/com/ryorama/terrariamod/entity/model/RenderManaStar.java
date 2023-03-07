package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.collectables.ManaStarEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderManaStar extends GeoEntityRenderer<ManaStarEntity> {
    public RenderManaStar(EntityRendererFactory.Context context) {
        super(context, new ModelManaStar());
    }

    @Override
    public RenderLayer getRenderType(ManaStarEntity animatable, float partialTicks, MatrixStack stack,
            VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
            Identifier textureLocation) {
        return RenderLayer.getEntityAlpha(getTextureResource(animatable));
    }

    @Override
    public void renderEarly(ManaStarEntity animatable, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer,
        VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue,
            float partialTicks) {

    }
}