package com.ryorama.terrariamod.stats;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class StatsT {
    public static final Identifier MANA = new Identifier(TerrariaMod.MOD_ID, "mana");
    public static final Identifier MAX_MANA = new Identifier(TerrariaMod.MOD_ID, "max_mana");
    public static final Identifier ROYAL_GEL_EQ = new Identifier(TerrariaMod.MOD_ID, "royal_gel_eq");

    //Buffs & Debuffs
    public static final Identifier IRON_SKIN = new Identifier(TerrariaMod.MOD_ID, "iron_skin");
    public static final Identifier POTION_SICKNESS = new Identifier(TerrariaMod.MOD_ID, "potion_sickness");
    public static final Identifier HAPPY = new Identifier(TerrariaMod.MOD_ID, "happy");
    public static final Identifier COZY_FIRE = new Identifier(TerrariaMod.MOD_ID, "cozy_fire");
    public static final Identifier REGENERATION = new Identifier(TerrariaMod.MOD_ID, "regeneration");
    public static final Identifier POISONED = new Identifier(TerrariaMod.MOD_ID, "poisoned");

    public static final Identifier BLEEDING = new Identifier(TerrariaMod.MOD_ID, "bleeding");
    public static final Identifier WATER_CANDLE = new Identifier(TerrariaMod.MOD_ID, "water_candle");
    
    public static void init() {
        Registry.register(Registries.CUSTOM_STAT, "mana", MANA);
        Stats.CUSTOM.getOrCreateStat(MANA, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "max_mana", MAX_MANA);
        Stats.CUSTOM.getOrCreateStat(MAX_MANA, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "royal_gel_eq", ROYAL_GEL_EQ);
        Stats.CUSTOM.getOrCreateStat(ROYAL_GEL_EQ, StatFormatter.DEFAULT);

        //Buffs
        Registry.register(Registries.CUSTOM_STAT, "iron_skin", IRON_SKIN);
        Stats.CUSTOM.getOrCreateStat(IRON_SKIN, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "happy", HAPPY);
        Stats.CUSTOM.getOrCreateStat(HAPPY, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "cozy_fire", COZY_FIRE);
        Stats.CUSTOM.getOrCreateStat(COZY_FIRE, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "regeneration", REGENERATION);
        Stats.CUSTOM.getOrCreateStat(REGENERATION, StatFormatter.DEFAULT);

        //DeBuffs
        Registry.register(Registries.CUSTOM_STAT, "potion_sickness", POTION_SICKNESS);
        Stats.CUSTOM.getOrCreateStat(POTION_SICKNESS, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "poisoned", POISONED);
        Stats.CUSTOM.getOrCreateStat(POISONED, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "bleeding", BLEEDING);
        Stats.CUSTOM.getOrCreateStat(BLEEDING, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "water_candle", WATER_CANDLE);
        Stats.CUSTOM.getOrCreateStat(WATER_CANDLE, StatFormatter.DEFAULT);

        /*
        Stats.CUSTOM.getOrCreateStat(MANA.get(), StatFormatter.DEFAULT);
        Stats.CUSTOM.getOrCreateStat(MAX_MANA.get(), StatFormatter.DEFAULT);
        Stats.CUSTOM.getOrCreateStat(ROYAL_GEL_EQ.get(), StatFormatter.DEFAULT);

        //Buffs
        Stats.CUSTOM.getOrCreateStat(IRON_SKIN.get(), StatFormatter.DEFAULT);
        Stats.CUSTOM.getOrCreateStat(HAPPY.get(), StatFormatter.DEFAULT);
        Stats.CUSTOM.getOrCreateStat(COZY_FIRE.get(), StatFormatter.DEFAULT);
        Stats.CUSTOM.getOrCreateStat(REGENERATION.get(), StatFormatter.DEFAULT);

        //DeBuffs
        Stats.CUSTOM.getOrCreateStat(POTION_SICKNESS.get(), StatFormatter.DEFAULT);
        Stats.CUSTOM.getOrCreateStat(POISONED.get(), StatFormatter.DEFAULT);
        Stats.CUSTOM.getOrCreateStat(BLEEDING.get(), StatFormatter.DEFAULT);
        Stats.CUSTOM.getOrCreateStat(WATER_CANDLE.get(), StatFormatter.DEFAULT);
         */
    }
}
