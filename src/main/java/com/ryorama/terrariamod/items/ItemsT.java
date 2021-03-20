package com.ryorama.terrariamod.items;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemsT {
		
	public static Item COPPER = new Item(new FabricItemSettings().group(ItemGroup.MISC).maxCount(999));
	
	public static void init() {
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "copper"), COPPER);
		
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "grass_block"), new BlockItem(BlocksT.GRASS_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS).maxCount(999)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "dirt_block"), new BlockItem(BlocksT.DIRT_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS).maxCount(999)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "stone_block"), new BlockItem(BlocksT.STONE_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS).maxCount(999)));
		Registry.register(Registry.ITEM, new Identifier(TerrariaMod.modid, "copper_ore"), new BlockItem(BlocksT.COPPER_ORE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS).maxCount(999)));

	}
}
