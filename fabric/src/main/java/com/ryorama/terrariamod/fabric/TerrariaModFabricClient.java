package com.ryorama.terrariamod.fabric;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.client.particles.Flame;
import com.ryorama.terrariamod.client.particles.Leaf;
import com.ryorama.terrariamod.client.particles.ParticlesT;
import com.ryorama.terrariamod.fabric.client.rendering.EntityRenderManager;
import com.ryorama.terrariamod.fabric.client.ui.TerrariaUIRenderer;
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TerrariaModFabricClient implements ClientModInitializer {

    public int tmpMana = 20;
    public static Random rand = new Random();

    @Override
    public void onInitializeClient() {
        addCutouts();
        onTickClient();
        EntityRenderManager.init();
        registerParticles();

        TerrariaUIRenderer.renderTerrariaHealth();
        TerrariaUIRenderer.renderTerrariaMana();
        TerrariaUIRenderer.renderTerrariaEffects();

        if (TerrariaMod.CONFIG.useCustomTitles) {
            MinecraftClient.getInstance().execute(this::SetRandomTitle);
        }
    }

    public void SetRandomTitle() {
        InputStream stream = TerrariaMod.class.getClassLoader().getResourceAsStream("assets/terrariamod/splash_texts.txt");
        Scanner scanner = new Scanner(stream);
        List<String> splashTexts = new ArrayList<>();

        while (scanner.hasNextLine()) {
            splashTexts.add(scanner.nextLine());
        }

        int id = rand.nextInt(splashTexts.size());

        if (MinecraftClient.getInstance().getWindow() != null) {
            MinecraftClient.getInstance().getWindow().setTitle(splashTexts.get(id));
        }
    }

    public void addCutouts() {
        /*
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

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.CACTUS.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.CACTUS.get(), RenderLayer.getTranslucent());

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
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SAPLING.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.CORRUPTED_SAPLING.get(), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.CRIMSON_SAPLING.get(), RenderLayer.getCutout());

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
