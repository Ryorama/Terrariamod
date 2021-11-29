package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.hostile.EntityDemon;
import com.ryorama.terrariamod.entity.hostile.EntityDrippler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderDrippler extends GeoEntityRenderer<EntityDrippler> {

    public RenderDrippler(Context context) {
        super(context, new ModelDrippler());
    }
 
    @Override
	public RenderLayer getRenderType(EntityDrippler animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityAlpha(getTextureLocation(animatable));
	}
    
    @Override
    public void renderEarly(EntityDrippler animatable, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer,
                            VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue,
                            float partialTicks) {
    	
    }
}