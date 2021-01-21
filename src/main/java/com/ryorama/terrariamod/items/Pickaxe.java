package com.ryorama.terrariamod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class Pickaxe extends Item {

	public Pickaxe(Properties properties, int level) {
		super(properties.group(ItemGroup.TOOLS).addToolType(ToolType.PICKAXE, level));
	}

}
