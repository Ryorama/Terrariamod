package com.ryorama.terrariamod.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.ryorama.terrariamod.TerrariaMod;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class BossBar extends DrawableHelper {
		
	public static BossBar instance = new BossBar();
	
	public static Identifier boss_health_bar = new Identifier(TerrariaMod.modid, "textures/ui/boss_bar_background.png");
	
	public BossBar() {
		super();
	}

	public static void render() {
		
		MatrixStack matrices = new MatrixStack();

		matrices.push();
		
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShader(GameRenderer::method_34542);
        RenderSystem.setShaderTexture(0, boss_health_bar);
		RenderSystem.bindTexture(0);
        drawTexture(matrices, 0, 0, 0, 0, 1, 220, 110, 220, 110);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
        
       matrices.pop();
	}
}