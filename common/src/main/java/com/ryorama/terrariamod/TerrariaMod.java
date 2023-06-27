package com.ryorama.terrariamod;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.entities.EntitiesT;
import com.ryorama.terrariamod.items.ItemsT;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class TerrariaMod {
    public static final String MOD_ID = "terrariamod";
    public static TerrariaModConfig CONFIG = new TerrariaModConfig();

    public static void init() {
        AutoConfig.register(TerrariaModConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(TerrariaModConfig.class).getConfig();

        BuffsT.init();
        BlocksT.init();
        EntitiesT.init();
        ItemsT.init();
    }
}
