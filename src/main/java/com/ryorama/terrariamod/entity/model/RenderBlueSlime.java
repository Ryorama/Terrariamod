package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.hostile.slimes.EntityBlueSlime;
import com.ryorama.terrariamod.entity.hostile.slimes.EntityGreenSlime;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RenderBlueSlime extends GeoModel<EntityBlueSlime> {
 
    public RenderBlueSlime(Context context) {
        super(context, new ModelBlueSlime());
    }
 
    @Override
	public RenderLayer getRenderType(EntityBlueSlime animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
	}
    
    @Override
    public void renderEarly(EntityBlueSlime animatable, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer,
			VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue,
			float partialTicks) {
      	
    }
}