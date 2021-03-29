package com.ryorama.terrariamod.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;

@Environment(EnvType.CLIENT)
public class TMusicTicker extends MusicTracker {
	  private static MinecraftClient client = MinecraftClient.getInstance();
	  public static SoundEvent currentMusic;
	  public static boolean musicChanged;
	  
	  public TMusicTicker(MinecraftClient client) {
	  	super(client);
	  }
	  
	  public static void onTickUpdate() {
		  if (musicChanged) {
			  client.getMusicTracker().stop();
			  client.getMusicTracker().play(new MusicSound(currentMusic, 0, 0, true));
			  musicChanged = false;
		  }
	  }
	  
	  public static SoundEvent getTrack(SoundEvent event) {
		  if (currentMusic != event) {
			  currentMusic = event;
			  musicChanged = true;
		  } else if (!client.getMusicTracker().isPlayingType(new MusicSound(currentMusic, 0, 0, true))) {
			musicChanged = true;
		  } else {
		  	return event;
		  }
		  return event;
	  }
}