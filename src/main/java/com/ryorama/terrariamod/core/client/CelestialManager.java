package com.ryorama.terrariamod.core.client;

import com.ryorama.terrariamod.world.WorldDataT;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
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

    private static Identifier SUN_TEXTURES = new Identifier("terrariamod:textures/environment/sun.png");
    private static Identifier SOLAR_ECLIPSE_SUN_TEXTURES = new Identifier("terrariamod:textures/environment/sun_eclipse.png");

    public static void handleMoon(World worldIn) {

        boolean night = worldIn.isNight();
        //worldIn.getTimeOfDay() % 24000L > 15000L && worldIn.getTimeOfDay() % 24000L < 22000L;
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

            if (rand.nextInt(10) <= 5 & !alreadyAttemptedBloodMoon) {
                WorldDataT.bloodMoon = true;
                if (!worldIn.isClient()) {
                    for (int p = 0; p < worldIn.getServer().getPlayerManager().getPlayerList().size() - 1; p++) {
                        worldIn.getServer().getPlayerManager().getPlayerList().get(p).sendMessage((Text.translatable("A bloodmoon is rising!")).formatted(Formatting.GREEN).formatted(Formatting.BOLD), false);
                    }
                }
            }

            alreadyAttemptedBloodMoon = true;
        }

        if (!night) {
            WorldDataT.bloodMoon = false;
        }
    }

    public static void handleSolarEvents(World worldIn) {
        boolean night = worldIn.isDay();

        if (!WorldDataT.solarEclipse && night) {
            WorldRenderer.SUN = SUN_TEXTURES;

            alreadyAttemptedBloodMoon = false;
            alreadyNight = false;

            if (rand.nextInt(50) <= 5 && WorldDataT.hardmode == true && !alreadyAttemptedSolarEclipse) {
                WorldDataT.solarEclipse = true;
                for (int p = 0; p < worldIn.getServer().getPlayerManager().getPlayerList().size() - 1; p++) {
                    worldIn.getServer().getPlayerManager().getPlayerList().get(p).sendMessage((Text.translatable("A solar eclipse is happening!")).formatted(Formatting.GREEN, Formatting.BOLD), false);
                }
            }

            alreadyAttemptedSolarEclipse = true;
        }

        if (!night) {
            WorldDataT.solarEclipse = false;
        }

        if (WorldDataT.solarEclipse) {
            WorldRenderer.SUN = SOLAR_ECLIPSE_SUN_TEXTURES;
        }

    }
}
