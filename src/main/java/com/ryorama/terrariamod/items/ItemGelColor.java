package com.ryorama.terrariamod.items;

import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class ItemGelColor implements ItemColorProvider {

	@Override
	public int getColor(ItemStack stack, int tintIndex) {
		if (stack.hasNbt()) {
			NbtCompound tag = stack.getNbt();
			if (tag.getString("GelColor") == "") {
				System.out.println(tag.getString("GelColor"));
				return 0x03fc24;
			} else {
				System.out.println("Changing gel color");
				return (int)Long.parseLong(tag.getString("GelColor"), 16);
			}
		} else {
			return 0x03fc24;
		}
	}
}
