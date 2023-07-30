package com.ryorama.terrariamod.client;

import com.ryorama.terrariamod.TerrariaMod;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class TAudio {

    /*
    public static List<String> musicNames = new ArrayList<>();

    public static final SoundEvent DAYONE = new SoundEvent(new Identifier(TerrariaMod.MODID, "day1"));
    public static final SoundEvent NIGHT = new SoundEvent(new Identifier(TerrariaMod.MODID, "night"));
    public static final SoundEvent UNDERGROUND = new SoundEvent(new Identifier(TerrariaMod.MODID, "underground"));
    public static final SoundEvent UNDERWORLD = new SoundEvent(new Identifier(TerrariaMod.MODID, "underworld"));
    public static final SoundEvent CORRUPTION = new SoundEvent(new Identifier(TerrariaMod.MODID, "corruption"));
    public static final SoundEvent UNDERGROUND_CORRUPTION = new SoundEvent(new Identifier(TerrariaMod.MODID, "underground_corruption"));
    public static final SoundEvent DESERT = new SoundEvent(new Identifier(TerrariaMod.MODID, "desert"));
    public static final SoundEvent SNOW = new SoundEvent(new Identifier(TerrariaMod.MODID, "snow"));
    public static final SoundEvent JUNGLE = new SoundEvent(new Identifier(TerrariaMod.MODID, "jungle"));
    public static final SoundEvent MUSHROOM = new SoundEvent(new Identifier(TerrariaMod.MODID, "mushroom"));
    public static final SoundEvent WINDY_DAY = new SoundEvent(new Identifier(TerrariaMod.MODID, "windy_day"));

    public static final SoundEvent TITLE_SCREEN = new SoundEvent(new Identifier(TerrariaMod.MODID, "title_screen"));
    public static final SoundEvent BOSS1 = new SoundEvent(new Identifier(TerrariaMod.MODID, "boss1"));
     */

    public static final SoundEvent HEALTH_CRYSTAL = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "health_crystal"));
    public static final SoundEvent DRINK = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "drink"));
    public static final SoundEvent ROAR_0 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "roar_0"));
    public static final SoundEvent STAR_FALL = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "star_fall"));
    public static final SoundEvent NPC_HIT1 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "npc_hit1"));
    public static final SoundEvent NPC_KILL1 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "npc_killed1"));
    public static final SoundEvent TELEPORT = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "teleport"));
    public static final SoundEvent SUMMON = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "summon"));
    public static final SoundEvent CRYSTAL_DESTROY = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "crystal_destroy"));

    public static final SoundEvent GRASS = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "grass"));

    public static final SoundEvent DIG_0 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "dig0"));
    public static final SoundEvent TINK_0 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "tink0"));
    public static final SoundEvent TINK_1 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "tink1"));
    public static final SoundEvent TINK_2 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "tink2"));
    public static final SoundEvent SNOW_HIT1 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "snowhit1"));
    public static final SoundEvent ICE_HIT = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "icehit"));
    public static final SoundEvent RUN = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "run"));

    public static final BlockSoundGroup DIRT = new BlockSoundGroup(1, 1, DIG_0, RUN, DIG_0, DIG_0, DIG_0);
    public static final BlockSoundGroup STONE = new BlockSoundGroup(1, 1, TINK_1, RUN, TINK_0, TINK_0, TINK_0);
    public static final BlockSoundGroup GRASS_GRP = new BlockSoundGroup(1, 0, GRASS, RUN, GRASS, GRASS, GRASS);
    public static final BlockSoundGroup SNOW = new BlockSoundGroup(1, 1, SNOW_HIT1, RUN, SNOW_HIT1, SNOW_HIT1, SNOW_HIT1);
    public static final BlockSoundGroup ICE = new BlockSoundGroup(1, 1, ICE_HIT, RUN, ICE_HIT, ICE_HIT, ICE_HIT);
    public static final BlockSoundGroup LIFE_CRYSTAL_GRP = new BlockSoundGroup(1, 1, CRYSTAL_DESTROY, RUN, CRYSTAL_DESTROY, CRYSTAL_DESTROY, CRYSTAL_DESTROY);

}
