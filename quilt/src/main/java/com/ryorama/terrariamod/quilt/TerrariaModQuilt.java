package com.ryorama.terrariamod.quilt;

import com.ryorama.terrariamod.TerrariaMod;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class TerrariaModQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        TerrariaMod.init();
    }
}
