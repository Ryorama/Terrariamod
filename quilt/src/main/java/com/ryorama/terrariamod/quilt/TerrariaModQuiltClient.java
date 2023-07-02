package com.ryorama.terrariamod.quilt;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.client.particles.Flame;
import com.ryorama.terrariamod.client.particles.Leaf;
import com.ryorama.terrariamod.client.particles.ParticlesT;
import com.ryorama.terrariamod.quilt.client.rendering.ChestRenderManager;
import com.ryorama.terrariamod.quilt.client.ui.TerrariaUIRenderer;
import com.ryorama.terrariamod.stats.StatsT;
import com.ryorama.terrariamod.utils.WorldDataT;
import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.stat.Stats;

public class TerrariaModQuiltClient implements ClientModInitializer {
    public int tmpMana = 20;
    @Override
    public void onInitializeClient() {
        addCutouts();
        onTickClient();
        ChestRenderManager.init();
        registerParticles();

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
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP.get(), RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM.get(), RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP.get(), RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_STEM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_STEM.get(), RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_TOP.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_TOP.get(), RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.BLINKROOT.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DAYBLOOM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DEATHWEED.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FIREBLOSSOM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MOONGLOW.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SHIVERTHORN.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.WATERLEAF.get(), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MUSHROOM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.VILE_MUSHROOM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.VICIOUS_MUSHROOM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GLOWING_MUSHROOM.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.JUNGLE_SPORES.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.VINE.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GRASS.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.BLOODY_GRASS.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DEAD_GRASS.get(), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LIFE_CRYSTAL_BLOCK.get(), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.EMPTY_BOTTLE.get(), RenderLayer.getCutout());
    }

    public void registerParticles() {
        ParticleProviderRegistry.register(ParticlesT.FLAME.get(), Flame.Factory::new);
        ParticleProviderRegistry.register(ParticlesT.LEAF.get(), Leaf.Factory::new);
    }

    @Environment(EnvType.CLIENT)
    public void onTickClient() {
        ServerTickEvents.START_SERVER_TICK.register(world -> {
            if (world.isRemote()) {
                ClientPlayerEntity player = MinecraftClient.getInstance().player;

                if (player != null) {

                    if (WorldDataT.firstUpdate) {
                        player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(StatsT.MANA), tmpMana);
                        player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(StatsT.MAX_MANA), tmpMana);
                    }

                    if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.POTION_SICKNESS)) > 0) {
                        player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(StatsT.POTION_SICKNESS), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.POTION_SICKNESS)) - 1);
                    }

                    if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.MANA)) < player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.MAX_MANA))) {
                        player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(StatsT.MANA), player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.MANA)) + 1);
                    }
                }
            }
        });
    }
}
