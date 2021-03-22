package com.ryorama.terrariamod.world;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class WorldDataT {
	
	  public static boolean hardmode = false;
	  
	  public static boolean expert = false, master = false;
	  
	  public static boolean isTerrariaWorld = true;
	  
	  public static boolean bloodMoon = false;
	  
	  public static boolean solarEclipse = false;
	  
	  public static float enemyDifficulty = 1.0F;
	  
	  public static final Set<String> defeatedBosses = new HashSet<>();
	  
	  public static final Set<UUID> activeBosses = new HashSet<>();
	  
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
	  
	  public static World world;		  	  
}
