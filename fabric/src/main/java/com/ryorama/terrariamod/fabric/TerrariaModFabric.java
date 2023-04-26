package com.ryorama.terrariamod.fabric;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.stats.StatsT;
import net.fabricmc.api.ModInitializer;

public class TerrariaModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TerrariaMod.init();
        StatsT.init();
    }
}
