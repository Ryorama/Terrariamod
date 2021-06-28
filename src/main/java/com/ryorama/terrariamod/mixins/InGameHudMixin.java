package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;

import com.ryorama.terrariamod.TerrariaMod;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper {

	public Identifier health_icon = new Identifier(TerrariaMod.MODID, "textures/ui/heart.png");

	public PlayerEntity player;
}