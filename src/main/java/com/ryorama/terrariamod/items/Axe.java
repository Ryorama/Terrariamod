package com.ryorama.terrariamod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;

public class Axe extends Item {

	public Axe(Properties properties, int level) {
		super(properties.group(ItemGroup.TOOLS).addToolType(ToolType.AXE, level));
	}

}
