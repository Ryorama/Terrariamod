package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.hostile.EntitySporeSkeleton;
import com.ryorama.terrariamod.entity.hostile.EntitySporeZombie;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderSporeSkeleton extends GeoEntityRenderer<EntitySporeSkeleton> {

    public RenderSporeSkeleton(Context context) {
        super(context, new ModelSporeSkeleton());
    }
 
    @Override
	public RenderLayer getRenderType(EntitySporeSkeleton animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityAlpha(getTextureResource(animatable));
	}
    
    @Override
    public void renderEarly(EntitySporeSkeleton animatable, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer,
			VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue,
			float partialTicks) {
    	
    }
}