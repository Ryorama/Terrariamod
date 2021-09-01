package com.ryorama.terrariamod.mixins;

import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ClearCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.function.Predicate;

@Mixin(ClearCommand.class)
public class ClearCommandMixin {

    @ModifyArg(method = "execute",
            at = @At (value = "INVOKE", target = "Lnet/minecraft/text/TranslatableText;<init>(Ljava/lang/String;[Ljava/lang/Object;)V"),
            index = 1)
    private static Object[] exec(Object[] arr) {
        arr[0] = 999;
        return arr;
    }
}