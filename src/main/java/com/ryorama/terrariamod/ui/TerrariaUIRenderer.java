package com.ryorama.terrariamod.ui;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.core.client.UIRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TerrariaUIRenderer {
	
	public static Identifier health_icon = new Identifier(TerrariaMod.MODID, "textures/ui/heart.png");
	public static Identifier shield = new Identifier(TerrariaMod.MODID, "textures/ui/shield.png");
	public static Identifier mana = new Identifier(TerrariaMod.MODID, "textures/ui/mana.png");

	//Buffs
	public static Identifier iron_skin = new Identifier(TerrariaMod.MODID, "textures/ui/buffs/ironskin.png");

	//DeBuffs
	public static Identifier potion_sickness = new Identifier(TerrariaMod.MODID, "textures/ui/buffs/potion_sickness.png");


	public static ClientPlayerEntity player;
	
	public static void renderTerrariaHealth() {
		HudRenderCallback.EVENT.register((matrixstack, delta) -> {
			
			float scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
						
			if (MinecraftClient.getInstance().player != null) {
				player = MinecraftClient.getInstance().player;
			}
						
			if (player != null) {
				for (int h = 0; h < player.getHealth() + 20; h++) {		
					TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
					textRenderer.draw(matrixstack, "Life: " + (int)player.getHealth() + "/" + (int)player.getMaxHealth(), scaledWidth - 113, 2, 0xffffff);
					
					if (h % 20 == 0) {
						if (h < 200) {
							UIRenderer.instance.renderOverlay(health_icon, 50, 8, 8, scaledWidth - 125 + h / 2, 10, -90);
						} else if (h > 200) {
							UIRenderer.instance.renderOverlay(health_icon, 50, 8, 8, scaledWidth - 125 + h / 2 - 110, 20, -90);
						}
					}
				}
			}
		});
	}
	
	public static void renderTerrariaDefense() {
		HudRenderCallback.EVENT.register((matrixstack, delta) -> {
			
			float scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
						
			if (MinecraftClient.getInstance().player != null) {
				player = MinecraftClient.getInstance().player;
			}
						
			if (player != null) {
				UIRenderer.instance.renderOverlay(shield, 50, 16, 16, scaledWidth - 100 / 2 - 70, 220, -90);
				TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
				
				if ((int)player.getAttributeValue(EntityAttributes.GENERIC_ARMOR) >= 10) {
					textRenderer.draw(matrixstack, Integer.toString((int)player.getAttributeValue(EntityAttributes.GENERIC_ARMOR)), scaledWidth - 100 / 2 - 66, 224, 0xffffff);
				} else {
					textRenderer.draw(matrixstack, Integer.toString((int)player.getAttributeValue(EntityAttributes.GENERIC_ARMOR)), scaledWidth - 100 / 2 - 64, 224, 0xffffff);
				}
			}
		});
	}
	
	public static void renderTerrariaMana() {
			HudRenderCallback.EVENT.register((matrixstack, delta) -> {

				float scaledWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();

				if (MinecraftClient.getInstance().player != null) {
					player = MinecraftClient.getInstance().player;
				}

				if (player != null) {
					MinecraftClient.getInstance().textRenderer.draw(matrixstack, "Mana", (int) (scaledWidth - 25), 3, 0xffffff);

					for (int i = 0; i <= player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.MANA)) - 20; i++) {
						if (i % 20 == 0) {
							UIRenderer.instance.renderOverlay(mana, 50, 10, 10, scaledWidth - 18, 10 + i / 2, -90);
						}
					}
				}
		});
	}

	public static void renderTerrariaEffects() {
		HudRenderCallback.EVENT.register((matrixstack, delta) -> {

			if (MinecraftClient.getInstance().player != null) {
				player = MinecraftClient.getInstance().player;
			}

			int i3 = 17;
			int j3 = 27;
			int effectCounter = 0;

			if (player != null) {
				if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS)) > 0) {
					UIRenderer.instance.renderOverlay(potion_sickness, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
					effectCounter++;
				}

				if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.IRON_SKIN)) > 0) {
					UIRenderer.instance.renderOverlay(iron_skin, 50, 16, 16, i3 + effectCounter * 20f, j3, -90);
					effectCounter++;
				}
			}
		});
	}
}
