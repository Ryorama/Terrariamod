package com.ryorama.terrariamod.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

/**
 * fixes server-client desync when cheating in items in creative mode
 */
@Mixin (ServerPlayNetworkHandler.class)
public class CreativeDesyncFixin {
	@ModifyConstant (method = "onCreativeInventoryAction", constant = @Constant (intValue = 64))
	private int max(int old) {
		return 9999;
	}
}