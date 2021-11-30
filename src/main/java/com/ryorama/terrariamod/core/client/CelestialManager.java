package com.ryorama.terrariamod.core.client;

import com.ryorama.terrariamod.world.WorldDataT;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import java.util.Random;

public class CelestialManager {
    static Random rand = new Random();

    public static boolean alreadyAttemptedBloodMoon;
    public static boolean alreadyNight = false;

    private static Identifier MOON_PHASES_TEXTURES = new Identifier("terrariamod:textures/environment/moon_phases.png");
    private static Identifier MOON_PHASES_TEXTURES2 = new Identifier("terrariamod:textures/environment/moon_2.png");
    private static Identifier MOON_PHASES_TEXTURES3 = new Identifier("terrariamod:textures/environment/moon_3.png");
    private static Identifier MOON_PHASES_TEXTURES4 = new Identifier("terrariamod:textures/environment/moon_4.png");
    private static Identifier MOON_PHASES_TEXTURES5 = new Identifier("terrariamod:textures/environment/moon_5.png");

    private static Identifier BLOOD_MOON_PHASES_TEXTURES = new Identifier("terrariamod:textures/environment/blood_moon_phases.png");

    public static boolean alreadyAttemptedSolarEclipse;
    public static boolean canAttemptEvents;

    private static Identifier SUN_TEXTURES = new Identifier("terrariamod:textures/environment/sun.png");
    private static Identifier SOLAR_ECLIPSE_SUN_TEXTURES = new Identifier("terrariamod:textures/environment/sun_eclipse.png");

    public static void handleMoon(World worldIn) {
        boolean night = worldIn.getTimeOfDay() % 24000L > 15000L && worldIn.getTimeOfDay() % 24000L < 22000L;
        if (!WorldDataT.bloodMoon && night) {

            alreadyAttemptedSolarEclipse = false;

            if (!alreadyNight) {
                int moonTextureIndex = rand.nextInt(5);

                if (moonTextureIndex == 0) {
                    WorldRenderer.MOON_PHASES = MOON_PHASES_TEXTURES;
                } else if (moonTextureIndex == 1) {
                    WorldRenderer.MOON_PHASES = MOON_PHASES_TEXTURES2;
                } else if (moonTextureIndex == 2) {
                    WorldRenderer.MOON_PHASES = MOON_PHASES_TEXTURES3;
                } else if (moonTextureIndex == 3) {
                    WorldRenderer.MOON_PHASES = MOON_PHASES_TEXTURES4;
                } else if (moonTextureIndex == 4) {
                    WorldRenderer.MOON_PHASES = MOON_PHASES_TEXTURES5;
                } else {
                    WorldRenderer.MOON_PHASES = MOON_PHASES_TEXTURES;
                }

                alreadyNight = true;
            }

            for (int p = 0; p < worldIn.getPlayers().size(); p++) {
                if (worldIn.getPlayers().get(p).getMaxHealth() >= 120) {
                    canAttemptEvents = true;
                } else {
                    canAttemptEvents = false;
                }
            }

            if (rand.nextInt(200) <= 5 && !alreadyAttemptedBloodMoon && canAttemptEvents) {
                WorldDataT.bloodMoon = true;
                for (int p = 0; p < worldIn.getPlayers().size(); p++) {
                    worldIn.getPlayers().get(p).sendMessage((new TranslatableText("A bloodmoon is rising!")).formatted(Formatting.GREEN).formatted(Formatting.BOLD), false);
                }
                alreadyAttemptedBloodMoon = true;
            } else {
                alreadyAttemptedBloodMoon = true;
            }
        }

        if (!night) {
            WorldDataT.bloodMoon = false;
        }

        if (WorldDataT.bloodMoon) {
            WorldRenderer.MOON_PHASES = BLOOD_MOON_PHASES_TEXTURES;
        }
    }

    public static void handleSolarEvents(World worldIn) {
        boolean night = worldIn.getTimeOfDay() % 24000L < 15000L || worldIn.getTimeOfDay() % 24000L > 22500L;
        if (!WorldDataT.solarEclipse && night) {
            WorldRenderer.SUN = SUN_TEXTURES;

            alreadyAttemptedBloodMoon = false;
            alreadyNight = false;

            if (rand.nextInt(200) <= 5 && WorldDataT.hardmode && !alreadyAttemptedSolarEclipse) {
                WorldDataT.solarEclipse = true;
                for (int p = 0; p < worldIn.getPlayers().size(); p++) {
                    worldIn.getPlayers().get(p).sendMessage((new TranslatableText("A solar eclipse is happening!")).formatted(Formatting.GREEN, Formatting.BOLD), false);
                }
                alreadyAttemptedSolarEclipse = true;
            } else {
                alreadyAttemptedSolarEclipse = true;
            }
        }

        if (!night) {
            WorldDataT.solarEclipse = false;
        }

        if (WorldDataT.solarEclipse) {
            WorldRenderer.SUN = SOLAR_ECLIPSE_SUN_TEXTURES;
        }

    }
}
