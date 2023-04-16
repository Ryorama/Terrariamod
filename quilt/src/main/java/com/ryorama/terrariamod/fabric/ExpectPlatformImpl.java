package com.ryorama.terrariamod.fabric;

import org.quiltmc.loader.api.QuiltLoader;

import java.nio.file.Path;

public class ExpectPlatformImpl {
    public static Path getConfigDirectory() {
        return QuiltLoader.getConfigDir();
    }
}
