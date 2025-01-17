package com.ryorama.terrariamod.entities.renderers.hostile.slime;

import com.ryorama.terrariamod.entities.models.hostile.slime.ModelGreenSlime;
import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityGreenSlime;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

public class RenderGreenSlime extends DynamicGeoEntityRenderer<EntityGreenSlime> {
    public RenderGreenSlime(EntityRendererProvider.Context context) {
        super(context, new ModelGreenSlime());
    }

    @Override
    public RenderType getRenderType(EntityGreenSlime animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}