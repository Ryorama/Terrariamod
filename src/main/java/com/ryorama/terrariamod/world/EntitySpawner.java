package com.ryorama.terrariamod.world;

import com.ryorama.terrariamod.entity.EntitiesT;

import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction.Location;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;

public class EntitySpawner {


	public static EntityType[] groundDaytime = {EntitiesT.BLUE_SLIME, EntitiesT.GREEN_SLIME};
	public static EntityType[] hardmodeGroundDaytime = {};
	public static EntityType[] hardmodeGroundNighttime = {};
	public static EntityType[] groundNighttime = {EntitiesT.DEMON_EYE};
	public static EntityType[] groundWaterDaytime = {};
	public static EntityType[] groundWaterNighttime = {};
	public static EntityType[] skyEntities = {};
	public static EntityType[] hardmodeSkyEntities = {};
	public static EntityType[] skyWaterEntities = {};
	public static EntityType[] caveEntities = {};
	public static EntityType[] hardmodeCaveEntities = {};
	public static EntityType[] caveWaterEntities = {};
	public static EntityType[] underworldEntities = {EntitiesT.DEMON};
	public static EntityType[] rainEntities = {};
	
	public static EntityType[] oceanEntities = {};
	
	public static void spawnEntities(PlayerEntity player, double x, double y, double z) {
		World world = player.getEntityWorld();
			if (y >= 190) {
				spawnSkyEntity(world, x, y, z);
			}
			if (y > 45) {
				spawnGroundEntity(world, x, y, z);
			}
			if (y <= 60) {
				spawnCaveEntity(world, x, y, z);
			}
			if (y <= -125) {
				spawnUnderworldEntity(world, x, y, z);
			}
			if (y > 45) {
				if (world.isRaining()) {
					spawnRainEntity(world, x, y, z);
				}
			}
	}
	
	public static boolean spawnSkyEntity(World world, double x, double y, double z) {
		BlockPos pos = new BlockPos(x, y, z);
		if (world.getBlockState(pos).getMaterial().blocksMovement())
			return false;
		EntityType[] list = EntitySpawner.skyEntities;
		
		if (world.getBlockState(pos).getMaterial() == Material.WATER) {
			list = EntitySpawner.skyWaterEntities;
		}
		if (list == null) return false;
		if (list.length == 0) return false;
		EntityType e = list[world.random.nextInt(list.length)];
		if (e == null)
			return false;
		spawnEntityAt(e, pos, world);
		return true;
	}
	
	public static boolean spawnHardmodeSkyEntity(World world, double x, double y, double z) {
		BlockPos pos = new BlockPos(x, y, z);
		if (world.getBlockState(pos).getMaterial().blocksMovement())
			return false;
		EntityType[] list = EntitySpawner.hardmodeSkyEntities;
		
		if (world.getBlockState(pos).getMaterial() == Material.WATER) {
			list = EntitySpawner.skyWaterEntities;
		}
		if (list == null) return false;
		if (list.length == 0) return false;
		EntityType e = list[world.random.nextInt(list.length)];
		if (e == null)
			return false;
		spawnEntityAt(e, pos, world);
		return true;
	}
	
	public static boolean spawnGroundEntity(World world, double x, double y, double z) {
		BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
		if (spawnpoint == null) return false;
		
		EntityType[] list = null;
		if (world.getTimeOfDay() % 24000 > 15000 && world.getTimeOfDay() % 24000 < 22000) {
			list = EntitySpawner.groundNighttime;
			if (world.getBlockState(spawnpoint).getMaterial() == Material.WATER) {
				list = EntitySpawner.groundWaterNighttime;
			}
		} else if (y < 190) {
			list = EntitySpawner.groundDaytime;
			if (world.getBlockState(spawnpoint).getMaterial() == Material.WATER) {
				list = EntitySpawner.groundWaterDaytime;
			}
		}
		if (list == null) return false;
		if (list.length == 0) return false;
		EntityType e = list[world.random.nextInt(list.length)];
		if (e == null)
			return false;
		spawnEntityAt(e, spawnpoint, world);
		return true;
	}
	
	public static boolean spawnRainEntity(World world, double x, double y, double z) {
		BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
		if (spawnpoint == null) return false;
		EntityType[] list = EntitySpawner.rainEntities;
		if (list == null) return false;
		if (list.length == 0) return false;
		EntityType e = list[world.random.nextInt(list.length)];
		if (e == null)
			return false;
		spawnEntityAt(e, spawnpoint, world);
		return true;
	}
	
