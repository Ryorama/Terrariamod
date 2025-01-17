package com.ryorama.terrariamod.entities.renderers.hostile;

import com.ryorama.terrariamod.entities.models.hostile.ModelDemonEye;
import com.ryorama.terrariamod.entities.terraria.hostile.EntityDemonEye;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

public class RenderDemonEye extends DynamicGeoEntityRenderer<EntityDemonEye> {

    private ResourceLocation[] eyeTexture = {
            new ResourceLocation("terrariamod:textures/entity/eyes/eye.png"),
            new ResourceLocation("terrariamod:textures/entity/eyes/eye_cataract.png"),
            new ResourceLocation("terrariamod:textures/entity/eyes/eye_dilated.png"),
            new ResourceLocation("terrariamod:textures/entity/eyes/eye_green.png"),
            new ResourceLocation("terrariamod:textures/entity/eyes/eye_purple.png"),
            new ResourceLocation("terrariamod:textures/entity/eyes/eye_sleepy.png")
    };

    public RenderDemonEye(EntityRendererProvider.Context context) {
        super(context, new ModelDemonEye());
    }

    @Override
    public RenderType getRenderType(EntityDemonEye animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        int type = animatable.getEntityData().get(EntityDemonEye.typed_data).intValue();

        return RenderType.entityTranslucent(eyeTexture[type]);
    }
}