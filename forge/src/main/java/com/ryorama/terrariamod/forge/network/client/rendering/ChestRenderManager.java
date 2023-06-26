package com.ryorama.terrariamod.forge.network.client.rendering;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.entities.renderers.ChestBlockEntityRendererT;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import net.minecraft.client.render.TexturedRenderLayers;
import org.intellij.lang.annotations.Identifier;

public class ChestRenderManager {

    public static void init() {
        BlockEntityRendererRegistry.register(EntitiesT.GOLD_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.FROZEN_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.IVY_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.SANDSTONE_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.WATER_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.SKYWARE_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.WOOD_CHEST.get(), ChestBlockEntityRendererT::new);
    }
}
