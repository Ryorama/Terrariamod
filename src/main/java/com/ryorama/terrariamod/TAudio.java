package com.ryorama.terrariamod;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TAudio {
	
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

    public static final SoundEvent HEALTH_CRYSTAL = new SoundEvent(new Identifier(TerrariaMod.MODID, "health_crystal"));
    public static final SoundEvent ROAR_0 = new SoundEvent(new Identifier(TerrariaMod.MODID, "roar_0"));
    public static final SoundEvent NPC_HIT1 = new SoundEvent(new Identifier(TerrariaMod.MODID, "npc_hit1"));
    public static final SoundEvent NPC_KILL1 = new SoundEvent(new Identifier(TerrariaMod.MODID, "npc_killed1"));
    public static final SoundEvent TELEPORT = new SoundEvent(new Identifier(TerrariaMod.MODID, "teleport"));
    public static final SoundEvent SUMMON = new SoundEvent(new Identifier(TerrariaMod.MODID, "summon"));

	public static void registerAudio() {
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "day1"), DAYONE);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "night"), NIGHT);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "title_screen"), TITLE_SCREEN);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "boss1"), BOSS1);

		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "roar_0"), ROAR_0);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "npc_hit1"), NPC_HIT1);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "npc_killed1"), NPC_KILL1);
	}
}
