package com.ryorama.terrariamod.blocks;

import com.ryorama.terrariamod.TerrariaMod;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlocksT {
	
	public static Block GRASS_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15);
	public static Block DIRT_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15);
	public static Block STONE_BLOCK = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15);
	public static Block ASH = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15);

	public static Block COPPER_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15);
	
	public static TreeSegment FOREST_STUMP = new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15);
	public static TreeSegment FOREST_STEM = new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15);
	public static TreeSegment FOREST_TOP = new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15);
	
	public static void init() {
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "grass_block"), GRASS_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "dirt_block"), DIRT_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "stone_block"), STONE_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "copper_ore"), COPPER_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "ash"), ASH);

		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "forest_stump"), FOREST_STUMP);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "forest_stem"), FOREST_STEM);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "forest_top"), FOREST_TOP);
	}
}
