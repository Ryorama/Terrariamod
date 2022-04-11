package com.ryorama.terrariamod.items.api;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class Shortsword extends ItemT {

	public Shortsword() {
		super(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));
		this.melee = true;
		this.maxStack = 1;
		
		this.animation = ItemT.SHORTSWORD_ANIMATION;
		this.scale = 1.25f;
	}
}