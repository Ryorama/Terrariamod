package com.ryorama.terrariamod.client;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

import java.util.ArrayList;
import java.util.List;

public class TAudio {

    public static List<String> musicNames = new ArrayList<>();

    public static final SoundEvent DAYONE = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "day1"));
    public static final SoundEvent NIGHT = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "night"));
    public static final SoundEvent UNDERGROUND = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "underground"));
    public static final SoundEvent UNDERWORLD = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "underworld"));
    public static final SoundEvent CORRUPTION = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "corruption"));
    public static final SoundEvent UNDERGROUND_CORRUPTION = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "underground_corruption"));
    public static final SoundEvent DESERT = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "desert"));
    public static final SoundEvent TUNDRA = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "snow"));
    public static final SoundEvent JUNGLE = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "jungle"));
    public static final SoundEvent MUSHROOM = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "mushroom"));
    public static final SoundEvent WINDY_DAY = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "windy_day"));

    public static final SoundEvent TITLE_SCREEN = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "title_screen"));
    public static final SoundEvent BOSS1 = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "boss1"));

    public static final SoundEvent HEALTH_CRYSTAL = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "health_crystal"));
    public static final SoundEvent DRINK = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "drink"));
    public static final SoundEvent EAT = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "eat"));
    public static final SoundEvent ROAR_0 = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "roar_0"));
    public static final SoundEvent STAR_FALL = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "star_fall"));
    public static final SoundEvent NPC_HIT1 = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "npc_hit1"));
    public static final SoundEvent NPC_KILL1 = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "npc_killed1"));
    public static final SoundEvent TELEPORT = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "teleport"));
    public static final SoundEvent SUMMON = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "summon"));
    public static final SoundEvent CRYSTAL_DESTROY = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "crystal_destroy"));

    public static final SoundEvent GRASS = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "grass"));

    public static final SoundEvent DIG_0 = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "dig0"));
    public static final SoundEvent TINK_0 = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "tink0"));
    public static final SoundEvent TINK_1 = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "tink1"));
    public static final SoundEvent TINK_2 = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "tink2"));
    public static final SoundEvent SNOW_HIT1 = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "snowhit1"));
    public static final SoundEvent ICE_HIT = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "icehit"));
    public static final SoundEvent RUN = SoundEvent.createVariableRangeEvent(new ResourceLocation(TerrariaMod.MOD_ID, "run"));

    public static final SoundType DIRT = new SoundType(1, 1, DIG_0, RUN, DIG_0, DIG_0, DIG_0);
    public static final SoundType STONE = new SoundType(1, 1, TINK_1, RUN, TINK_0, TINK_0, TINK_0);
    public static final SoundType GRASS_GRP = new SoundType(1, 0, GRASS, RUN, GRASS, GRASS, GRASS);
    public static final SoundType SNOW = new SoundType(1, 1, SNOW_HIT1, RUN, SNOW_HIT1, SNOW_HIT1, SNOW_HIT1);
    public static final SoundType ICE = new SoundType(1, 1, ICE_HIT, RUN, ICE_HIT, ICE_HIT, ICE_HIT);
    public static final SoundType LIFE_CRYSTAL_GRP = new SoundType(1, 1, CRYSTAL_DESTROY, RUN, CRYSTAL_DESTROY, CRYSTAL_DESTROY, CRYSTAL_DESTROY);

}
