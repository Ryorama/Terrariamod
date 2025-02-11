package com.ryorama.terrariamod.forge;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.forge.network.client.rendering.EntityRenderManager;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TerrariaMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TerrariaModForgeClient {

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlocksT.FOREST_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.FOREST_STEM.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.FOREST_TOP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.FOREST_TOP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.FOREST_STUMP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.FOREST_STUMP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.GIANT_GLOWING_MUSHROOM_TOP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.GIANT_GLOWING_MUSHROOM_TOP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.GIANT_GLOWING_MUSHROOM_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.GIANT_GLOWING_MUSHROOM_STEM.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(BlocksT.CACTUS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.CACTUS.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(BlocksT.BLINKROOT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.DAYBLOOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.DEATHWEED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.FIREBLOSSOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.MOONGLOW.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.SHIVERTHORN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.WATERLEAF.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlocksT.MUSHROOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.VILE_MUSHROOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.VICIOUS_MUSHROOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.GLOWING_MUSHROOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.JUNGLE_SPORES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.VINE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.GRASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.BLOODY_GRASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.DEAD_GRASS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.CORRUPTED_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlocksT.CRIMSON_SAPLING.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlocksT.LIFE_CRYSTAL_BLOCK.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlocksT.EMPTY_BOTTLE.get(), RenderType.cutout());

        EntityRenderManager.init();
    }
}
