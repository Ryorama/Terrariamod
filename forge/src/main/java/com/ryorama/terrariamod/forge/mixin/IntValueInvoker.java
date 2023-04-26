package com.ryorama.terrariamod.forge.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiConsumer;

@Mixin(GameRules.IntRule.class)
public interface IntValueInvoker {

    @Invoker("create")
    static GameRules.Type<GameRules.IntRule> invokeCreate(int defaultValue, BiConsumer<MinecraftServer, GameRules.IntRule> onChanged) {
        throw new AssertionError();
    }
}