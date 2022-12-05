package com.ryorama.terrariamod.world;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.WorldSavePath;
import net.minecraft.world.World;

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
	  	  
	  public static boolean firstUpdate;
	  
	  public static Identifier bossMusicOverride;
	  	  
	  public static boolean night;
	  
	  public static boolean day;
	  
	  public static boolean rain;
	  
	  public static boolean thunderstorm;
	  
	  public static boolean windyDay;
	  	  
	  public static ServerWorld worldMP;

	  public static int worldEvil = 0;

	  public static void setupWorldData() {
		 	ServerTickEvents.START_WORLD_TICK.register(callbacks -> {
				if (firstUpdate) {
					worldEvil = random.nextInt(1);
				}
			});
	  }

	  public static void saveData(World world) throws IOException {

	  		File worldDif = new File("WorldSettinsDif");
	  		if (worldDif.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(worldDif));
				String content = reader.readLine();

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

			NbtCompound nbtCompound = new NbtCompound();

	  		nbtCompound.putBoolean("expert", expert);
	  		nbtCompound.putBoolean("master", master);
			nbtCompound.putBoolean("bloodmoon", bloodMoon);
			nbtCompound.putBoolean("solarEclipse", solarEclipse);
			nbtCompound.putBoolean("hasStartingTools", hasStartingTools);
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
		worldEvil = nbtCompound.getInt("worldEvil");
	}
}
