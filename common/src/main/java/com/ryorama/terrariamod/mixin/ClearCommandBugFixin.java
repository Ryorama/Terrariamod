package com.ryorama.terrariamod.mixin;

import net.minecraft.server.command.ClearCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin (ClearCommand.class)
public class ClearCommandBugFixin {
	@ModifyArg (method = {"method_51936", "method_51937", "method_51938", "method_51939"},
			at = @At (value = "INVOKE", target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/text/MutableText;"),
			index = 1)
	private static Object[] exec(Object[] arr) {
		arr[0] = 9999;
		return arr;
	}
}
