package com.ryorama.terrariamod.client;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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
    public static final SoundEvent ROAR_0 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "roar_0"));
    public static final SoundEvent STAR_FALL = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "star_fall"));
    public static final SoundEvent NPC_HIT1 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "npc_hit1"));
    public static final SoundEvent NPC_KILL1 = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "npc_killed1"));
    public static final SoundEvent TELEPORT = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "teleport"));
    public static final SoundEvent SUMMON = SoundEvent.of(new Identifier(TerrariaMod.MOD_ID, "summon"));

	public static void registerAudio() {
        /*
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "day1"), DAYONE);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "night"), NIGHT);
        Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "boss1"), BOSS1);
        Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "underground"), UNDERGROUND);
        Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "underworld"), UNDERWORLD);
        Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "title_screen"), TITLE_SCREEN);
         */

		Registry.register(Registries.SOUND_EVENT, new Identifier(TerrariaMod.MOD_ID, "health_crystal"), HEALTH_CRYSTAL);
        Registry.register(Registries.SOUND_EVENT, new Identifier(TerrariaMod.MOD_ID, "roar_0"), ROAR_0);
        Registry.register(Registries.SOUND_EVENT, new Identifier(TerrariaMod.MOD_ID, "star_fall"), STAR_FALL);
        Registry.register(Registries.SOUND_EVENT, new Identifier(TerrariaMod.MOD_ID, "npc_hit1"), NPC_HIT1);
        Registry.register(Registries.SOUND_EVENT, new Identifier(TerrariaMod.MOD_ID, "npc_killed1"), NPC_KILL1);
        Registry.register(Registries.SOUND_EVENT, new Identifier(TerrariaMod.MOD_ID, "teleport"), TELEPORT);
        Registry.register(Registries.SOUND_EVENT, new Identifier(TerrariaMod.MOD_ID, "summon"), SUMMON);
    }
}
