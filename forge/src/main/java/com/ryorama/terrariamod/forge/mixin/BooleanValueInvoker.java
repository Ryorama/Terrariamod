package com.ryorama.terrariamod.forge.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiConsumer;

@Mixin(GameRules.BooleanRule.class)
public interface BooleanValueInvoker {

    @Invoker("create")
    static GameRules.Type<GameRules.BooleanRule> invokeCreate(boolean defaultValue, BiConsumer<MinecraftServer, GameRules.BooleanRule> onChanged) {
        throw new AssertionError();
    }
}