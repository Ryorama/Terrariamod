package com.ryorama.terrariamod.entity.model;

import com.ryorama.terrariamod.client.fx.ShadersManager;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.Shader;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Nullable;

public class PlayerEntityShaderRenderer extends PlayerEntityRenderer {

    public PlayerEntityShaderRenderer(EntityRendererFactory.Context ctx, boolean slim) {
        super(ctx, slim);
    }

    @Nullable
    @Override
    protected RenderLayer getRenderLayer(AbstractClientPlayerEntity entity, boolean showBody, boolean translucent, boolean glowing) {
        RenderLayer baseLayer = super.getRenderLayer(entity, showBody, translucent, glowing);
        return baseLayer == null ? null : ShadersManager.COLOR_SHADER_BUFFER.getRenderLayer(baseLayer);
    }
}
