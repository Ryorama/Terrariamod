package com.ryorama.terrariamod.quilt;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.quilt.client.ui.TerrariaUIRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class TerrariaModQuiltClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        addCutouts();

        TerrariaUIRenderer.renderTerrariaHealth();
        TerrariaUIRenderer.renderTerrariaMana();
        TerrariaUIRenderer.renderTerrariaEffects();
    }

    public void addCutouts() {
        /*
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_STEM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_STEM, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_TOP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_TOP, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.TOMBSTONE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.TOMBSTONE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GOLD_TOMBSTONE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GOLD_TOMBSTONE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SPIKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SPIKE, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.WATER_CANDLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.WATER_CANDLE, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LARVA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LARVA, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_POT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_POT, RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.BLINKROOT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DAYBLOOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DEATHWEED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FIREBLOSSOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MOONGLOW, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SHIVERTHORN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.WATERLEAF, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MUSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GLOWING_MUSHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.JUNGLE_SPORES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.VINE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LIFE_CRYSTAL_BLOCK, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.EMPTY_BOTTLE, RenderLayer.getCutout());
         */

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.BLINKROOT.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DAYBLOOM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DEATHWEED.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FIREBLOSSOM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MOONGLOW.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SHIVERTHORN.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.WATERLEAF.get(), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MUSHROOM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GLOWING_MUSHROOM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.JUNGLE_SPORES.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.VINE.get(), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LIFE_CRYSTAL_BLOCK.get(), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.EMPTY_BOTTLE.get(), RenderLayer.getCutout());
    }
}
