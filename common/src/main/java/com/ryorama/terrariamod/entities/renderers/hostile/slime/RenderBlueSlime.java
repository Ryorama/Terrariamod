package com.ryorama.terrariamod.entities.renderers.hostile.slime;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entities.models.hostile.slime.ModelBlueSlime;

import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityBlueSlime;
import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityGreenSlime;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

import javax.annotation.Nullable;

public class RenderBlueSlime extends DynamicGeoEntityRenderer<EntityBlueSlime> {

    public RenderBlueSlime(EntityRendererFactory.Context context) {
        super(context, new ModelBlueSlime());
    }

    public void preRender(MatrixStack poseStack, EntityBlueSlime animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.scale(1.3f, 1.3f, 1.3f);

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public RenderLayer getRenderType(EntityBlueSlime animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }
}