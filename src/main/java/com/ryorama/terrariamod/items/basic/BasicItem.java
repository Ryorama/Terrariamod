package com.ryorama.terrariamod.items.basic;

import com.ryorama.terrariamod.util.Conversions;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;

public class BasicItem extends Item {

	/***
	 * 
	 * @param properties
	 */
	public BasicItem(Properties properties) {
		super(new Properties().group(ItemGroup.MATERIALS));
	}
}