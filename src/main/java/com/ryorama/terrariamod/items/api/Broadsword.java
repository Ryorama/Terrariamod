package com.ryorama.terrariamod.items.api;

import com.ryorama.terrariamod.items.ItemT;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;

public class Broadsword extends ItemT {

	public Broadsword() {
		super(new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));
		this.melee = true;
		this.maxStack = 1;
		
		this.animation = ItemT.BROADSWORD_ANIMATION;
		this.scale = 1.25f;
	}
}
