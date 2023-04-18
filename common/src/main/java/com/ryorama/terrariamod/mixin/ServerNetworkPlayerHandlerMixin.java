package com.ryorama.terrariamod.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerNetworkPlayerHandlerMixin {
    @ModifyConstant(method = "onCreativeInventoryAction", constant = @Constant(intValue = 64))
    private int max(int old) {
        return 999;
    }
}
