package com.ryorama.terrariamod.items;

import java.util.HashMap;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.items.basic.BasicItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ItemsT {
	
	public static Item COPPER;
	public static Item IRON;
	public static Item GRASS_BLOCK;
	public static Item DIRT_BLOCK;
	public static Item STONE_BLOCK;
	public static Item FALLEN_STAR;
	public static Item WORKBENCH;
	public static Item COPPPER_PICKAXE;
	public static Item COPPER_SWORD;
	public static Item COPPER_AXE;
	
	public static HashMap<String, ItemT> items = new HashMap<String, ItemT>();
	
	public static String getStringForItem(Item item) {
		if (item == null) {
			return "null";
		}
		String str = item.getRegistryName().getNamespace()+":"+item.getRegistryName().toString();
		if (item.getRegistryName().toString().contains(item.getRegistryName().getNamespace())) {
			str = item.getRegistryName().toString();
		}
		return str;
	}
	
	public static Item getItemFromString(String name) {
		
		try {
			return Registry.ITEM.getValue(new ResourceLocation(name)).get();
		}catch (Exception e) {
//			e.printStackTrace();
		}
		
		try {
			return Registry.ITEM.getValue(new ResourceLocation("terrariamod:"+name)).get();
		}catch (Exception e) {
//			e.printStackTrace();
		}
		
		return null;
//		return ItemsT.items.get(name);
	}
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				
				COPPER = new BasicItem(new Properties().group(ItemGroup.MISC)).setRegistryName(new ResourceLocation(TerrariaMod.modid, "copper")),
				IRON = new BasicItem(new Properties().group(ItemGroup.MISC)).setRegistryName(new ResourceLocation(TerrariaMod.modid, "iron")),
				FALLEN_STAR = new BasicItem(new Properties().group(ItemGroup.MISC)).setRegistryName(new ResourceLocation(TerrariaMod.modid, "fallen_star")),
				GRASS_BLOCK = new BlockItem(BlocksT.GRASS_BLOCK, new Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(new ResourceLocation(TerrariaMod.modid, "grass_block")),
				DIRT_BLOCK = new BlockItem(BlocksT.DIRT_BLOCK, new Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(new ResourceLocation(TerrariaMod.modid, "dirt_block")),
				STONE_BLOCK = new BlockItem(BlocksT.STONE_BLOCK, new Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(new ResourceLocation(TerrariaMod.modid, "stone_block")),
				WORKBENCH = new BlockItem(BlocksT.WORKBENCH, new Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(new ResourceLocation(TerrariaMod.modid, "workbench")),
				COPPPER_PICKAXE = new Pickaxe(new Properties(), 1).setRegistryName(new ResourceLocation(TerrariaMod.modid, "copper_pickaxe")),
				COPPER_SWORD = new Shortsword(7).setRegistryName(new ResourceLocation(TerrariaMod.modid, "copper_shortsword")),
				COPPER_AXE = new Axe(new Properties(), 1).setRegistryName(new ResourceLocation(TerrariaMod.modid, "copper_axe"))

				
				
		);
	}
	
}