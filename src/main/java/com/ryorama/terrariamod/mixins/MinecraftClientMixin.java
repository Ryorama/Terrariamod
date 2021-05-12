package com.ryorama.terrariamod.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.ryorama.terrariamod.entity.projectiles.TerrariaInventoryScreen;
import com.ryorama.terrariamod.items.ItemsT;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

	@Inject(at = @At("HEAD"), method = "handleInputEvents")
	public void handleInputEvents(CallbackInfo info) {
		if (MinecraftClient.getInstance().player != null) {
			if (MinecraftClient.getInstance().options.keySneak.wasPressed()) {
				MinecraftClient.getInstance().openScreen(new TerrariaInventoryScreen(MinecraftClient.getInstance().player));
				TerrariaInventoryScreen.TerrariaInventoryScreenHandler.itemList.add(new ItemStack(ItemsT.COPPER_PICKAXE, 1));
			}	
		}	
	}
}
