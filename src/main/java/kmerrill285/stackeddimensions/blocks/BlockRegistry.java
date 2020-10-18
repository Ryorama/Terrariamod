package kmerrill285.stackeddimensions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry {
	
	
	public static Block DIMENSION_BLOCK;
	
	
	 
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().register(DIMENSION_BLOCK = new DimensionBlock(Properties.create(Material.EARTH).sound(SoundType.STONE)).setRegistryName("trewrite:dimension_block"));
	}
	
	
}
