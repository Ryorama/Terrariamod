package com.ryorama.terrariamod.quilt.client.ui;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffT;
import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.client.ui.UIRenderer;
import com.ryorama.terrariamod.stats.StatsT;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TerrariaUIRenderer {
    public static Identifier health_icon = new Identifier(TerrariaMod.MOD_ID, "textures/ui/heart.png");
    public static Identifier shield = new Identifier(TerrariaMod.MOD_ID, "textures/ui/shield.png");
    public static Identifier mana = new Identifier(TerrariaMod.MOD_ID, "textures/ui/mana.png");

    //Buffs
    public static Identifier iron_skin = new Identifier(TerrariaMod.MOD_ID, "textures/ui/buffs/ironskin.png");
    public static Identifier happy = new Identifier(TerrariaMod.MOD_ID, "textures/ui/buffs/happy.png");
    public static Identifier cozy_fire = new Identifier(TerrariaMod.MOD_ID, "textures/ui/buffs/cozy_fire.png");
    public static Identifier regeneration = new Identifier(TerrariaMod.MOD_ID, "textures/ui/buffs/regeneration.png");

    //DeBuffs
    public static Identifier potion_sickness = new Identifier(TerrariaMod.MOD_ID, "textures/ui/buffs/potion_sickness.png");
    public static Identifier poisoned = new Identifier(TerrariaMod.MOD_ID, "textures/ui/buffs/poisoned.png");

    public static Identifier bleeding = new Identifier(TerrariaMod.MOD_ID, "textures/ui/buffs/bleeding.png");
    public static Identifier water_candle = new Identifier(TerrariaMod.MOD_ID, "textures/ui/buffs/water_candle.png");

    public static ClientPlayerEntity player;

    public static void renderTerrariaHealth() {
        HudRenderCallback.EVENT.register((matrixstack, delta) -> {
            float scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();

            if (MinecraftClient.getInstance().player != null) {
                player = MinecraftClient.getInstance().player;
            }

            if (player != null) {
                for (int h = 0; h < player.getHealth() + 20; h++) {
                    if (h % 20 == 0) {
                        if (h < 200) {
                            UIRenderer.renderOverlay(health_icon, 50, 8, 8, scaledWidth - 125 + h / 2, 10, -90);
                        } else if (h > 200) {
                            UIRenderer.renderOverlay(health_icon, 50, 8, 8, scaledWidth - 125 + h / 2 - 110, 20, -90);
                        }
                    }
                }
            }
        });
    }

    public static void renderTerrariaDefense() {
        HudRenderCallback.EVENT.register((matrixstack, delta) -> {

            float scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();

            if (MinecraftClient.getInstance().player != null) {
                player = MinecraftClient.getInstance().player;
            }

            if (player != null) {
                UIRenderer.renderOverlay(shield, 50, 16, 16, scaledWidth - 100 / 2 - 70, 220, -90);
            }
        });
    }

    public static void renderTerrariaMana() {
        HudRenderCallback.EVENT.register((matrixstack, delta) -> {

            float scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();

            if (MinecraftClient.getInstance().player != null) {
                player = MinecraftClient.getInstance().player;
            }

            if (player != null) {
                for (int i = 0; i <= player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.MANA)) - 20; i++) {
                    if (i % 20 == 0) {
                        UIRenderer.renderOverlay(mana, 50, 10, 10, scaledWidth - 18, 10 + i / 2, -90);
                    }
                }
            }
        });
    }

    public static void renderTerrariaEffects() {
        HudRenderCallback.EVENT.register((matrixstack, delta) -> {
            if (MinecraftClient.getInstance().player != null) {
                player = MinecraftClient.getInstance().player;
            }

            int i3 = 17;
            int j3 = 27;
            int effectCounter = 0;

            if (player != null) {
                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.POTION_SICKNESS)) > 0) {
                    UIRenderer.renderOverlay(potion_sickness, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.POISONED)) > 0) {
                    UIRenderer.renderOverlay(poisoned, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.IRON_SKIN)) > 0) {
                    UIRenderer.renderOverlay(iron_skin, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.HAPPY)) > 0) {
                    UIRenderer.renderOverlay(happy, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.COZY_FIRE)) > 0) {
                    UIRenderer.renderOverlay(cozy_fire, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.REGENERATION)) > 0) {
                    UIRenderer.renderOverlay(regeneration, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.BLEEDING)) > 0) {
                    UIRenderer.renderOverlay(bleeding, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }
                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.WATER_CANDLE)) > 0) {
                    UIRenderer.renderOverlay(water_candle, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }
            }
        });
    }

    public static void newRenderTerrariaEffects(LivingEntity entity) {
        HudRenderCallback.EVENT.register((matrixstack, delta) -> {
            int effectCount = 0;

            if (BuffsT.GetEntityActiveBuffs(entity).size() != 0) {
                effectCount = BuffsT.GetEntityActiveBuffs(entity).size();

                for (int i = 0; i < effectCount; i++) {
                    boolean isActive = BuffsT.GetEntityActiveBuffs(entity).get(i).IsActive();

                    if (isActive) {
                        Identifier icon = BuffsT.GetEntityActiveBuffIcons(entity).get(i);

                        UIRenderer.renderOverlay(icon, 50, 16, 16, 17 + i * 20f, 27, -90);
                    }
                }
            }
        });
    }
}
