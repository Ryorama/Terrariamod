package com.ryorama.terrariamod.items.api;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class Axe extends ItemT {

	public Axe() {
		super(new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1));
		this.melee = true;
		this.maxStack = 1;
		this.animation = ItemT.PICKAXE_ANIMATION;
		this.scale = 2.0;
	}
}
