package com.ryorama.terrariamod.forge.network;

import com.google.common.base.CaseFormat;
import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.forge.mixin.BooleanValueInvoker;
import com.ryorama.terrariamod.forge.mixin.IntValueInvoker;
import com.ryorama.terrariamod.items.ItemsT;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.RegistryObject;


import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class GameRulesT {
    private static final Map<String, BooleanValue> BOOLEAN_VALUES = new HashMap<>();
    private static final Map<String, IntegerValue> INTEGER_VALUES = new HashMap<>();

    public static final BooleanValue
            ROYAL_GEL_EQ = booleanValue("royal_gel_eq");

    public static final IntegerValue
            MANA = integerValue("mana", 0),
            MAX_MANA = integerValue("max_mana", 20),

            IRON_SKIN = integerValue("iron_skin", 0),
            POTION_SICKNESS = integerValue("potion_sickness", 0),
            HAPPY = integerValue("happy", 0),
            COZY_FIRE = integerValue("cozy_fire", 0),
            REGENERATION = integerValue("regeneration", 0),
            POISONED = integerValue("poisoned", 0),
            BLEEDING = integerValue("bleeding", 0),
            WATER_CANDLE = integerValue("water_candle", 0);



    private static String createName(RegistryObject<? extends Item> item, String name) {
        return String.format("%s.%s.%s",
                TerrariaMod.MOD_ID,
                CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, item.getId().getPath()),
                name
        );
    }

    private static BooleanValue booleanValue(String name) {
        BooleanValue result = new BooleanValue();
        GameRules.Type<GameRules.BooleanRule> type = BooleanValueInvoker.invokeCreate(true, (server, value) -> {
            result.update(value.get());
            NetworkHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new BooleanGameRuleChangedPacket(name, value.get()));
        });
        result.key = GameRules.register(name, GameRules.Category.PLAYER, type);
        BOOLEAN_VALUES.put(name, result);
        return result;
    }

    private static IntegerValue integerValue(String name, int defaultValue) {
        return integerValue(name, defaultValue, (server, value) -> { });
    }

    private static IntegerValue integerValue(String name, int defaultValue, BiConsumer<MinecraftServer, GameRules.IntRule> onChanged) {
        IntegerValue result = new IntegerValue();
        result.update(defaultValue);
        GameRules.Type<GameRules.IntRule> type = IntValueInvoker.invokeCreate(defaultValue, (server, value) -> {
            result.update(value.get());
            NetworkHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new IntegerGameRuleChangedPacket(name, value.get()));
            onChanged.accept(server, value);
        });
        result.key = GameRules.register(name, GameRules.Category.PLAYER, type);

        INTEGER_VALUES.put(name, result);
        return result;
    }

    public static void updateValue(String key, boolean value) {
        BOOLEAN_VALUES.get(key).update(value);
    }

    public static void updateValue(String key, int value) {
        INTEGER_VALUES.get(key).update(value);
    }

    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayerEntity player) {
            BOOLEAN_VALUES.forEach((key, value) -> NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new BooleanGameRuleChangedPacket(key, value.get())));
            INTEGER_VALUES.forEach((key, value) -> NetworkHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new IntegerGameRuleChangedPacket(key, value.get())));
        }
    }

    public static void onServerStarted(ServerStartedEvent event) {
        BOOLEAN_VALUES.values().forEach(value -> value.update(event.getServer()));
        INTEGER_VALUES.values().forEach(value -> value.update(event.getServer()));
    }

    public static class BooleanValue implements Supplier<Boolean> {

        private Boolean value = true;
        private GameRules.Key<GameRules.BooleanRule> key;

        public Boolean get() {
            return value;
        }

        private void update(MinecraftServer server) {
            update(server.getGameRules().getBoolean(key));
        }

        private void update(boolean value) {
            this.value = value;
        }
    }

    public static class IntegerValue implements Supplier<Integer> {

        private Integer value;
        private GameRules.Key<GameRules.IntRule> key;

        public Integer get() {
            return value;
        }

        private void update(MinecraftServer server) {
            update(server.getGameRules().getInt(key));
        }

        private void update(int value) {
            this.value = value;
        }
    }
}
