package com.ryorama.terrariamod.entity;

import com.ryorama.terrariamod.ui.BossBar;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

public abstract interface IBoss {

	public abstract Identifier bossIcon();
	
	public abstract float getBossHealth();

	public abstract float getBossMaxHealth();
	
	public default void activateBoss() {
		BossBar.isRendering = true;
		BossBar.renderBossBar();

	}
	
	public default void defeatedBoss() {
		BossBar.isRendering = false;
		BossBar.renderBossBar();

	}
	
	public default void updateBossHealthBar() {
		BossBar.bossHealth = getBossHealth();
		BossBar.maxBossHealth = getBossMaxHealth();
	}
	
	public default void setBossIcon() {
		BossBar.setBossIcon(bossIcon());
	}
}