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

    public static List<String> musicNames = new ArrayList<>();

    public static final SoundEvent DAYONE = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "day1"));
    public static final SoundEvent NIGHT = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "night"));
    public static final SoundEvent UNDERGROUND = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "underground"));
    public static final SoundEvent UNDERWORLD = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "underworld"));
    public static final SoundEvent CORRUPTION = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "corruption"));
    public static final SoundEvent UNDERGROUND_CORRUPTION = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "underground_corruption"));
    public static final SoundEvent DESERT = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "desert"));
    public static final SoundEvent TUNDRA = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "snow"));
    public static final SoundEvent JUNGLE = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "jungle"));
    public static final SoundEvent MUSHROOM = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "mushroom"));
    public static final SoundEvent WINDY_DAY = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "windy_day"));

    public static final SoundEvent TITLE_SCREEN = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "title_screen"));
    public static final SoundEvent BOSS1 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "boss1"));

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
