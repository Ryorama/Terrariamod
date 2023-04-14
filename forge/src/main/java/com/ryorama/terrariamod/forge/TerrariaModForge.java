package com.ryorama.terrariamod.forge;

import dev.architectury.platform.forge.EventBuses;
import com.ryorama.terrariamod.TerrariaMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TerrariaMod.MOD_ID)
public class TerrariaModForge {
    public TerrariaModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(TerrariaMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        TerrariaMod.init();
    }
}
