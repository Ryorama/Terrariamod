package com.ryorama.terrariamod.blocks;

import java.util.Random;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.items.ItemsT;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlocksT {
		
	public static BlockT GRASS_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15);
	public static BlockT CORRUPTED_GRASS_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15);
	public static BlockT JUNGLE_GRASS = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15);
	public static BlockT MUD = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15);
	public static BlockT SAND = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15);
	public static BlockT SNOW = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15);
	public static BlockT DIRT_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15);
	public static BlockT STONE_BLOCK = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15);
	public static BlockT EBONSTONE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 30);
	public static BlockT ASH = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15);

	public static BlockT COPPER_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15);
	public static BlockT IRON_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15);
	public static BlockT GOLD_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15);
	public static LightBlockT HELLSTONE_ORE = new LightBlockT(FabricBlockSettings.of(Material.STONE), 50, 50, 10);
	
	public static PlantT MUSHROOM = new PlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f);
	public static LightPlantT JUNGLE_SPORES = new LightPlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f, 10);

	public static PlantT EMPTY_BOTTLE = new PlantT(FabricBlockSettings.of(Material.GLASS), 0.5f, 0.5f);
	
	public static BlockT RUBY_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15);
	public static BlockT SAPPHIRE_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15);
	
	public static TreeSegment FOREST_STUMP = new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15);
	public static TreeSegment FOREST_STEM = new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15);
	public static TreeSegment FOREST_TOP = new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15);
	
	public static void init() {
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "grass_block"), GRASS_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "corrupted_grass_block"), CORRUPTED_GRASS_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "jungle_grass"), JUNGLE_GRASS);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "dirt_block"), DIRT_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "stone_block"), STONE_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "ebonstone"), EBONSTONE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "mud"), MUD);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "snow"), SNOW);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "ash"), ASH);

		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "empty_bottle"), EMPTY_BOTTLE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "mushroom"), MUSHROOM);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "jungle_spores"), JUNGLE_SPORES);
		
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "copper_ore"), COPPER_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "iron_ore"), IRON_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "gold_ore"), GOLD_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "hellstone_ore"), HELLSTONE_ORE);

		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "ruby_ore"), RUBY_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "sapphire_ore"), SAPPHIRE_ORE);
		
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "forest_stump"), FOREST_STUMP);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "forest_stem"), FOREST_STEM);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "forest_top"), FOREST_TOP);
	}
}
