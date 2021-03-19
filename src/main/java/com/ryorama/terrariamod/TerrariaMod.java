package com.ryorama.terrariamod;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.world.TerrariaWorldType;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class TerrariaMod implements ModInitializer {

	public static String modid = "terrariamod";
	
	public static TerrariaWorldType levelGeneratorType;
	
	@Override
	public void onInitialize() {
		BlocksT.init();
		

		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			levelGeneratorType = new TerrariaWorldType();
		}
	}
}
