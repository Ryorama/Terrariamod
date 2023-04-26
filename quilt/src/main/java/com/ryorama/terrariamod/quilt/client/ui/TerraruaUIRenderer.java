package com.ryorama.terrariamod.quilt.client.ui;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.client.ui.UIRenderer;
import com.ryorama.terrariamod.stats.StatsT;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TerraruaUIRenderer {
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
                    TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
                    textRenderer.draw(matrixstack, "Life: " + (int)player.getHealth() + "/" + (int)player.getMaxHealth(), scaledWidth - 113, 2, 0xffffff);

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
                TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

                if ((int)player.getAttributeValue(EntityAttributes.GENERIC_ARMOR) >= 10) {
                    textRenderer.draw(matrixstack, Integer.toString((int)player.getAttributeValue(EntityAttributes.GENERIC_ARMOR)), scaledWidth - 100 / 2 - 66, 224, 0xffffff);
                } else {
                    textRenderer.draw(matrixstack, Integer.toString((int)player.getAttributeValue(EntityAttributes.GENERIC_ARMOR)), scaledWidth - 100 / 2 - 64, 224, 0xffffff);
                }
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
                MinecraftClient.getInstance().textRenderer.draw(matrixstack, "Mana", (int) (scaledWidth - 25), 3, 0xffffff);

                for (int i = 0; i <= player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.MANA.get())) - 20; i++) {
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
                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.POTION_SICKNESS.get())) > 0) {
                    UIRenderer.renderOverlay(potion_sickness, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.POISONED.get())) > 0) {
                    UIRenderer.renderOverlay(poisoned, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.IRON_SKIN.get())) > 0) {
                    UIRenderer.renderOverlay(iron_skin, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.HAPPY.get())) > 0) {
                    UIRenderer.renderOverlay(happy, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.COZY_FIRE.get())) > 0) {
                    UIRenderer.renderOverlay(cozy_fire, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.REGENERATION.get())) > 0) {
                    UIRenderer.renderOverlay(regeneration, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }

                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.BLEEDING.get())) > 0) {
                    UIRenderer.renderOverlay(bleeding, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }
                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(StatsT.WATER_CANDLE.get())) > 0) {
                    UIRenderer.renderOverlay(water_candle, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                    effectCounter++;
                }
            }
        });
    }
}
