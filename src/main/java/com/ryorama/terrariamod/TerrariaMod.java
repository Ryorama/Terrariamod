package com.ryorama.terrariamod;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.core.sounds.TMusicTicker;

import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent.NeighborNotifyEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("terrariamod")
public class TerrariaMod
{	
    // Directly reference a log4j logger.
    
	public static String modid = "terrariamod";
	
	private static final Logger LOGGER = LogManager.getLogger();
    
    public static boolean DEBUG = false;

    public TerrariaMod() {
    	
    	try {
    		Field f = Chunk.class.getDeclaredField("field_76634_f");
    	} catch (Exception e) {
    		DEBUG = true;
    		System.out.println("DEBUG!");
    	}
    	
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
   	public static void onBlockUpdate(NeighborNotifyEvent e) {     	
       	if (e.getState().getBlock() == Blocks.STONE || e.getState().getBlock() == Blocks.COBBLESTONE) {
       		e.getWorld().setBlockState(e.getPos(), BlocksT.STONE_BLOCK.getDefaultState(), 0);
       	}
       	if (e.getState().getBlock() == Blocks.ICE) {
       		e.getWorld().setBlockState(e.getPos(), Blocks.WATER.getDefaultState(), 0);
       	}
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    	try {
 			Field musicTicker = ObfuscationReflectionHelper.findField(Minecraft.class, "field_147126_aw");
 			musicTicker.setAccessible(true);
 			musicTicker.set(Minecraft.getInstance(), new TMusicTicker(Minecraft.getInstance()));
 		} catch (Exception e1) {
 			e1.printStackTrace();
 		}
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
       
    }

    private void processIMC(final InterModProcessEvent event)
    {
       
    }
}
