package com.ryorama.terrariamod.world;

import com.ryorama.terrariamod.blocks.BlocksT;

import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.utils.WorldDataT;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction.Location;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;

public class EntitySpawner {


    public static EntityType[] groundDaytime = {EntitiesT.BLUE_SLIME.get(), EntitiesT.GREEN_SLIME.get()};
    public static EntityType[] groundCorruptionDaytime = {};
    public static EntityType[] bloodMoon = {};
    public static EntityType[] hardmodeGroundDaytime = {};
    public static EntityType[] hardmodeGroundNighttime = {};
    public static EntityType[] groundNighttime = {EntitiesT.DEMON_EYE.get(), EntityType.ZOMBIE};
    public static EntityType[] groundWaterDaytime = {};
    public static EntityType[] groundWaterNighttime = {};
    public static EntityType[] skyEntities = {};
    public static EntityType[] hardmodeSkyEntities = {};
    public static EntityType[] skyWaterEntities = {};
    public static EntityType[] caveEntities = {};
    public static EntityType[] hardmodeCaveEntities = {};
    public static EntityType[] caveWaterEntities = {};
    public static EntityType[] underworldEntities = {};
    public static EntityType[] rainEntities = {};
    public static EntityType[] mushroomBiomeEntities = {};
    public static EntityType[] oceanEntities = {};

