package com.ryorama.terrariamod.forge;

import com.ryorama.terrariamod.forge.network.NetworkHandler;
import com.ryorama.terrariamod.stats.StatsT;
import com.ryorama.terrariamod.utils.WorldDataT;
import dev.architectury.platform.forge.EventBuses;
import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.client.util.telemetry.WorldLoadedEvent;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.tick.Tick;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.io.IOException;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(TerrariaMod.MOD_ID)
public class TerrariaModForge {

    public TerrariaModForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(TerrariaMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        TerrariaMod.init();

        modBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(TerrariaModEvents.class);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(NetworkHandler::register);
    }
}
