package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.client.TMusicTicker;

import net.minecraft.client.gui.screen.TitleScreen;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
	
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		//TMusicTicker.getTrack(TAudio.TITLE_SCREEN);
	}
}
