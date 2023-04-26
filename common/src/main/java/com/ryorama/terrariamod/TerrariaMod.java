package com.ryorama.terrariamod;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.stats.StatsT;
import dev.architectury.injectables.annotations.ExpectPlatform;

public class TerrariaMod {
    public static final String MOD_ID = "terrariamod";

    public static void init() {
        BlocksT.init();
        ItemsT.init();
    }
}
