package com.ryorama.terrariamod.stats;

import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

import java.util.function.Supplier;

public class StatsT {
    public static final ResourceLocation MANA = new ResourceLocation(TerrariaMod.MOD_ID, "mana");
    public static final ResourceLocation MAX_MANA = new ResourceLocation(TerrariaMod.MOD_ID, "max_mana");
    public static final ResourceLocation ROYAL_GEL_EQ = new ResourceLocation(TerrariaMod.MOD_ID, "royal_gel_eq");

    //Buffs & Debuffs
    public static final ResourceLocation IRON_SKIN = new ResourceLocation(TerrariaMod.MOD_ID, "iron_skin");
    public static final ResourceLocation POTION_SICKNESS = new ResourceLocation(TerrariaMod.MOD_ID, "potion_sickness");
    public static final ResourceLocation HAPPY = new ResourceLocation(TerrariaMod.MOD_ID, "happy");
    public static final ResourceLocation COZY_FIRE = new ResourceLocation(TerrariaMod.MOD_ID, "cozy_fire");
    public static final ResourceLocation REGENERATION = new ResourceLocation(TerrariaMod.MOD_ID, "regeneration");
    public static final ResourceLocation POISONED = new ResourceLocation(TerrariaMod.MOD_ID, "poisoned");

    public static final ResourceLocation BLEEDING = new ResourceLocation(TerrariaMod.MOD_ID, "bleeding");
    public static final ResourceLocation WATER_CANDLE = new ResourceLocation(TerrariaMod.MOD_ID, "water_candle");
    
    public static void init() {
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "mana", MANA);
        Stats.CUSTOM.get(MANA, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "max_mana", MAX_MANA);
        Stats.CUSTOM.get(MAX_MANA, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "royal_gel_eq", ROYAL_GEL_EQ);
        Stats.CUSTOM.get(ROYAL_GEL_EQ, StatFormatter.DEFAULT);

        //Buffs
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "iron_skin", IRON_SKIN);
        Stats.CUSTOM.get(IRON_SKIN, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "happy", HAPPY);
        Stats.CUSTOM.get(HAPPY, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "cozy_fire", COZY_FIRE);
        Stats.CUSTOM.get(COZY_FIRE, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "regeneration", REGENERATION);
        Stats.CUSTOM.get(REGENERATION, StatFormatter.DEFAULT);

        //DeBuffs
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "potion_sickness", POTION_SICKNESS);
        Stats.CUSTOM.get(POTION_SICKNESS, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "poisoned", POISONED);
        Stats.CUSTOM.get(POISONED, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "bleeding", BLEEDING);
        Stats.CUSTOM.get(BLEEDING, StatFormatter.DEFAULT);
        Registry.register(BuiltInRegistries.CUSTOM_STAT, "water_candle", WATER_CANDLE);
        Stats.CUSTOM.get(WATER_CANDLE, StatFormatter.DEFAULT);
    }
}
