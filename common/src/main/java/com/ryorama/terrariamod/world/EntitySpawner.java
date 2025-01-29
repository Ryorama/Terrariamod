package com.ryorama.terrariamod.world;

import com.ryorama.terrariamod.blocks.BlocksT;

import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.utils.WorldDataT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.Blocks;

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

    public static void spawnEntities(Player player, int x, int y, int z) {
        Level world = player.level();
        if (y >= 190) {
            spawnSkyEntity(world, x, y, z);
        }
        if (y > 45 && world.getDayTime() % 24000 > 15000 && world.getDayTime() % 24000 < 22000 && WorldDataT.bloodMoon) {
            spawnBloodMoonEntity(world, x, y, z);
        }
        if (y > 45) {
            spawnGroundEntity(world, x, y, z);
        }
        if (y > 45 && world.getBlockState(new BlockPos(x, y - 1, z)) == BlocksT.CORRUPTED_GRASS_BLOCK.get().defaultBlockState()) {
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

    public static boolean spawnSkyEntity(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        EntityType[] list = EntitySpawner.skyEntities;

        if (world.getBlockState(pos) == Blocks.WATER.defaultBlockState()) {
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

    public static boolean spawnBloodMoonEntity(Level world, int x, int y, int z) {
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

    public static boolean spawnHardmodeSkyEntity(Level world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        EntityType[] list = EntitySpawner.hardmodeSkyEntities;

        if (world.getBlockState(pos) == Blocks.WATER.defaultBlockState()) {
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

    public static boolean spawnGroundEntity(Level world, int x, int y, int z) {
        BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
        if (spawnpoint == null) return false;

        EntityType[] list = null;
        if (world.getDayTime() % 24000 > 15000 && world.getDayTime() % 24000 < 22000) {
            list = EntitySpawner.groundNighttime;
            if (world.getBlockState(spawnpoint) == Blocks.WATER.defaultBlockState()) {
                list = EntitySpawner.groundWaterNighttime;
            }
        } else if (y < 190) {
            list = EntitySpawner.groundDaytime;
            if (world.getBlockState(spawnpoint) == Blocks.WATER.defaultBlockState()) {
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

    public static boolean spawnCorruptionGroundEntity(Level world, int x, int y, int z) {
        BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
        if (spawnpoint == null) return false;

        EntityType[] list = null;
        if (world.getDayTime() % 24000 > 15000 && world.getDayTime() % 24000 < 22000) {
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

    public static boolean spawnRainEntity(Level world, int x, int y, int z) {
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

    public static boolean spawnHardmodeGroundEntity(Level world, int x, int y, int z) {
        BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
        if (spawnpoint == null) return false;

        EntityType[] list = null;
        if (world.getDayTime() % 24000 > 15000 && world.getDayTime() % 24000 < 22000) {
            list = EntitySpawner.hardmodeGroundNighttime;
            if (world.getBlockState(spawnpoint) == Blocks.WATER.defaultBlockState()) {
                list = EntitySpawner.groundWaterNighttime;
            }
        } else if (y < -190) {
            list = EntitySpawner.hardmodeGroundDaytime;
            if (world.getBlockState(spawnpoint) == Blocks.WATER.defaultBlockState()) {
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

    public static boolean spawnCaveEntity(Level world, int x, int y, int z) {
        BlockPos spawnpoint = getSuitableEntitySpawnpoint(world, x, y, z);
        System.out.println("Attempt spawn cave entity");
        if (spawnpoint == null) return false;
        for (int i = 1; i <= world.getRandom().nextInt(10); i++) {
            EntityType[] list = EntitySpawner.caveEntities;
            if (world.getBlockState(spawnpoint) == Blocks.WATER.defaultBlockState()) {
                list = EntitySpawner.caveWaterEntities;
            }
            if (world.getBlockState(new BlockPos(spawnpoint.getX(), spawnpoint.getY() - 1, spawnpoint.getZ())) == BlocksT.MUSHROOM_GRASS.get().defaultBlockState()) {
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

    public static boolean spawnHardmodeCaveEntity(Level world, int x, int y, int z) {
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

    public static boolean spawnUnderworldEntity(Level world, int x, int y, int z) {
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

    public static void spawnEntityAt(EntityType type, BlockPos pos, Level world) {

        if (NaturalSpawner.isSpawnPositionOk(SpawnPlacements.Type.ON_GROUND, world, pos, type)) {
            Entity e = type.create(world);
            e.setPos(pos.getX(), pos.getY(), pos.getZ());
            world.addFreshEntity(e);
        }
    }

    public static BlockPos getSuitableEntitySpawnpoint(Level world, int x, int y, int z) {

        BlockPos pos = new BlockPos(x, y, z);
        if (!world.getBlockState(pos).blocksMotion()) {
            if (!world.getBlockState(new BlockPos(x, y + 1, z)).blocksMotion()) {
                if (world.getBlockState(new BlockPos(x, y - 1, z)).blocksMotion()) {
                    return pos;
                }
            }
        }

        if (!world.getBlockState(pos).blocksMotion()) {
            for (int i = 0; i < 20; i++) {
                if (world.getBlockState(new BlockPos(x, y - i, z)).blocksMotion()) {
                    return new BlockPos(x, y - i + 1, z);
                }
            }
        }

        if (world.getBlockState(new BlockPos(x, y, z)).blocksMotion())
        {
            int closestUpAir = 0;
            int closestDownAir = 0;
            for (closestUpAir = 0; closestUpAir < 10; closestUpAir++) {
                if (world.getBlockState(new BlockPos(x, y + closestUpAir, z)).blocksMotion() == false) {
                    if (world.getBlockState(new BlockPos(x, y + closestUpAir + 1, z)).blocksMotion() == false) {
                        break;
                    }
                }
            }
            for (closestDownAir = 0; closestDownAir < 10; closestDownAir++) {
                if (world.getBlockState(new BlockPos(x, y - closestUpAir, z)).blocksMotion() == false) {
                    if (world.getBlockState(new BlockPos(x, y - closestUpAir + 1, z)).blocksMotion() == false) {
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
        if (world.getBlockState(new BlockPos(x, y, z)).blocksMotion())
            return null;
        if (!world.getBlockState(new BlockPos(x, y - 1, z)).blocksMotion())
            return null;
        return new BlockPos(x, y, z);
    }
}