    public static void spawnEntities(PlayerEntity player, int x, int y, int z) {
        World world = player.getEntityWorld();
        if (y >= 190) {
            spawnSkyEntity(world, x, y, z);
        }
        if (y > 45 && world.getTimeOfDay() % 24000 > 15000 && world.getTimeOfDay() % 24000 < 22000 && WorldDataT.bloodMoon) {
            spawnBloodMoonEntity(world, x, y, z);
        }
        if (y > 45) {
            spawnGroundEntity(world, x, y, z);
        }
        if (y > 45 && world.getBlockState(new BlockPos(x, y - 1, z)) == BlocksT.CORRUPTED_GRASS_BLOCK.get().getDefaultState()) {
            spawnCorruptionGroundEntity(world, x, y, z);
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

    public static boolean spawnSkyEntity(World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        EntityType[] list = EntitySpawner.skyEntities;

        if (world.getBlockState(pos) == Blocks.WATER.getDefaultState()) {
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

    public static boolean spawnBloodMoonEntity(World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        EntityType[] list = EntitySpawner.bloodMoon;

        if (list == null) return false;
        if (list.length == 0) return false;
        EntityType e = list[world.random.nextInt(list.length)];
        if (e == null)
            return false;
        spawnEntityAt(e, pos, world);
        return true;
    }

    public static boolean spawnHardmodeSkyEntity(World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        EntityType[] list = EntitySpawner.hardmodeSkyEntities;

        if (world.getBlockState(pos) == Blocks.WATER.getDefaultState()) {
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

    public static boolean spawnGroundEntity(World world, int x, int y, int z) {
        BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
        if (spawnpoint == null) return false;

        EntityType[] list = null;
        if (world.getTimeOfDay() % 24000 > 15000 && world.getTimeOfDay() % 24000 < 22000) {
            list = EntitySpawner.groundNighttime;
            if (world.getBlockState(spawnpoint) == Blocks.WATER.getDefaultState()) {
                list = EntitySpawner.groundWaterNighttime;
            }
        } else if (y < 190) {
            list = EntitySpawner.groundDaytime;
            if (world.getBlockState(spawnpoint) == Blocks.WATER.getDefaultState()) {
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

    public static boolean spawnCorruptionGroundEntity(World world, int x, int y, int z) {
        BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
        if (spawnpoint == null) return false;

        EntityType[] list = null;
        if (world.getTimeOfDay() % 24000 > 15000 && world.getTimeOfDay() % 24000 < 22000) {
            return false;
        } else if (y < 190) {
            list = EntitySpawner.groundCorruptionDaytime;
        }
        if (list == null) return false;
        if (list.length == 0) return false;
        EntityType e = list[world.random.nextInt(list.length)];
        if (e == null)
            return false;
        spawnEntityAt(e, spawnpoint, world);
        return true;
    }

    public static boolean spawnRainEntity(World world, int x, int y, int z) {
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

    public static boolean spawnHardmodeGroundEntity(World world, int x, int y, int z) {
        BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
        if (spawnpoint == null) return false;

        EntityType[] list = null;
        if (world.getTimeOfDay() % 24000 > 15000 && world.getTimeOfDay() % 24000 < 22000) {
            list = EntitySpawner.hardmodeGroundNighttime;
            if (world.getBlockState(spawnpoint) == Blocks.WATER.getDefaultState()) {
                list = EntitySpawner.groundWaterNighttime;
            }
        } else if (y < -190) {
            list = EntitySpawner.hardmodeGroundDaytime;
            if (world.getBlockState(spawnpoint) == Blocks.WATER.getDefaultState()) {
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

    public static boolean spawnCaveEntity(World world, int x, int y, int z) {
        BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
        System.out.println("Attempt spawn cave entity");
        if (spawnpoint == null) return false;
        for (int i = 1; i <= world.getRandom().nextInt(10); i++) {
            EntityType[] list = EntitySpawner.caveEntities;
            if (world.getBlockState(spawnpoint) == Blocks.WATER.getDefaultState()) {
                list = EntitySpawner.caveWaterEntities;
            }
            if (world.getBlockState(new BlockPos(spawnpoint.getX(), spawnpoint.getY() - 1, spawnpoint.getZ())) == BlocksT.MUSHROOM_GRASS.get().getDefaultState()) {
                list = EntitySpawner.mushroomBiomeEntities;
            }
            if (list == null) return false;
            if (list.length == 0) return false;
            EntityType e = list[world.random.nextInt(list.length)];
            if (e == null)
                return false;
            spawnEntityAt(e, spawnpoint, world);
            System.out.println("Attempt spawn cave entity");
        }

        return true;
    }

    public static boolean spawnHardmodeCaveEntity(World world, int x, int y, int z) {
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

    public static boolean spawnUnderworldEntity(World world, int x, int y, int z) {
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

    public static BlockPos getSuitableEntitySpawnpoint(World world, int x, int y, int z) {

        BlockPos pos = new BlockPos(x, y, z);
        if (!world.getBlockState(pos).blocksMovement()) {
            if (!world.getBlockState(new BlockPos(x, y + 1, z)).blocksMovement()) {
                if (world.getBlockState(new BlockPos(x, y - 1, z)).blocksMovement()) {
                    return pos;
                }
            }
        }

        if (!world.getBlockState(pos).blocksMovement()) {
            for (int i = 0; i < 20; i++) {
                if (world.getBlockState(new BlockPos(x, y - i, z)).blocksMovement()) {
                    return new BlockPos(x, y - i + 1, z);
                }
            }
        }

        if (world.getBlockState(new BlockPos(x, y, z)).blocksMovement())
        {
            int closestUpAir = 0;
            int closestDownAir = 0;
            for (closestUpAir = 0; closestUpAir < 10; closestUpAir++) {
                if (world.getBlockState(new BlockPos(x, y + closestUpAir, z)).blocksMovement() == false) {
                    if (world.getBlockState(new BlockPos(x, y + closestUpAir + 1, z)).blocksMovement() == false) {
                        break;
                    }
                }
            }
            for (closestDownAir = 0; closestDownAir < 10; closestDownAir++) {
                if (world.getBlockState(new BlockPos(x, y - closestUpAir, z)).blocksMovement() == false) {
                    if (world.getBlockState(new BlockPos(x, y - closestUpAir + 1, z)).blocksMovement() == false) {
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
        if (world.getBlockState(new BlockPos(x, y, z)).blocksMovement())
            return null;
        if (!world.getBlockState(new BlockPos(x, y - 1, z)).blocksMovement())
            return null;
        return new BlockPos(x, y, z);
    }
}