package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.hostile.EntityGranityElemental;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityKingSlime;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RenderGraniteElemental extends GeoEntityRenderer<EntityGranityElemental> {
		
    public RenderGraniteElemental(Context context) {
        super(context, new ModelGraniteElemental());
    }
 
    @Override
	public RenderLayer getRenderType(EntityGranityElemental animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
    	    	
    	
		return RenderLayer.getEntityAlpha(getTextureResource(animatable));
	}
    
    @Override
    public void renderEarly(EntityGranityElemental animatable, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer,
			VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue,
			float partialTicks) {
    
    	stackIn.scale(5, 5, 5);
    	stackIn.translate(0, -0.5f, 0);
    }
}