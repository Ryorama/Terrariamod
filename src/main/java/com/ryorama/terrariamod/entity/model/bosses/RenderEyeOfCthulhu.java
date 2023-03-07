package com.ryorama.terrariamod.entity.model.bosses;

import com.ryorama.terrariamod.entity.hostile.EntityEaterOfSouls;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityEyeOfCthulhu;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityKingSlime;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RenderEyeOfCthulhu extends GeoModel<EntityEyeOfCthulhu> {
 
    public RenderEyeOfCthulhu(Context context) {
        super(context, new ModelEyeOfCthulhu());
    }
 
    @Override
	public RenderLayer getRenderType(EntityEyeOfCthulhu animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
		return RenderLayer.getEntityAlpha(getTextureResource(animatable));
	}
    
    @Override
    public void renderEarly(EntityEyeOfCthulhu animatable, MatrixStack stackIn, float ticks, VertexConsumerProvider renderTypeBuffer,
			VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue,
			float partialTicks) {
     	
    }
}