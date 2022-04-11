package com.ryorama.terrariamod.blocks.api;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

public class LightBlockT extends BlockT {

	public LightBlockT(FabricBlockSettings properties, float hardness, float difficulty, int lightLevel) {
		super(properties.luminance(lightLevel), hardness, difficulty);
	}
}
