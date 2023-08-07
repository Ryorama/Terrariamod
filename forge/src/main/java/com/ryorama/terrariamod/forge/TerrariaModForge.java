package com.ryorama.terrariamod.forge;

import com.ryorama.terrariamod.forge.network.NetworkHandler;
import com.ryorama.terrariamod.stats.StatsT;
import com.ryorama.terrariamod.utils.WorldDataT;
import dev.architectury.platform.forge.EventBuses;
import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.client.MinecraftClient;
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
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(TerrariaMod.MOD_ID)
public class TerrariaModForge {

    public static Random rand = new Random();

    public TerrariaModForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(TerrariaMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        TerrariaMod.init();

        modBus.addListener(this::commonSetup);
        modBus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(TerrariaModEvents.class);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(NetworkHandler::register);
    }

    public void clientSetup(FMLClientSetupEvent event) {
        if (TerrariaMod.CONFIG.useCustomTitles) {
            MinecraftClient.getInstance().execute(this::SetRandomTitle);
        }
    }

    public void SetRandomTitle() {
        InputStream stream = TerrariaMod.class.getClassLoader().getResourceAsStream("assets/terrariamod/splash_texts.txt");
        Scanner scanner = new Scanner(stream);
        List<String> splashTexts = new ArrayList<>();

        while (scanner.hasNextLine()) {
            splashTexts.add(scanner.nextLine());
        }

        int id = rand.nextInt(splashTexts.size());

        if (MinecraftClient.getInstance().getWindow() != null) {
            MinecraftClient.getInstance().getWindow().setTitle(splashTexts.get(id));
        }
    }
}
