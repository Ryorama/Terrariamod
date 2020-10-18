package kmerrill285.trewrite.items.terraria.loot_bags;

import java.util.Random;

import kmerrill285.trewrite.items.ItemT;

public class LootBag extends ItemT {
	
	
	
	public LootStack[] stacks;
	
	public LootBag(Properties properties, String name, LootStack...stacks) {
		super(properties, name);
		this.stacks = stacks;
		this.setTooltip("Right click to open");
	}
}
