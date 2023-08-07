package com.ryorama.terrariamod.entities.renderers.hostile.slime;

import com.ryorama.terrariamod.entities.models.hostile.slime.ModelGreenSlime;
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

public class RenderGreenSlime extends DynamicGeoEntityRenderer<EntityGreenSlime> {
    public RenderGreenSlime(EntityRendererFactory.Context context) {
        super(context, new ModelGreenSlime());
    }

    @Override
    public RenderLayer getRenderType(EntityGreenSlime animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable));
    }
}