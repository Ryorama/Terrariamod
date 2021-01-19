package com.ryorama.terrariamod.blocks;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class BlocksT {

	public static HashMap<String, Block> BLOCKS = new HashMap<String, Block>();
	
	public static BlockT AIR_BLOCK;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
				
				
				AIR_BLOCK = new BlockAirT().setLocation("air_block")
				
		);
	}
	
}
