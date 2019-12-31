package ryorama.terrariamod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import ryorama.terrariamod.TForest;

@Mod("terrariamod")
public class Terrariamod
{
	public static Terrariamod instance;
	public static final String modid = "terrariamod";
	private static final Logger LOGGER = LogManager.getLogger(modid);
	public static final WorldType TERRARIAMOD_TYPE = new TerrariaType();
	
	public Terrariamod()
	{
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		LOGGER.info("Setup Method Registered");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event)
	{
		LOGGER.info("Client Setup Method Registered");
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents 
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll
			(
				ItemList.copper_ore = new BlockItem(BlockList.copper_ore, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.copper_ore.getRegistryName()),
				ItemList.stone = new BlockItem(BlockList.stone, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.stone.getRegistryName()),
				ItemList.grass = new BlockItem(BlockList.grass, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.grass.getRegistryName()),
				ItemList.dirt = new BlockItem(BlockList.dirt, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.dirt.getRegistryName()),
				ItemList.log = new BlockItem(BlockList.log, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.log.getRegistryName()),
				ItemList.leaves = new BlockItem(BlockList.leaves, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.leaves.getRegistryName()),
				
				ItemList.copper = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(location("copper"))
			);
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll
			(
				BlockList.copper_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.STONE)).setRegistryName(location("copper_ore")),
				BlockList.grass = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.STONE)).setRegistryName(location("grass")),
				BlockList.dirt = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.STONE)).setRegistryName(location("dirt")),
				BlockList.log = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.STONE)).setRegistryName(location("log")),
				BlockList.leaves = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.STONE)).setRegistryName(location("leaves")),
				BlockList.stone = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.STONE)).setRegistryName(location("stone"))
			);
		}
			
		@SubscribeEvent
		public static void registerBiomes(final RegistryEvent.Register<Biome> event)
		{
			event.getRegistry().registerAll
			(
				TForest.terrforestbiome = new TForestBiome()
			);
			
			TForest.registerBiome();
		}
		
		public static ResourceLocation location(String name)
		{
			return new ResourceLocation(modid, name);
		}
	}
}