package com.ryorama.terrariamod.items.api;

import com.ryorama.terrariamod.items.ItemT;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class Axe extends ItemT {

	public Axe(float axe) {
		super(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1));
		this.axe = axe;
		this.maxStack = 1;
	}
}
