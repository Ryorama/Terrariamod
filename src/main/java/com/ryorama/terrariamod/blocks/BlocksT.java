package com.ryorama.terrariamod.blocks;

import java.util.HashMap;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.core.sounds.SoundsT;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class BlocksT {

	public static HashMap<String, Block> BLOCKS = new HashMap<String, Block>();
	
	public static Block AIR_BLOCK;
	public static Block GRASS_BLOCK;
	public static Block DIRT_BLOCK;
	public static Block STONE_BLOCK;
	
	public static float mul = 1.5f;
	public static float GROUND_HARDNESS = 30.0f * mul, STONE_HARDNESS = 45.0f * mul, ORE_HARDNESS = 50.0f * mul, DUNGEON_HARDNESS = 60.0f * mul;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				
				
				AIR_BLOCK = new BlockAirT().setRegistryName(new ResourceLocation(TerrariaMod.modid, "air")),
				GRASS_BLOCK = new GrassBlock(Properties.create(Material.EARTH).sound(SoundsT.DIRT)).setRegistryName(new ResourceLocation(TerrariaMod.modid, "grass")),
				DIRT_BLOCK = new DirtBlock(Properties.create(Material.EARTH).sound(SoundsT.DIRT)).setRegistryName(new ResourceLocation(TerrariaMod.modid, "dirt")),
				STONE_BLOCK = new BasicBlock(Properties.create(Material.EARTH).sound(SoundsT.STONE)).setRegistryName(new ResourceLocation(TerrariaMod.modid, "stone"))
				
		);
	}
	
}
