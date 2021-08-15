package com.ryorama.terrariamod.entity.model;

import java.util.Random;

import com.ryorama.terrariamod.entity.hostile.EntityDemonEye;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderDemonEye extends GeoEntityRenderer<EntityDemonEye> {
	
	public Random rand = new Random();
	
	private Identifier[] eyeTexture = {
    		new Identifier("terrariamod:textures/entity/eyes/eye.png"),
    		new Identifier("terrariamod:textures/entity/eyes/eye_cataract.png"),
    		new Identifier("terrariamod:textures/entity/eyes/eye_dilated.png"),
    		new Identifier("terrariamod:textures/entity/eyes/eye_green.png"),
    		new Identifier("terrariamod:textures/entity/eyes/eye_purple.png"),
    		new Identifier("terrariamod:textures/entity/eyes/eye_sleepy.png")
    };
	
    public RenderDemonEye(Context context) {
        super(context, new ModelDemonEye());
    }
 
    @Override
	public RenderLayer getRenderType(EntityDemonEye animatable, float partialTicks, MatrixStack stack,
			VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
			Identifier textureLocation) {
    	

		int type = animatable.getDataTracker().get(EntityDemonEye.typed_data).intValue();
    	
		return RenderLayer.getEntityAlpha(eyeTexture[type]);
	}
}