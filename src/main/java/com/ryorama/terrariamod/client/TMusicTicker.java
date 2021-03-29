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
	  public static boolean bossMusicOverride = false;
	  
	  public static SoundEvent currentBossTrack;
	  
	  public TMusicTicker(MinecraftClient client) {
	  	super(client);
	  }
	  
	  public static void onTickUpdate() {
		  if (musicChanged) {
			  if (!bossMusicOverride) {
				  client.getMusicTracker().stop();
				  client.getMusicTracker().play(new MusicSound(currentMusic, 0, 0, true));
				  musicChanged = false;
			  } else {
				  client.getMusicTracker().stop();
				  client.getMusicTracker().play(new MusicSound(currentBossTrack, 0, 0, true));
				  musicChanged = false;
			  }
		  }
		  
		  if (!bossMusicOverride) {
			  if (!client.getMusicTracker().isPlayingType(new MusicSound(currentMusic, 0, 0, true))) {
				  musicChanged = true;
			  }
		  } else {
			  if (!client.getMusicTracker().isPlayingType(new MusicSound(currentBossTrack, 0, 0, true))) {
				  musicChanged = true;
			  }
		  }
	  }
	  
	  public static SoundEvent getTrack(SoundEvent event) {
		  if (!bossMusicOverride) {
			  if (currentMusic != event) {
				  currentMusic = event;
				  musicChanged = true;
			  } else {
			  	return event;
			  }
		  } else {
			  if (!client.getMusicTracker().isPlayingType(new MusicSound(currentBossTrack, 0, 0, true))) {
				  musicChanged = true;
			  }
		  }
		  return event;
	  }
}