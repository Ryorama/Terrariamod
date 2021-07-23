package kmerrill285.trewrite.core.sounds;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class TAudio {
		
   @SubscribeEvent
   public static void registerAudio(final RegistryEvent.Register<SoundEvent> event) {
      TAudio.SoundEvents[] var1 = TAudio.SoundEvents.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         TAudio.SoundEvents e = var1[var3];
         event.getRegistry().register(e.getSound());
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
      BLOODMOON("blood_moon"),
      BOSS1("boss1"),
      BOSS2("boss2"),
      UNDERWORLD("underworld"),
      SC("sc"),
      CRIMSON("crimson"),
      HALLOW("hallow"),
      OCEAN("ocean"),
      JUNGLE("jungle"),
      BOSS3("boss3"),
      ROAR("roar0"),
      MENU_OPEN("menu_open"),
      MENU_CLOSE("menu_close"),
      GRAB("grab"),
      HEALTH_CRYSTAL("health_crystal"),
      CRYSTAL_DESTROY("crystal_destroy"),
      TINK("tink0"),
      DIG("dig0"),
      SHATTER("shatter"),
      PLANTERA("plantera"),
      LUNAR_EVENT("lunar_event"),
      MOON_LORD("moon_lord"),
      GOLEM("golem"),
      RAIN("rain"),
      STORM("storm"),
      SLIME_RAIN("slime_rain");

      private SoundEvent sound;

      private SoundEvents(String name) {
         ResourceLocation loc = new ResourceLocation("trewrite", name);
         this.sound = (SoundEvent)(new SoundEvent(loc)).setRegistryName(name);
      }

      public SoundEvent getSound() {
         return this.sound;
      }
   }
}
