package com.ryorama.terrariamod.ui;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.entity.EntityBaseMob;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;

public class BossBar  {
	
	public static Identifier boss_bar_background = new Identifier(TerrariaMod.modid, "textures/ui/boss_bar_background.png");
	
	public static void drawBossBar(EntityBaseMob boss)  {
		
		float health = boss.getHealth();
	}
}
