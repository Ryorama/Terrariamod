package com.ryorama.terrariamod;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class TAudio {

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

	public static void registerAudio() {
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "day1"), DAYONE);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "night"), NIGHT);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "title_screen"), TITLE_SCREEN);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID, "boss1"), BOSS1);
     }

    public static final SoundEvent HEALTH_CRYSTAL = new SoundEvent(new Identifier(TerrariaMod.MODID, "health_crystal"));
    public static final SoundEvent ROAR_0 = new SoundEvent(new Identifier(TerrariaMod.MODID, "roar_0"));
    public static final SoundEvent STAR_FALL = new SoundEvent(new Identifier(TerrariaMod.MODID, "star_fall"));
    public static final SoundEvent NPC_HIT1 = new SoundEvent(new Identifier(TerrariaMod.MODID, "npc_hit1"));
    public static final SoundEvent NPC_KILL1 = new SoundEvent(new Identifier(TerrariaMod.MODID, "npc_killed1"));
    public static final SoundEvent TELEPORT = new SoundEvent(new Identifier(TerrariaMod.MODID, "teleport"));
    public static final SoundEvent SUMMON = new SoundEvent(new Identifier(TerrariaMod.MODID, "summon"));

    /*
    public static void registerAudio() {
        TAudio.SoundEvents[] var1 = TAudio.SoundEvents.values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TAudio.SoundEvents e = var1[var3];
            Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.MODID,  e.toString().toLowerCase()), e.getSound());
        }
    }

    public static enum SoundEvents {
        MENU_MUSIC("menu_music"),
        DAY1("day1"),
        NIGHT("night"),
        CORRUPTION("corruption"),
        SNOW("snow"),
        DESERT("desert"),
        UNDERGROUND("underground"),
        UNDERGROUND_CORRUPTION("underground_corruption"),
        BLOODMOON("bloodmoon"),
        BOSS1("boss1"),
        BOSS2("boss2"),
        UNDERWORLD("underworld"),
        SC("sc"),
        CRIMSON("crimson"),
        HALLOW("hallow"),
        OCEAN("ocean"),
        JUNGLE("jungle"),
        BOSS3("boss3"),
        PLANTERA("plantera"),
        LUNAR_EVENT("lunar_event"),
        MOON_LORD("moon_lord"),
        GOLEM("golem"),
        RAIN("rain"),
        STORM("storm"),
        SLIME_RAIN("slime_rain");

        private SoundEvent sound;

        private SoundEvents(String name) {
            sound = new SoundEvent(new Identifier(TerrariaMod.MODID, name));
        }

        public SoundEvent getSound() {
            return this.sound;
        }
    }
    */
}
