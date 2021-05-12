package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.ui.UIRenderer;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper {

	public Identifier health_icon = new Identifier(TerrariaMod.modid, "textures/ui/heart.png");

	public PlayerEntity player;
	
	@Inject(at = @At("TAIL"), method = "<init>()V") 
	public void InGameHud(MinecraftClient client, CallbackInfo info) {
		if (MinecraftClient.getInstance().player != null) {
			player = MinecraftClient.getInstance().player;
		}
	}
	
	@Inject(at = @At("HEAD"), method = "render")
	public void render(MatrixStack matrices, float tickDelta, CallbackInfo info) {
		renderTerrariaHealth();
	}
	
	public void renderTerrariaHealth() {
		HudRenderCallback.EVENT.register((matrixstack, delta) -> {
			float scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
			
			if (player != null) {
				for (int h = 0; h <= player.getHealth(); h++) {
					if (h % 20 == 0) {
						UIRenderer.instance.renderOverlay(health_icon, 1f, 16, 16, scaledWidth + h * 2, 10, -90);
					}
				}
			}
		});
	}
}