package com.ryorama.terrariamod.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {
    private static final Map<PlayerEntity, Boolean> HOLDING_JUMP = new HashMap<>();
    private static final Map<PlayerEntity, Boolean> HOLDING_FORWARDS = new HashMap<>();
    private static final Map<PlayerEntity, Boolean> HOLDING_BACKWARDS = new HashMap<>();
    private static final Map<PlayerEntity, Boolean> HOLDING_LEFT = new HashMap<>();
    private static final Map<PlayerEntity, Boolean> HOLDING_RIGHT = new HashMap<>();
    private static final Map<PlayerEntity, Boolean> HOLDING_ATTACK = new HashMap<>();
    private static final Map<PlayerEntity, Boolean> HOLDING_SHIFT = new HashMap<>();

    public static boolean isHoldingJump(PlayerEntity player) {
        return HOLDING_JUMP.containsKey(player) && HOLDING_JUMP.get(player);
    }

    public static boolean isHoldingForwards(PlayerEntity player) {
        return HOLDING_FORWARDS.containsKey(player) && HOLDING_FORWARDS.get(player);
    }

    public static boolean isHoldingBackwards(PlayerEntity player) {
        return HOLDING_BACKWARDS.containsKey(player) && HOLDING_BACKWARDS.get(player);
    }

    public static boolean isHoldingLeft(PlayerEntity player) {
        return HOLDING_LEFT.containsKey(player) && HOLDING_LEFT.get(player);
    }

    public static boolean isHoldingRight(PlayerEntity player) {
        return HOLDING_RIGHT.containsKey(player) && HOLDING_RIGHT.get(player);
    }

    public static boolean isHoldingAttack(PlayerEntity player) {
        return HOLDING_ATTACK.containsKey(player) && HOLDING_ATTACK.get(player);
    }

    public static boolean isHoldingShift(PlayerEntity player) {
        return HOLDING_SHIFT.containsKey(player) && HOLDING_SHIFT.get(player);
    }

    public static void update(PlayerEntity player, boolean jump, boolean attack, boolean shift, boolean forwards, boolean backwards, boolean left, boolean right) {
        HOLDING_JUMP.put(player, jump);
        HOLDING_FORWARDS.put(player, forwards);
        HOLDING_BACKWARDS.put(player, backwards);
        HOLDING_LEFT.put(player, left);
        HOLDING_RIGHT.put(player, right);
        HOLDING_ATTACK.put(player, attack);
        HOLDING_SHIFT.put(player, shift);
    }

    public static void remove(PlayerEntity player) {
        HOLDING_JUMP.remove(player);
        HOLDING_FORWARDS.remove(player);
        HOLDING_BACKWARDS.remove(player);
        HOLDING_LEFT.remove(player);
        HOLDING_RIGHT.remove(player);
        HOLDING_ATTACK.remove(player);
        HOLDING_SHIFT.remove(player);
    }

    public static void clear() {
        HOLDING_JUMP.clear();
        HOLDING_FORWARDS.clear();
        HOLDING_BACKWARDS.clear();
        HOLDING_LEFT.clear();
        HOLDING_RIGHT.clear();
        HOLDING_ATTACK.clear();
        HOLDING_SHIFT.clear();
    }

    public static void onLogout(ServerPlayerEntity playerEntity) {
        remove(playerEntity);
    }

    public static void onChangeDimension(ServerPlayerEntity playerEntity) {
        remove(playerEntity);
    }
}