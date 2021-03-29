package com.ryorama.terrariamod.items.api;

import com.ryorama.terrariamod.items.ItemT;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class Pickaxe extends ItemT {

	public Pickaxe() {
		super(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1));
		this.melee = true;
		this.maxStack = 1;
		this.animation = ItemT.PICKAXE_ANIMATION;
		this.scale = 2.0;
	}
}
