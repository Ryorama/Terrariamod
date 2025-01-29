package com.ryorama.terrariamod.fabric.client.ui;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.buffs.BuffT;
import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.client.ui.UIRenderer;
import com.ryorama.terrariamod.stats.StatsT;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

@Environment(EnvType.CLIENT)
public class TerrariaUIRenderer {
    public static ResourceLocation health_icon = new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/heart.png");
    public static ResourceLocation shield = new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/shield.png");
    public static ResourceLocation mana = new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/mana.png");

    //Buffs
    public static ResourceLocation iron_skin = new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/buffs/ironskin.png");
    public static ResourceLocation happy = new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/buffs/happy.png");
    public static ResourceLocation cozy_fire = new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/buffs/cozy_fire.png");
    public static ResourceLocation regeneration = new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/buffs/regeneration.png");

    //DeBuffs
    public static ResourceLocation potion_sickness = new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/buffs/potion_sickness.png");
    public static ResourceLocation poisoned = new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/buffs/poisoned.png");

    public static ResourceLocation bleeding = new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/buffs/bleeding.png");
    public static ResourceLocation water_candle = new ResourceLocation(TerrariaMod.MOD_ID, "textures/ui/buffs/water_candle.png");

    public static LocalPlayer player;

    public static void renderTerrariaHealth() {
        HudRenderCallback.EVENT.register((matrixstack, delta) -> {
            if (!TerrariaMod.CONFIG.useVanillaHud) {
                float scaledWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();

                if (Minecraft.getInstance().player != null) {
                    player = Minecraft.getInstance().player;
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
            }
        });
    }

    public static void renderTerrariaDefense() {
        HudRenderCallback.EVENT.register((matrixstack, delta) -> {

            float scaledWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();

            if (Minecraft.getInstance().player != null) {
                player = Minecraft.getInstance().player;
            }

            if (player != null) {
                UIRenderer.renderOverlay(shield, 50, 16, 16, scaledWidth - 100 / 2 - 70, 220, -90);
            }
        });
    }

    public static void renderTerrariaMana() {
        HudRenderCallback.EVENT.register((matrixstack, delta) -> {
            if (!TerrariaMod.CONFIG.useVanillaHud) {
                float scaledWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();

                if (Minecraft.getInstance().player != null) {
                    player = Minecraft.getInstance().player;
                }

                if (player != null) {
                    for (int i = 0; i <= player.getStats().getValue(Stats.CUSTOM.get(StatsT.MANA)) - 20; i++) {
                        if (i % 20 == 0) {
                            UIRenderer.renderOverlay(mana, 50, 10, 10, scaledWidth - 18, 10 + i / 2, -90);
                        }
                    }
                }
            }
        });
    }

    public static void renderTerrariaEffects() {
        HudRenderCallback.EVENT.register((matrixstack, delta) -> {
            if (TerrariaMod.CONFIG.showTerrariaBuffs) {
                if (Minecraft.getInstance().player != null) {
                    player = Minecraft.getInstance().player;
                }

                int i3 = 17;
                int j3 = 27;
                int effectCounter = 0;

                if (player != null) {
                    if (player.getStats().getValue(Stats.CUSTOM.get(StatsT.POTION_SICKNESS)) > 0) {
                        UIRenderer.renderOverlay(potion_sickness, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                        effectCounter++;
                    }

                    if (player.getStats().getValue(Stats.CUSTOM.get(StatsT.POISONED)) > 0) {
                        UIRenderer.renderOverlay(poisoned, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                        effectCounter++;
                    }

                    if (player.getStats().getValue(Stats.CUSTOM.get(StatsT.IRON_SKIN)) > 0) {
                        UIRenderer.renderOverlay(iron_skin, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                        effectCounter++;
                    }

                    if (player.getStats().getValue(Stats.CUSTOM.get(StatsT.HAPPY)) > 0) {
                        UIRenderer.renderOverlay(happy, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                        effectCounter++;
                    }

                    if (player.getStats().getValue(Stats.CUSTOM.get(StatsT.COZY_FIRE)) > 0) {
                        UIRenderer.renderOverlay(cozy_fire, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                        effectCounter++;
                    }

                    if (player.getStats().getValue(Stats.CUSTOM.get(StatsT.REGENERATION)) > 0) {
                        UIRenderer.renderOverlay(regeneration, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                        effectCounter++;
                    }

                    if (player.getStats().getValue(Stats.CUSTOM.get(StatsT.BLEEDING)) > 0) {
                        UIRenderer.renderOverlay(bleeding, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                        effectCounter++;
                    }
                    if (player.getStats().getValue(Stats.CUSTOM.get(StatsT.WATER_CANDLE)) > 0) {
                        UIRenderer.renderOverlay(water_candle, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
                        effectCounter++;
                    }
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
                        ResourceLocation icon = BuffsT.GetEntityActiveBuffIcons(entity).get(i);

                        UIRenderer.renderOverlay(icon, 50, 16, 16, 17 + i * 20f, 27, -90);
                    }
                }
            }
        });
    }
}
