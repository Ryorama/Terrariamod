package com.ryorama.terrariamod.forge;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.forge.network.GameRulesT;
import com.ryorama.terrariamod.forge.network.client.ui.TerraruaUIRenderer;
import com.ryorama.terrariamod.utils.WorldDataT;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.io.IOException;

public class TerrariaModEvents {
    public int tmpMana = 20;
    public int tmpMaxMana = 20;

    @SubscribeEvent
    public static void WorldTickEvent(TickEvent.LevelTickEvent event) {
        PlayerEntity player = null;
        World world = event.level;

        for (int p = 0; p < world.getPlayers().size(); p++) {
            player = world.getPlayers().get(p);
        }

        if (player != null) {

            if (WorldDataT.firstUpdate && !WorldDataT.hasStartingTools) {
                if (TerrariaMod.CONFIG.modifyPlayerHealth) {
                    player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(100);
                    player.setHealth(20);
                }

                WorldDataT.hasStartingTools = true;
                WorldDataT.firstUpdate = false;
            }

            if (TerrariaMod.CONFIG.disableHunger) {
                player.getHungerManager().setFoodLevel(20);
            }
        }
    }

    @SubscribeEvent
    public static void WorldLoadEvent(LevelEvent.Load event) {
        if (event.getLevel().getServer() != null) {
            try {
                WorldDataT.setupWorldData();
                WorldDataT.loadData(event.getLevel());
            } catch (IOException e) {
                e.printStackTrace();
            }

            WorldDataT.setupWorldData();
        }
    }
    @SubscribeEvent
    public static void WorldUnloadEvent(LevelEvent.Save event) {
        try {
            WorldDataT.saveData(event.getLevel());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    @OnlyIn(value= Dist.CLIENT)
    public static void renderGuiOverlayEvent(RenderGuiOverlayEvent.Pre event) {
        TerraruaUIRenderer.renderTerrariaHealth();
        TerraruaUIRenderer.renderTerrariaEffects();
        TerraruaUIRenderer.renderTerrariaMana();
    }

    @SubscribeEvent
    @OnlyIn(value=Dist.CLIENT)
    public static void ClientWorldTickEvent(TickEvent.LevelTickEvent event) {
        World world = event.level;
        if (world.isClient) {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;

            if (player != null) {
                if (WorldDataT.firstUpdate) {
                    GameRulesT.MAX_MANA.set(100);
                    GameRulesT.MANA.set(GameRulesT.MAX_MANA.get());
                }

                if (GameRulesT.POTION_SICKNESS.get() > 0) {
                    GameRulesT.POTION_SICKNESS.set(GameRulesT.POTION_SICKNESS.get() - 1);
                }

                if (GameRulesT.MANA.get() < GameRulesT.MAX_MANA.get()) {
                    GameRulesT.MANA.set(GameRulesT.MANA.get() + 1);
                }
            }
        }
    }
}
