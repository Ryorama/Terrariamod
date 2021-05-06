package com.ryorama.terrariamod.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.TorchBlock;
import net.minecraft.item.ItemStack;

public class LightBlockT extends BlockT {

	public LightBlockT(FabricBlockSettings properties, float hardness, float difficulty, int lightLevel) {
		super(properties.luminance(lightLevel), hardness, difficulty);
	}
}
