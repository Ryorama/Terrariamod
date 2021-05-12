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
}