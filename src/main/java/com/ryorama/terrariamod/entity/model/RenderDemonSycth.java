package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.hostile.EntityDemon;
import com.ryorama.terrariamod.entity.hostile.projectiles.DemonScythProjectile;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RenderDemonSycth extends GeoModel<DemonScythProjectile> {
 
    public RenderDemonSycth(Context context) {
        super(context, new ModelDemonSycth());
    }
 
    @Override
	public RenderLayer getRenderType(DemonScythProjectile animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityAlpha(getTextureResource(animatable));
	}
    
    @Override
    public void renderEarly(DemonScythProjectile animatable, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer,
			VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue,
			float partialTicks) {
    	
    }
}