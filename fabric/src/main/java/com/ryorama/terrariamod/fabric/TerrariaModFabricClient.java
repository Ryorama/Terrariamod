package com.ryorama.terrariamod.fabric;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.client.ui.TerrariaUI;
import com.ryorama.terrariamod.fabric.client.rendering.EntityRenderManager;
import com.ryorama.terrariamod.fabric.client.ui.TerrariaUIRenderer;
import com.ryorama.terrariamod.stats.StatsT;
import com.ryorama.terrariamod.utils.WorldDataT;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.stats.Stats;

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

        HudRenderCallback.EVENT.register((matrixstack, delta) -> {
            TerrariaUI.renderTerrariaHealth();
            TerrariaUI.renderTerrariaMana();
            TerrariaUI.renderTerrariaEffects();
        });

        if (TerrariaMod.CONFIG.useCustomTitles) {
            Minecraft.getInstance().execute(this::SetRandomTitle);
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

        if (Minecraft.getInstance().getWindow() != null) {
            Minecraft.getInstance().getWindow().setTitle(splashTexts.get(id));
        }
    }

    public void addCutouts() {
        /*
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.TOMBSTONE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.TOMBSTONE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GOLD_TOMBSTONE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GOLD_TOMBSTONE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SPIKE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SPIKE, RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.WATER_CANDLE, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.WATER_CANDLE, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LARVA, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LARVA, RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_POT, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_POT, RenderType.translucent());
         */

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STUMP.get(), RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_STEM.get(), RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FOREST_TOP.get(), RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_STEM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_STEM.get(), RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_TOP.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GIANT_GLOWING_MUSHROOM_TOP.get(), RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.CACTUS.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.CACTUS.get(), RenderType.translucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.BLINKROOT.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DAYBLOOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DEATHWEED.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.FIREBLOSSOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MOONGLOW.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SHIVERTHORN.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.WATERLEAF.get(), RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.MUSHROOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.VILE_MUSHROOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.VICIOUS_MUSHROOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GLOWING_MUSHROOM.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.JUNGLE_SPORES.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.VINE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.GRASS.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.BLOODY_GRASS.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.DEAD_GRASS.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.CORRUPTED_SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.CRIMSON_SAPLING.get(), RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.LIFE_CRYSTAL_BLOCK.get(), RenderType.cutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlocksT.EMPTY_BOTTLE.get(), RenderType.cutout());
    }

    public void registerParticles() {
    }

    @Environment(EnvType.CLIENT)
    public void onTickClient() {
        ServerTickEvents.START_SERVER_TICK.register(world -> {
            if (world.isDedicatedServer()) {
                LocalPlayer player = Minecraft.getInstance().player;

                if (player != null) {

                    if (WorldDataT.firstUpdate) {
                        player.getStats().setValue(player, Stats.CUSTOM.get(StatsT.MANA), tmpMana);
                        player.getStats().setValue(player, Stats.CUSTOM.get(StatsT.MAX_MANA), tmpMana);
                    }

                    if (player.getStats().getValue(Stats.CUSTOM.get(StatsT.POTION_SICKNESS)) > 0) {
                        player.getStats().setValue(player, Stats.CUSTOM.get(StatsT.POTION_SICKNESS), player.getStats().getValue(Stats.CUSTOM.get(StatsT.POTION_SICKNESS)) - 1);
                    }

                    if (player.getStats().getValue(Stats.CUSTOM.get(StatsT.MANA)) < player.getStats().getValue(Stats.CUSTOM.get(StatsT.MAX_MANA))) {
                        player.getStats().setValue(player, Stats.CUSTOM.get(StatsT.MANA), player.getStats().getValue(Stats.CUSTOM.get(StatsT.MANA)) + 1);
                    }
                }
            }
        });
    }
}
