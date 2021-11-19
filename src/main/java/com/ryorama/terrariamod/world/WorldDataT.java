package com.ryorama.terrariamod.world;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.mojang.datafixers.DataFixer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.argument.DimensionArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.WorldSavePath;
import net.minecraft.world.World;
import net.minecraft.world.WorldSaveHandler;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.storage.LevelStorage;
import org.jetbrains.annotations.Nullable;

public class WorldDataT {
	
	  public static boolean hardmode = false;
	  
	  public static boolean journey = false, expert = false, master = false;

	  public static boolean hasStartingTools = false;

	  public static boolean bloodMoon = false;
	  
	  public static boolean solarEclipse = false;
	  
	  public static float enemyDifficulty = 1.0F;
	  
	  public static final Set<String> defeatedBosses = new HashSet<>();
	  
	  public static final Set<UUID> activeBosses = new HashSet<>();
	  
	  public static int altarsBroken;

	  public static boolean night;
	  
	  public static boolean day;
	  
	  public static boolean rain;
	  
	  public static boolean thunderstorm;
	  
	  public static boolean windyDay;

	  public static void saveData(World world) throws IOException {
			NbtCompound nbtCompound = new NbtCompound();

			nbtCompound.putBoolean("bloodmoon", bloodMoon);
			nbtCompound.putBoolean("solarEclipse", solarEclipse);
			nbtCompound.putBoolean("hasStartingTools", hasStartingTools);

			nbtCompound.putBoolean("journey", journey);
			nbtCompound.putBoolean("expert", expert);
			nbtCompound.putBoolean("master", master);

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

		hasStartingTools = nbtCompound.getBoolean("hasStartingTools");
		bloodMoon = nbtCompound.getBoolean("bloodmoon");
		solarEclipse = nbtCompound.getBoolean("solarEclipse");

		journey = nbtCompound.getBoolean("journey");
		expert = nbtCompound.getBoolean("expert");
		master = nbtCompound.getBoolean("master");
	}
}
