package kmerrill285.trewrite.core.sounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Music {
   @SubscribeEvent
   public static void handleScreenDraw(DrawScreenEvent event) {
      SimpleSound menu_music = SimpleSound.music(TAudio.SoundEvents.MENU_MUSIC.getSound());
      if (menu_music == null) {
         menu_music = SimpleSound.music(TAudio.SoundEvents.MENU_MUSIC.getSound());
      }

      if (event.getGui() instanceof MainMenuScreen) {
         Minecraft.getInstance().getMusicTicker().stop();
         Minecraft.getInstance().getMusicTicker();
         if (!Minecraft.getInstance().getSoundHandler().isPlaying(menu_music)) {
            Minecraft.getInstance().getSoundHandler().play(menu_music);
         }
      }

   }
}
