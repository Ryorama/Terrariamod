package com.ryorama.terrariamod.blocks;

import com.ryorama.terrariamod.TerrariaMod;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlocksT {
	
	public static Block GRASS_BLOCK = new Block(FabricBlockSettings.of(Material.SOIL).strength(4.0f));
	public static Block DIRT_BLOCK = new Block(FabricBlockSettings.of(Material.SOIL).strength(4.0f));
	public static Block STONE_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0f));
	
	public static Block COPPER_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(4.0f));
	
	public static void init() {
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "grass_block"), GRASS_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "dirt_block"), DIRT_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "stone_block"), STONE_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier(TerrariaMod.modid, "copper_ore"), COPPER_ORE);

	}
}
