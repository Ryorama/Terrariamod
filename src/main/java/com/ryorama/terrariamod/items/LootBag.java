package com.ryorama.terrariamod.items;

public class LootBag extends ItemT {
	
	public LootStack[] stacks;
	
	public LootBag(Properties properties, String name, LootStack...stacks) {
		super(properties, name);
		this.stacks = stacks;
		this.setTooltip("Right click to open");
	}
}