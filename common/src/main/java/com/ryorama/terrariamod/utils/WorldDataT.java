package com.ryorama.terrariamod.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Level;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.storage.LevelResource;

public class WorldDataT {

    public static Random random = new Random();

    public static boolean hardmode = false;

    public static boolean expert = false, master = false;

    public static boolean hasStartingTools = false;

    public static boolean isTerrariaWorld = true;

    public static boolean bloodMoon = false;

    public static boolean solarEclipse = false;

    public static float enemyDifficulty = 1.0F;

    public static final Set<String> defeatedBosses = new HashSet<>();

    public static final Set<UUID> activeBosses = new HashSet<>();

    public static boolean isCorruption = false, isCrimson = false;

    public static int altarsBroken;

    public static long totalTime;

    public static boolean firstUpdate = true;

    public static ResourceLocation bossMusicOverride;

    public static boolean night;

    public static boolean day;

    public static boolean rain;

    public static boolean thunderstorm;

    public static boolean windyDay;

    public static ServerLevel worldMP;

    public static int worldEvil = 0;

    public static void setupWorldData() {
        if (firstUpdate) {
            File worldDif = new File("WorldSettingsDif.txt");
            if (worldDif.exists()) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(worldDif));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String content = null;
                try {
                    content = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int diff = Integer.parseInt(content);

                switch (diff) {
                    case 0:
                        expert = false;
                        master = false;
                    case 1:
                        expert = false;
                        master = false;
                    case 2:
                        expert = true;
                        master = false;
                    case 3:
                        expert = false;
                        master = true;
                }
            }

            worldEvil = random.nextInt(2);
        }
    }

    public static void saveData(LevelAccessor world) throws IOException {
        CompoundTag compoundTag = new CompoundTag();

        compoundTag.putBoolean("expert", expert);
        compoundTag.putBoolean("master", master);
        compoundTag.putBoolean("bloodmoon", bloodMoon);
        compoundTag.putBoolean("solarEclipse", solarEclipse);
        compoundTag.putBoolean("hasStartingTools", hasStartingTools);
        compoundTag.putBoolean("firstUpdate", firstUpdate);
        compoundTag.putInt("worldEvil", worldEvil);

        File dataFile = new File(LevelResource.ROOT.getId() + "/saves/" + world.getServer().getWorldData().getLevelName() + "/worldSaveData.dat");
        if (!dataFile.exists()) {
            Files.createFile(Path.of(LevelResource.ROOT.getId() + "/saves/" + world.getServer().getWorldData().getLevelName() + "/worldSaveData.dat"));
        }
        NbtIo.writeCompressed(compoundTag, dataFile);
    }

    public static void loadData(LevelAccessor world) throws IOException {
        CompoundTag compoundTag = new CompoundTag();

        File dataFile = new File(LevelResource.ROOT.getId() + "/saves/" + world.getServer().getWorldData().getLevelName() + "/worldSaveData.dat");
        if (dataFile.exists()) {
            compoundTag = NbtIo.readCompressed(dataFile);
        }

        expert = compoundTag.getBoolean("expert");
        master = compoundTag.getBoolean("master");
        hasStartingTools = compoundTag.getBoolean("hasStartingTools");
        bloodMoon = compoundTag.getBoolean("bloodmoon");
        solarEclipse = compoundTag.getBoolean("solarEclipse");
        firstUpdate = compoundTag.getBoolean("firstUpdate");
        worldEvil = compoundTag.getInt("worldEvil");
    }
}