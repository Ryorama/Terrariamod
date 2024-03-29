package com.ryorama.terrariamod.forge;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.forge.network.client.rendering.EntityRenderManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TerrariaMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerrariaModForgeClient {

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {
        RenderLayers.setRenderLayer(BlocksT.FOREST_STEM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.FOREST_STEM.get(), RenderLayer.getTranslucent());
        RenderLayers.setRenderLayer(BlocksT.FOREST_TOP.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.FOREST_TOP.get(), RenderLayer.getTranslucent());
        RenderLayers.setRenderLayer(BlocksT.FOREST_STUMP.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.FOREST_STUMP.get(), RenderLayer.getTranslucent());
        RenderLayers.setRenderLayer(BlocksT.GIANT_GLOWING_MUSHROOM_TOP.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.GIANT_GLOWING_MUSHROOM_TOP.get(), RenderLayer.getTranslucent());
        RenderLayers.setRenderLayer(BlocksT.GIANT_GLOWING_MUSHROOM_STEM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.GIANT_GLOWING_MUSHROOM_STEM.get(), RenderLayer.getTranslucent());

        RenderLayers.setRenderLayer(BlocksT.CACTUS.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.CACTUS.get(), RenderLayer.getTranslucent());

        RenderLayers.setRenderLayer(BlocksT.BLINKROOT.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.DAYBLOOM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.DEATHWEED.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.FIREBLOSSOM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.MOONGLOW.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.SHIVERTHORN.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.WATERLEAF.get(), RenderLayer.getCutout());

        RenderLayers.setRenderLayer(BlocksT.MUSHROOM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.VILE_MUSHROOM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.VICIOUS_MUSHROOM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.GLOWING_MUSHROOM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.JUNGLE_SPORES.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.VINE.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.GRASS.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.BLOODY_GRASS.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.DEAD_GRASS.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.SAPLING.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.CORRUPTED_SAPLING.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.CRIMSON_SAPLING.get(), RenderLayer.getCutout());

        RenderLayers.setRenderLayer(BlocksT.LIFE_CRYSTAL_BLOCK.get(), RenderLayer.getCutout());

        RenderLayers.setRenderLayer(BlocksT.EMPTY_BOTTLE.get(), RenderLayer.getCutout());

        EntityRenderManager.init();
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {

    }
}
