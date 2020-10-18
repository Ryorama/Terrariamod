package kmerrill285.trewrite.items.terraria.loot_bags;

import kmerrill285.trewrite.items.ItemsT;

public class Present extends LootBag {
	
	public static LootStack[] loot = {
			new LootStack(ItemsT.DIRT_BLOCK, 1, 999)
	};
	public Present(Properties properties, String name) {
		super(properties, name, loot);
		
	}

}
