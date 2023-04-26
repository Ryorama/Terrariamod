package com.ryorama.terrariamod.forge;

import com.ryorama.terrariamod.forge.network.NetworkHandler;
import com.ryorama.terrariamod.stats.StatsT;
import dev.architectury.platform.forge.EventBuses;
import com.ryorama.terrariamod.TerrariaMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TerrariaMod.MOD_ID)
public class TerrariaModForge {
    public TerrariaModForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(TerrariaMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        TerrariaMod.init();

        modBus.addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(NetworkHandler::register);
    }
}
