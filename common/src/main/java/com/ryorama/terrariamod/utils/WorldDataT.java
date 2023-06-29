package com.ryorama.terrariamod.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import com.ryorama.terrariamod.buffs.BuffT;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.WorldSavePath;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

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

    public static Identifier bossMusicOverride;

    public static boolean night;

    public static boolean day;

    public static boolean rain;

    public static boolean thunderstorm;

    public static boolean windyDay;

    public static ServerWorld worldMP;

    public static int worldEvil = 0;

    public static void setupWorldData() {
        if (firstUpdate) {
            File worldDif = new File("WorldSettingsDif.txt");
            System.out.println(worldDif.exists());
            System.out.println(worldDif.getAbsolutePath());
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

    public static void saveData(World world) throws IOException {
        NbtCompound nbtCompound = new NbtCompound();

        nbtCompound.putBoolean("expert", expert);
        nbtCompound.putBoolean("master", master);
        nbtCompound.putBoolean("bloodmoon", bloodMoon);
        nbtCompound.putBoolean("solarEclipse", solarEclipse);
        nbtCompound.putBoolean("hasStartingTools", hasStartingTools);
        nbtCompound.putBoolean("firstUpdate", firstUpdate);
        nbtCompound.putInt("worldEvil", worldEvil);

        File dataFile = new File(WorldSavePath.ROOT.getRelativePath() + "/saves/" + world.getServer().getSaveProperties().getLevelName() + "/worldSaveData.dat");
        if (!dataFile.exists()) {
            Files.createFile(Path.of(WorldSavePath.ROOT.getRelativePath() + "/saves/" + world.getServer().getSaveProperties().getLevelName() + "/worldSaveData.dat"));
        }
        NbtIo.writeCompressed(nbtCompound, dataFile);
    }

    public static void loadData(World world) throws IOException {
        NbtCompound nbtCompound = new NbtCompound();

        File dataFile = new File(WorldSavePath.ROOT.getRelativePath() + "/saves/" + world.getServer().getSaveProperties().getLevelName() + "/worldSaveData.dat");
        if (dataFile.exists()) {
            nbtCompound = NbtIo.readCompressed(dataFile);
        }

        expert = nbtCompound.getBoolean("expert");
        master = nbtCompound.getBoolean("master");
        hasStartingTools = nbtCompound.getBoolean("hasStartingTools");
        bloodMoon = nbtCompound.getBoolean("bloodmoon");
        solarEclipse = nbtCompound.getBoolean("solarEclipse");
        firstUpdate = nbtCompound.getBoolean("firstUpdate");
        worldEvil = nbtCompound.getInt("worldEvil");
    }

    public static void saveData(WorldAccess world) throws IOException {
        NbtCompound nbtCompound = new NbtCompound();

        nbtCompound.putBoolean("expert", expert);
        nbtCompound.putBoolean("master", master);
        nbtCompound.putBoolean("bloodmoon", bloodMoon);
        nbtCompound.putBoolean("solarEclipse", solarEclipse);
        nbtCompound.putBoolean("hasStartingTools", hasStartingTools);
        nbtCompound.putBoolean("firstUpdate", firstUpdate);
        nbtCompound.putInt("worldEvil", worldEvil);

        File dataFile = new File(WorldSavePath.ROOT.getRelativePath() + "/saves/" + world.getServer().getSaveProperties().getLevelName() + "/worldSaveData.dat");
        if (!dataFile.exists()) {
            Files.createFile(Path.of(WorldSavePath.ROOT.getRelativePath() + "/saves/" + world.getServer().getSaveProperties().getLevelName() + "/worldSaveData.dat"));
        }
        NbtIo.writeCompressed(nbtCompound, dataFile);
    }

    public static void loadData(WorldAccess world) throws IOException {
        NbtCompound nbtCompound = new NbtCompound();

        File dataFile = new File(WorldSavePath.ROOT.getRelativePath() + "/saves/" + world.getServer().getSaveProperties().getLevelName() + "/worldSaveData.dat");
        if (dataFile.exists()) {
            nbtCompound = NbtIo.readCompressed(dataFile);
        }

        expert = nbtCompound.getBoolean("expert");
        master = nbtCompound.getBoolean("master");
        hasStartingTools = nbtCompound.getBoolean("hasStartingTools");
        bloodMoon = nbtCompound.getBoolean("bloodmoon");
        solarEclipse = nbtCompound.getBoolean("solarEclipse");
        firstUpdate = nbtCompound.getBoolean("firstUpdate");
        worldEvil = nbtCompound.getInt("worldEvil");
    }
}