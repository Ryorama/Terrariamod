package com.ryorama.terrariamod.entity;

import com.ryorama.terrariamod.core.client.TMusicTicker;
import com.ryorama.terrariamod.ui.BossBar;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public abstract interface IBoss {
	
	public abstract SoundEvent setBossMusic();
	
	public abstract Identifier bossIcon();
	
	public abstract float getBossHealth();

	public abstract float getBossMaxHealth();
	
	public default void activateBoss() {
		BossBar.isRendering = true;
		BossBar.renderBossBar();
		TMusicTicker.bossMusicOverride = true;
		TMusicTicker.currentBossTrack = setBossMusic();
		TMusicTicker.getTrack(setBossMusic());
	}
	
	public default void defeatedBoss() {
		BossBar.isRendering = false;
		BossBar.renderBossBar();
		TMusicTicker.bossMusicOverride = false;
		TMusicTicker.getTrack(TMusicTicker.currentMusic);
	}
	
	public default void updateBossHealthBar() {
		BossBar.bossHealth = getBossHealth();
		BossBar.maxBossHealth = getBossMaxHealth();
	}
	
	public default void setBossIcon() {
		BossBar.setBossIcon(bossIcon());
	}
}