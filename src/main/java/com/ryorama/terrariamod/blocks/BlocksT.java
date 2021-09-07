package com.ryorama.terrariamod.blocks;

import com.ryorama.terrariamod.TerrariaMod;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlocksT {
		
	public static BlockT GRASS_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT CORRUPTED_GRASS_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT JUNGLE_GRASS = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT MUD = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT SAND = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT SNOW = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT DIRT_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15).setPick(true);
	public static BlockT STONE_BLOCK = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT EBONSTONE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 30).setPick(true);
	public static BlockT ASH = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT MARBLE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 30).setPick(true);
	public static BlockT GRANITE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 30).setPick(true);
	public static BlockT MUSHROOM_GRASS = new BlockT(FabricBlockSettings.of(Material.SOIL).luminance(15), 15, 15).setPick(true);
	public static BlockT WOOD = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT WOODEN_BEAM = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static PlantT LIFE_CRYSTAL_BLOCK = (PlantT) new PlantT(FabricBlockSettings.of(Material.STONE).luminance(10), 15, 15).setPick(true);
	public static PlantT VINE = (PlantT) new PlantT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true).setAxe(true);

	public static BlockT COPPER_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT IRON_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static BlockT GOLD_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15).setPick(true);
	public static LightBlockT HELLSTONE_ORE = (LightBlockT) new LightBlockT(FabricBlockSettings.of(Material.STONE), 50, 50, 10).setPick(true);
	
	public static PlantT MUSHROOM = (PlantT) new PlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f).setPick(true);
	public static LightPlantT JUNGLE_SPORES = (LightPlantT) new LightPlantT(FabricBlockSettings.of(Material.PLANT), 0.1f, 0.1f, 10).setPick(true);

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

	public static void init() {
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "grass_block"), GRASS_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "corrupted_grass"), CORRUPTED_GRASS_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "jungle_grass"), JUNGLE_GRASS);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "dirt_block"), DIRT_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "stone_block"), STONE_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "ebonstone"), EBONSTONE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "mud"), MUD);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "sand"), SAND);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "snow"), SNOW);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "ash"), ASH);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "marble"), MARBLE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "granite"), GRANITE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "mushroom_grass"), MUSHROOM_GRASS);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "wood"), WOOD);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "wooden_beam"), WOODEN_BEAM);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "life_crystal_block"), LIFE_CRYSTAL_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "vine"), VINE);

		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "empty_bottle"), EMPTY_BOTTLE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "mushroom"), MUSHROOM);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "jungle_spores"), JUNGLE_SPORES);
		
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "copper_ore"), COPPER_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "iron_ore"), IRON_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "gold_ore"), GOLD_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "hellstone_ore"), HELLSTONE_ORE);

		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "ruby_ore"), RUBY_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "sapphire_ore"), SAPPHIRE_ORE);
		
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "forest_stump"), FOREST_STUMP);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "forest_stem"), FOREST_STEM);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "forest_top"), FOREST_TOP);

		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "giant_glowing_mushroom_stem"), GIANT_GLOWING_MUSHROOM_STEM);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "giant_glowing_mushroom_top"), GIANT_GLOWING_MUSHROOM_TOP);

		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "honey_block"), HONEY_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.MODID, "crispy_honey_block"), CRISPY_HONEY_BLOCK);
	}
}
