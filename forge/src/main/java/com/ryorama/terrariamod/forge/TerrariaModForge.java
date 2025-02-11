package com.ryorama.terrariamod.forge;

import com.ryorama.terrariamod.TerrariaModConfig;
import dev.architectury.platform.forge.EventBuses;
import com.ryorama.terrariamod.TerrariaMod;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;

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
        modBus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(TerrariaModEvents.class);
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> TerrariaModConfig::new);
    }

    public void clientSetup(FMLClientSetupEvent event) {
        if (TerrariaMod.CONFIG.useCustomTitles) {
            Minecraft.getInstance().execute(this::SetRandomTitle);
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

        if (Minecraft.getInstance().getWindow() != null) {
            Minecraft.getInstance().getWindow().setTitle(splashTexts.get(id));
        }
    }
}
