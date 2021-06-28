package com.ryorama.terrariamod.ui;

import com.ryorama.terrariamod.TerrariaMod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BossBar extends DrawableHelper {
		
	public static BossBar instance = new BossBar();
	
	public static Identifier boss_health_bar = new Identifier(TerrariaMod.MODID, "textures/ui/boss_bar_background.png");
	public static Identifier boss_health_bar_progress_bg = new Identifier(TerrariaMod.MODID, "textures/ui/boss_bar_progress.png");
	public static Identifier boss_health_bar_progress_fg = new Identifier(TerrariaMod.MODID, "textures/ui/boss_bar_forground.png");
	public static Identifier boss_health_bar_icon;

	public static boolean isRendering = false;
	
	public static float healthBarWidth = 0.1f;
	
	public static float bossHealth = 0;
	public static float maxBossHealth = 0;
		
	public static int maxBossHealthForBar = 177;
	
	public BossBar() {
		super();
	}
	
	public static void setBossIcon(Identifier icon) {
		boss_health_bar_icon = icon;
	}
	
	public static void renderBossBar() {
			
		System.out.println("Changing rendering");
		HudRenderCallback.EVENT.register((matrixstack, delta) -> {
			if (isRendering) {
				
				float percent = bossHealth / maxBossHealth;
				
				float scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
				
				UIRenderer.instance.renderOverlay(boss_health_bar_progress_bg, 1f, 189, 8, scaledWidth / 2 - 130, 10, -90);
				if (bossHealth > 0) {
					UIRenderer.instance.renderOverlay(boss_health_bar_progress_fg, 1f, 177 * percent, 8, scaledWidth / 2 - 118, 10, -90);				
				}
				UIRenderer.instance.renderOverlay(boss_health_bar, 1f, 200, 25, scaledWidth / 2 - 130, 0, -90);
				UIRenderer.instance.renderOverlay(boss_health_bar_icon, 1f, 10, 10, scaledWidth / 2 - 128.5f, 9, -90);
			}
		});
	}
}