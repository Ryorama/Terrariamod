package com.ryorama.terrariamod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class Broadsword extends Item {

	public Broadsword(Properties properties, int damage) {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1).maxDamage(damage));	
	}
}