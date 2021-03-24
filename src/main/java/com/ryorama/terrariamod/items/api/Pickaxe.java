package com.ryorama.terrariamod.items.api;

import com.ryorama.terrariamod.items.ItemT;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class Pickaxe extends ItemT {

	public Pickaxe(float pick) {
		super(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1));
		this.pick = pick;
		this.maxStack = 1;
	}
}
