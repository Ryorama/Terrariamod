package com.ryorama.terrariamod.items;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;

public class Shortsword extends Item {
	
	public Shortsword(int damage) {
		super(new Properties().group(ItemGroup.COMBAT).maxStackSize(1).maxDamage(damage));
	}

}
