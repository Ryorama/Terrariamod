package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.entity.hostile.bosses.EntityKingSlime;
import com.ryorama.terrariamod.entity.hostile.slimes.EntityGreenSlime;
import com.ryorama.terrariamod.entity.model.bosses.ModelKingSlime;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderer.geo.GeoEntityRenderer;

public class RenderGreenSlime extends GeoEntityRenderer<EntityGreenSlime> {
 
    public RenderGreenSlime(Context context) {
        super(context, new ModelGreenSlime());
    }
 
    @Override
	public RenderLayer getRenderType(EntityGreenSlime animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
	}
    
    @Override
    public void renderEarly(EntityGreenSlime animatable, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer,
			VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue,
			float partialTicks) {
      	
    }
}