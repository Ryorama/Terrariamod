package com.ryorama.terrariamod;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TAudio {
	
    public static final SoundEvent DAYONE = new SoundEvent(new Identifier(TerrariaMod.modid, "day1"));
    public static final SoundEvent NIGHT = new SoundEvent(new Identifier(TerrariaMod.modid, "night"));
    public static final SoundEvent UNDERGROUND = new SoundEvent(new Identifier(TerrariaMod.modid, "underground"));
    public static final SoundEvent UNDERWORLD = new SoundEvent(new Identifier(TerrariaMod.modid, "underworld"));

    public static final SoundEvent TITLE_SCREEN = new SoundEvent(new Identifier(TerrariaMod.modid, "title_screen"));
    public static final SoundEvent BOSS1 = new SoundEvent(new Identifier(TerrariaMod.modid, "boss1"));

    public static final SoundEvent ROAR_0 = new SoundEvent(new Identifier(TerrariaMod.modid, "roar_0"));
    public static final SoundEvent NPC_HIT1 = new SoundEvent(new Identifier(TerrariaMod.modid, "npc_hit1"));
    public static final SoundEvent NPC_KILL1 = new SoundEvent(new Identifier(TerrariaMod.modid, "npc_killed1"));
    
	public static void registerAudio() {
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.modid, "day1"), DAYONE);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.modid, "night"), NIGHT);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.modid, "title_screen"), TITLE_SCREEN);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.modid, "boss1"), BOSS1);

		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.modid, "roar_0"), ROAR_0);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.modid, "npc_hit1"), NPC_HIT1);
		Registry.register(Registry.SOUND_EVENT, new Identifier(TerrariaMod.modid, "npc_killed1"), NPC_KILL1);
	}
}