	public static boolean spawnHardmodeGroundEntity(World world, double x, double y, double z) {
		BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
		if (spawnpoint == null) return false;
		
		EntityType[] list = null;
		if (world.getTimeOfDay() % 24000 > 15000 && world.getTimeOfDay() % 24000 < 22000) {
			list = EntitySpawner.hardmodeGroundNighttime;
			if (world.getBlockState(spawnpoint).getMaterial() == Material.WATER) {
				list = EntitySpawner.groundWaterNighttime;
			}
		} else if (y < -190) {
			list = EntitySpawner.hardmodeGroundDaytime;
			if (world.getBlockState(spawnpoint).getMaterial() == Material.WATER) {
				list = EntitySpawner.groundWaterDaytime;
			}
		}
		if (list == null) return false;
		if (list.length == 0) return false;
		EntityType e = list[world.random.nextInt(list.length)];
		if (e == null)
			return false;
		spawnEntityAt(e, spawnpoint, world);
		return true;
	}
	
	public static boolean spawnCaveEntity(World world, double x, double y, double z) {
		BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
		if (spawnpoint == null) return false;
		EntityType[] list = EntitySpawner.caveEntities;
		if (world.getBlockState(spawnpoint).getMaterial() == Material.WATER) {
			list = EntitySpawner.caveWaterEntities;
		}
		if (list == null) return false;
		if (list.length == 0) return false;
		EntityType e = list[world.random.nextInt(list.length)];
		if (e == null)
			return false;
		spawnEntityAt(e, spawnpoint, world);
		return true;
	}
	
	public static boolean spawnHardmodeCaveEntity(World world, double x, double y, double z) {
		BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
		if (spawnpoint == null) return false;
		EntityType[] list = EntitySpawner.hardmodeCaveEntities;
		if (list == null) return false;
		if (list.length == 0) return false;
		EntityType e = list[world.random.nextInt(list.length)];
		if (e == null)
			return false;
		spawnEntityAt(e, spawnpoint, world);
		return true;
	}
	
	public static boolean spawnUnderworldEntity(World world, double x, double y, double z) {
		BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
		if (spawnpoint == null) return false;
		EntityType[] list = EntitySpawner.underworldEntities;
		if (list == null) return false;
		if (list.length == 0) return false;
		EntityType e = list[world.random.nextInt(list.length)];
		
		if (e == null)
			return false;
		spawnEntityAt(e, spawnpoint, world);
		return true;
	}
	
	public static void spawnEntityAt(EntityType type, BlockPos pos, World world) {
		
		if (SpawnHelper.canSpawn(Location.ON_GROUND, world, pos, type)) {
			Entity e = type.create(world);
			e.setPosition(pos.getX(), pos.getY(), pos.getZ());
			world.spawnEntity(e);
		}
	}
	
	public static BlockPos getSuitableEntitySpawnpoint(World world, double x, double y, double z) {
		
		BlockPos pos = new BlockPos(x, y, z);
		if (!world.getBlockState(pos).getMaterial().blocksMovement()) {
			if (!world.getBlockState(new BlockPos(x, y + 1, z)).getMaterial().blocksMovement()) {
				if (world.getBlockState(new BlockPos(x, y - 1, z)).getMaterial().blocksMovement()) {
					return pos;
				}
			}
		}
		
		if (!world.getBlockState(pos).getMaterial().blocksMovement()) {
			for (int i = 0; i < 20; i++) {
				if (world.getBlockState(new BlockPos(x, y - i, z)).getMaterial().blocksMovement()) {
					return new BlockPos(x, y - i + 1, z);
				}
			}
		}
		
		if (world.getBlockState(new BlockPos(x, y, z)).getMaterial().blocksMovement())
		{
			int closestUpAir = 0;
			int closestDownAir = 0;
			for (closestUpAir = 0; closestUpAir < 10; closestUpAir++) {
				if (world.getBlockState(new BlockPos(x, y + closestUpAir, z)).getMaterial().blocksMovement() == false) {
					if (world.getBlockState(new BlockPos(x, y + closestUpAir + 1, z)).getMaterial().blocksMovement() == false) {
						break;
					}
				}
			}
			for (closestDownAir = 0; closestDownAir < 10; closestDownAir++) {
				if (world.getBlockState(new BlockPos(x, y - closestUpAir, z)).getMaterial().blocksMovement() == false) {
					if (world.getBlockState(new BlockPos(x, y - closestUpAir + 1, z)).getMaterial().blocksMovement() == false) {
						break;
					}
				}
			}
			if (closestUpAir < closestDownAir) {
				y = y + closestUpAir;
			} else {
				y = y - closestDownAir;
			}
		}
		if (world.getBlockState(new BlockPos(x, y, z)).getMaterial().blocksMovement())
			return null;
		if (!world.getBlockState(new BlockPos(x, y - 1, z)).getMaterial().blocksMovement())
			return null;
		return new BlockPos(x, y, z);
	}
}
