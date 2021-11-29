package com.ryorama.terrariamod.weather;

import java.util.Random;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.core.client.TMusicTicker;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;

public abstract class WeatherBase {
	
	public static SoundEvent weatherMusic;
	
	public static enum WeatherTime {
		ANY,
		DAY,
		NIGHT
	}
	
	public static Random random = new Random();
	
	public static int currentWeather;
	
	public static float currentWeatherTime;
	public static float maxWeatherTime;
	
	public static boolean isClearWeather = true;
		
	public static void emitWeatherParticles() {}
		
	public static void tickWeather() {
		
		boolean day = false;
		
		if (MinecraftClient.getInstance().world != null) {
			day = MinecraftClient.getInstance().world.getTimeOfDay() >= 1000 && MinecraftClient.getInstance().world.getTimeOfDay() <= 13000;
		}
		
		if (random.nextInt(2000) == 0 && isClearWeather) {
			
			currentWeather = random.nextInt(2);
			
			maxWeatherTime = 3000 + random.nextInt(2000);
	
			isClearWeather = false;
			
			if (currentWeather == 1 && day) {
				weatherMusic = TAudio.WINDY_DAY;
			}
		}
		
		if (!isClearWeather) {
			currentWeatherTime += 1;
					
			emitWeatherParticles();
			if (TMusicTicker.currentMusic != weatherMusic && TMusicTicker.weatherMusicOverride == false && weatherMusic != null) {
				TMusicTicker.weatherMusicOverride = true;
				TMusicTicker.getTrack(weatherMusic);
			}
			
			if (currentWeatherTime >= maxWeatherTime) {
				TMusicTicker.weatherMusicOverride = false;
				currentWeatherTime = 0;
				isClearWeather = true;
			}
			
			if (currentWeather == 1 && !day) {
				TMusicTicker.weatherMusicOverride = false;
				currentWeatherTime = 0;
				isClearWeather = true;
			}
		}
	}
}
