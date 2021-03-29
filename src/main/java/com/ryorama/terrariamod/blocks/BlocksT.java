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
		
	public static BlockT GRASS_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15, new ItemStack(Registry.ITEM.get(new Identifier(TerrariaMod.modid, "grass_block")), 1));
	public static BlockT DIRT_BLOCK = new BlockT(FabricBlockSettings.of(Material.SOIL), 15, 15, new ItemStack(Registry.ITEM.get(new Identifier(TerrariaMod.modid, "dirt_block")), 1));
	public static BlockT STONE_BLOCK = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15, new ItemStack(Registry.ITEM.get(new Identifier(TerrariaMod.modid, "stone_block")), 1));
	public static BlockT ASH = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15, new ItemStack(Registry.ITEM.get(new Identifier(TerrariaMod.modid, "ash")), 1));

	public static BlockT COPPER_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15, new ItemStack(Registry.ITEM.get(new Identifier(TerrariaMod.modid, "copper_ore")), 1));
	public static BlockT IRON_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15, new ItemStack(Registry.ITEM.get(new Identifier(TerrariaMod.modid, "copper_ore")), 1));
	public static BlockT GOLD_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 15, 15, new ItemStack(Registry.ITEM.get(new Identifier(TerrariaMod.modid, "copper_ore")), 1));
	public static BlockT HELLSTONE_ORE = new BlockT(FabricBlockSettings.of(Material.STONE), 50, 50, new ItemStack(Registry.ITEM.get(new Identifier(TerrariaMod.modid, "copper_ore")), 1));
	
	public static TreeSegment FOREST_STUMP = new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15);
	public static TreeSegment FOREST_STEM = new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15);
	public static TreeSegment FOREST_TOP = new TreeSegment(FabricBlockSettings.of(Material.WOOD), 15, 15);
	
	public static void init() {
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "grass_block"), GRASS_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "dirt_block"), DIRT_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "stone_block"), STONE_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "ash"), ASH);

		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "copper_ore"), COPPER_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "iron_ore"), IRON_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "gold_ore"), GOLD_ORE);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "hellstone_ore"), HELLSTONE_ORE);

		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "forest_stump"), FOREST_STUMP);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "forest_stem"), FOREST_STEM);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "forest_top"), FOREST_TOP);
	}
}
