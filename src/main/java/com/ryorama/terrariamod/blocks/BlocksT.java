package com.ryorama.terrariamod.blocks;

import com.ryorama.terrariamod.TerrariaMod;

import com.ryorama.terrariamod.blocks.api.*;
import com.ryorama.terrariamod.blocks.boss_summons.Larva;
import com.ryorama.terrariamod.blocks.chests.*;
import com.ryorama.terrariamod.blocks.furniture.WaterCandle;
import com.ryorama.terrariamod.blocks.obsticals.Spike;
import com.ryorama.terrariamod.entity.EntitiesT;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BlocksT {
		
	public static BlockT GRASS_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT CORRUPTED_GRASS_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT CRIMSON_GRASS_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT JUNGLE_GRASS = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT MUD = new BlockT(FabricBlockSettings.copyOf(Blocks.STONE), 15, 15).setPick(true);
	public static BlockT SAND = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT SANDSTONE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT SNOW = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT ICE = new BlockT(FabricBlockSettings.of(Material.ICE), 15, 15).setPick(true);
	public static BlockT DIRT_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT STONE_BLOCK = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT BLUE_BRICK = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT GREEN_BRICK = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT PURPLE_BRICK = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static Spike SPIKE = (Spike) new Spike(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static WaterCandle WATER_CANDLE = (WaterCandle) new WaterCandle(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);

	public static BlockT EBONSTONE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 30).setPick(true);
	public static BlockT CRIMSTONE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 30).setPick(true);
	public static BlockT ASH = new BlockT(FabricBlockSettings.copyOf(Blocks.STONE), 15, 15).setPick(true);
	public static BlockT MARBLE = new BlockT(FabricBlockSettings.copyOf(Blocks.STONE), 15, 30).setPick(true);
	public static BlockT GRANITE = new BlockT(FabricBlockSettings.copyOf(Blocks.STONE), 15, 30).setPick(true);
	public static BlockT SUNPLATE_BLOCK = new BlockT(FabricBlockSettings.copyOf(Blocks.STONE), 15, 30).setPick(true);
	public static BlockT CLOUD = new BlockT(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL), 15, 30).setPick(true);
	public static BlockT RAIN_CLOUD = new BlockT(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL), 15, 30).setPick(true);

	public static BlockT MUSHROOM_GRASS = new BlockT(FabricBlockSettings.copyOf(Blocks.STONE).luminance(15), 15, 15).setPick(true);
	public static BlockT WOOD = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT WOODEN_BEAM = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static PlantT LIFE_CRYSTAL_BLOCK = (PlantT) new PlantT(FabricBlockSettings.copyOf(Blocks.STONE).luminance(10), 15, 15).setPick(true);
	public static PlantT VINE = (PlantT) new PlantT(FabricBlockSettings.of(Material.STONE), 0.1f, 0.1f).setPick(true).setAxe(true);
	public static Pot FOREST_POT = new Pot(FabricBlockSettings.of(Material.GLASS).sounds(BlockSoundGroup.GLASS), 0.1f, 0.1f);

	public static BlockT COPPER_ORE = new BlockT(FabricBlockSettings.copyOf(Blocks.COPPER_ORE), 15, 15).setPick(true);
	public static BlockT IRON_ORE = new BlockT(FabricBlockSettings.copyOf(Blocks.IRON_ORE), 15, 15).setPick(true);
	public static BlockT GOLD_ORE = new BlockT(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE), 15, 15).setPick(true);
	public static BlockT DEMONITE_ORE = new BlockT(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE), 15, 15).setPick(true);
	public static BlockT CRIMTANE_ORE = new BlockT(FabricBlockSettings.copyOf(Blocks.DIAMOND_ORE), 15, 15).setPick(true);
	public static LightBlockT HELLSTONE_ORE = (LightBlockT) new LightBlockT(FabricBlockSettings.copyOf(Blocks.ANCIENT_DEBRIS), 50, 50, 10).setPick(true);

	public static PlantT MUSHROOM = (PlantT) new PlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
	public static LightPlantT GLOWING_MUSHROOM = (LightPlantT) new LightPlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f, 15).setPick(true);
	public static LightPlantT JUNGLE_SPORES = (LightPlantT) new LightPlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f, 10).setPick(true);

	public static PlantT BLINKROOT = (PlantT) new PlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
	public static PlantT DAYBLOOM = (PlantT) new PlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
	public static PlantT DEATHWEED = (PlantT) new PlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
	public static PlantT FIREBLOSSOM = (PlantT) new PlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
	public static PlantT MOONGLOW = (PlantT) new PlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
	public static PlantT SHIVERTHORN = (PlantT) new PlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
	public static PlantT WATERLEAF = (PlantT) new PlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);

	public static PlantT EMPTY_BOTTLE = (PlantT) new PlantT(FabricBlockSettings.of(Material.GLASS), 0.5f, 0.5f).setPick(true);
	
	public static BlockT RUBY_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT SAPPHIRE_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	
	public static TreeSegment FOREST_STUMP = (TreeSegment) new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15).setAxe(true);
	public static TreeSegment FOREST_STEM = (TreeSegment) new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15).setAxe(true);
	public static TreeSegment FOREST_TOP = (TreeSegment) new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15).setAxe(true);

	public static TreeSegment GIANT_GLOWING_MUSHROOM_STEM = (TreeSegment) new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15).setAxe(true);
	public static TreeSegment GIANT_GLOWING_MUSHROOM_TOP = (TreeSegment) new TreeSegment(FabricBlockSettings.of(Material.WOOD).luminance(20), 15, 15).setAxe(true);

	public static BlockT HONEY_BLOCK = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT CRISPY_HONEY_BLOCK = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);

	public static GravestoneT TOMBSTONE = (GravestoneT) new GravestoneT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static GravestoneT GOLD_TOMBSTONE = (GravestoneT) new GravestoneT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);

	public static Block WOOD_CHEST = new WoodChest(FabricBlockSettings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.WOOD_CHEST);
	public static Block GOLD_CHEST = new GoldChest(FabricBlockSettings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.GOLD_CHEST);
	public static Block WATER_CHEST = new WaterChest(FabricBlockSettings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.WATER_CHEST);
	public static Block IVY_CHEST = new IvyChest(FabricBlockSettings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.IVY_CHEST);
	public static Block SKYWARE_CHEST = new SkywareChest(FabricBlockSettings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.SKYWARE_CHEST);
	public static Block SANDSTONE_CHEST = new SandstoneChest(FabricBlockSettings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.SANDSTONE_CHEST);
	public static Block FROZEN_CHEST = new FrozenChest(FabricBlockSettings.of(Material.WOOD).strength(3.0f, 6.0f).requiresTool(), () -> EntitiesT.FROZEN_CHEST);

	public static Larva LARVA = (Larva) new Larva(FabricBlockSettings.of(Material.SPONGE), 15, 15).setPick(true).setAxe(true);

	public static void init() {
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "grass_block"), GRASS_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "corrupted_grass"), CORRUPTED_GRASS_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "crimson_grass"), CRIMSON_GRASS_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "jungle_grass"), JUNGLE_GRASS);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "dirt_block"), DIRT_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "stone_block"), STONE_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "spike"), SPIKE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "water_candle"), WATER_CANDLE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "blue_brick"), BLUE_BRICK);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "green_brick"), GREEN_BRICK);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "purple_brick"), PURPLE_BRICK);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "ebonstone"), EBONSTONE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "crimstone"), CRIMSTONE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "mud"), MUD);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "sand"), SAND);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "sandstone"), SANDSTONE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "snow"), SNOW);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "ice"), ICE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "ash"), ASH);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "marble"), MARBLE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "granite"), GRANITE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "mushroom_grass"), MUSHROOM_GRASS);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "wood"), WOOD);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "wooden_beam"), WOODEN_BEAM);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "life_crystal_block"), LIFE_CRYSTAL_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "vine"), VINE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "sunplate_block"), SUNPLATE_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "cloud"), CLOUD);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "rain_cloud"), RAIN_CLOUD);

		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "empty_bottle"), EMPTY_BOTTLE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "mushroom"), MUSHROOM);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "glowing_mushroom"), GLOWING_MUSHROOM);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "jungle_spores"), JUNGLE_SPORES);

		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "blinkroot"), BLINKROOT);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "daybloom"), DAYBLOOM);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "deathweed"), DEATHWEED);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "fireblossom"), FIREBLOSSOM);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "moonglow"), MOONGLOW);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "shiverthorn"), SHIVERTHORN);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "waterleaf"), WATERLEAF);

		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "copper_ore"), COPPER_ORE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "iron_ore"), IRON_ORE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "gold_ore"), GOLD_ORE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "demonite_ore"), DEMONITE_ORE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "crimtane_ore"), CRIMTANE_ORE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "hellstone_ore"), HELLSTONE_ORE);

		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "ruby_ore"), RUBY_ORE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "sapphire_ore"), SAPPHIRE_ORE);
		
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "forest_stump"), FOREST_STUMP);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "forest_stem"), FOREST_STEM);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "forest_top"), FOREST_TOP);

		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "giant_glowing_mushroom_stem"), GIANT_GLOWING_MUSHROOM_STEM);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "giant_glowing_mushroom_top"), GIANT_GLOWING_MUSHROOM_TOP);

		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "honey_block"), HONEY_BLOCK);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "crispy_honey_block"), CRISPY_HONEY_BLOCK);

		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "tombstone"), TOMBSTONE);
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "gold_tombstone"), GOLD_TOMBSTONE);
		
		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "larva"), LARVA);

		Registry.register(Registries.BLOCK, new Identifier(TerrariaMod.MODID, "pot"), FOREST_POT);

		Registry.register(Registries.BLOCK , new Identifier(TerrariaMod.MODID, "wood_chest"), WOOD_CHEST);
		Registry.register(Registries.BLOCK , new Identifier(TerrariaMod.MODID, "gold_chest"), GOLD_CHEST);
		Registry.register(Registries.BLOCK , new Identifier(TerrariaMod.MODID, "water_chest"), WATER_CHEST);
		Registry.register(Registries.BLOCK , new Identifier(TerrariaMod.MODID, "ivy_chest"), IVY_CHEST);
		Registry.register(Registries.BLOCK , new Identifier(TerrariaMod.MODID, "skyware_chest"), SKYWARE_CHEST);
		Registry.register(Registries.BLOCK , new Identifier(TerrariaMod.MODID, "sandstone_chest"), SANDSTONE_CHEST);
		Registry.register(Registries.BLOCK , new Identifier(TerrariaMod.MODID, "frozen_chest"), FROZEN_CHEST);

	}
}
