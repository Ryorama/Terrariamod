package kmerrill285.trewrite.core.sounds;

import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundsT {
	
	public static final SoundEvent ROAR = new SoundEvent(new ResourceLocation("trewrite", "roar0"));
	public static final SoundEvent MENU_OPEN = new SoundEvent(new ResourceLocation("trewrite", "menu_open"));
	public static final SoundEvent MENU_CLOSE = new SoundEvent(new ResourceLocation("trewrite", "menu_close"));
	public static final SoundEvent HEALTH_CRYSTAL = new SoundEvent(new ResourceLocation("trewrite", "health_crystal"));

	public static final SoundEvent DIG = new SoundEvent(new ResourceLocation("trewrite", "dig0"));
	public static final SoundEvent TINK = new SoundEvent(new ResourceLocation("trewrite", "tink0"));
	public static final SoundEvent SHATTER = new SoundEvent(new ResourceLocation("trewrite", "shatter"));
	public static final SoundEvent GRAB = new SoundEvent(new ResourceLocation("trewrite", "grab"));

	public static final SoundEvent HIT1 = new SoundEvent(new ResourceLocation("trewrite", "npc_hit1"));
	public static final SoundEvent HIT2 = new SoundEvent(new ResourceLocation("trewrite", "npc_hit2"));
	public static final SoundEvent HIT13 = new SoundEvent(new ResourceLocation("trewrite", "npc_hit13"));
	public static final SoundEvent HIT19 = new SoundEvent(new ResourceLocation("trewrite", "npc_hit19"));
	
	public static final SoundEvent KILLED1 = new SoundEvent(new ResourceLocation("trewrite", "npc_killed1"));
	public static final SoundEvent KILLED2 = new SoundEvent(new ResourceLocation("trewrite", "npc_killed2"));
	public static final SoundEvent KILLED10 = new SoundEvent(new ResourceLocation("trewrite", "npc_killed10"));
	public static final SoundEvent KILLED12 = new SoundEvent(new ResourceLocation("trewrite", "npc_killed12"));
	
	public static final SoundEvent ZOMBIE1 = new SoundEvent(new ResourceLocation("trewrite", "zombie1"));
	
	public static final SoundType DIRT = new SoundType(100, 1, DIG, null, null, DIG, DIG);
	public static final SoundType STONE = new SoundType(100, 1, TINK, null, null, TINK, TINK);
	public static final SoundType POT = new SoundType(100, 1, SHATTER, null, null, SHATTER, SHATTER);

}