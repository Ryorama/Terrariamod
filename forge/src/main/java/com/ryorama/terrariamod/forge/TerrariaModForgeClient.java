package com.ryorama.terrariamod.forge;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TerrariaMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerrariaModForgeClient {

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {
        RenderLayers.setRenderLayer(BlocksT.BLINKROOT.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.DAYBLOOM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.DEATHWEED.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.FIREBLOSSOM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.MOONGLOW.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.SHIVERTHORN.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.WATERLEAF.get(), RenderLayer.getCutout());

        RenderLayers.setRenderLayer(BlocksT.MUSHROOM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.GLOWING_MUSHROOM.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.JUNGLE_SPORES.get(), RenderLayer.getCutout());
        RenderLayers.setRenderLayer(BlocksT.VINE.get(), RenderLayer.getCutout());

        RenderLayers.setRenderLayer(BlocksT.LIFE_CRYSTAL_BLOCK.get(), RenderLayer.getCutout());

        RenderLayers.setRenderLayer(BlocksT.EMPTY_BOTTLE.get(), RenderLayer.getCutout());
    }
}
