package com.ryorama.terrariamod.entities.renderers.hostile.slime;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ryorama.terrariamod.entities.models.hostile.slime.ModelBlueSlime;

import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityBlueSlime;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

public class RenderBlueSlime extends DynamicGeoEntityRenderer<EntityBlueSlime> {

    public RenderBlueSlime(EntityRendererProvider.Context context) {
        super(context, new ModelBlueSlime());
    }

    public void preRender(PoseStack poseStack, EntityBlueSlime animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        poseStack.scale(1.3f, 1.3f, 1.3f);

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public RenderType getRenderType(EntityBlueSlime animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}