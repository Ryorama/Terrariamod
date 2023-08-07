package com.ryorama.terrariamod.entities.renderers.hostile;

import com.ryorama.terrariamod.entities.models.hostile.ModelDemonEye;
import com.ryorama.terrariamod.entities.models.hostile.slime.ModelBlueSlime;
import com.ryorama.terrariamod.entities.terraria.hostile.EntityDemonEye;
import com.ryorama.terrariamod.entities.terraria.hostile.slime.EntityBlueSlime;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.Random;

public class RenderDemonEye extends DynamicGeoEntityRenderer<EntityDemonEye> {

    public Random rand = new Random();

    private Identifier[] eyeTexture = {
            new Identifier("terrariamod:textures/entity/eyes/eye.png"),
            new Identifier("terrariamod:textures/entity/eyes/eye_cataract.png"),
            new Identifier("terrariamod:textures/entity/eyes/eye_dilated.png"),
            new Identifier("terrariamod:textures/entity/eyes/eye_green.png"),
            new Identifier("terrariamod:textures/entity/eyes/eye_purple.png"),
            new Identifier("terrariamod:textures/entity/eyes/eye_sleepy.png")
    };

    public RenderDemonEye(EntityRendererFactory.Context context) {
        super(context, new ModelDemonEye());
    }

    @Override
    public RenderLayer getRenderType(EntityDemonEye animatable, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        int type = animatable.getDataTracker().get(EntityDemonEye.typed_data).intValue();

        return RenderLayer.getEntityTranslucent(eyeTexture[type]);
    }
}