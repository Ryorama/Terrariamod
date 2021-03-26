package com.ryorama.terrariamod.entity;

import com.ryorama.terrariamod.ui.BossBar;

import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvent;

public abstract interface IBoss {
	
	public abstract SoundEvent setBossMusic();

}