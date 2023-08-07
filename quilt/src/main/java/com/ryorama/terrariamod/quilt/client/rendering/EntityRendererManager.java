package com.ryorama.terrariamod.quilt.client.rendering;

import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.entities.renderers.block.ChestBlockEntityRendererT;
import com.ryorama.terrariamod.entities.renderers.hostile.RenderDemonEye;
import com.ryorama.terrariamod.entities.renderers.hostile.slime.RenderBlueSlime;
import com.ryorama.terrariamod.entities.renderers.hostile.slime.RenderGreenSlime;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EntityRendererManager {

    public static void init() {
        BlockEntityRendererRegistry.register(EntitiesT.GOLD_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.FROZEN_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.IVY_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.SANDSTONE_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.WATER_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.SKYWARE_CHEST.get(), ChestBlockEntityRendererT::new);
        BlockEntityRendererRegistry.register(EntitiesT.WOOD_CHEST.get(), ChestBlockEntityRendererT::new);

        EntityRendererRegistry.register(EntitiesT.GREEN_SLIME.get(), RenderGreenSlime::new);
        EntityRendererRegistry.register(EntitiesT.BLUE_SLIME.get(), RenderBlueSlime::new);
        EntityRendererRegistry.register(EntitiesT.DEMON_EYE.get(), RenderDemonEye::new);
    }
}
