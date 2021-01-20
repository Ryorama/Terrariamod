package com.ryorama.terrariamod.items;

import java.util.Random;

public class LootStack {


	public ItemT loot;
	public int min;
	public int max;
	public ItemT secondLoot;
	public int secondValue;
	
	public LootStack(ItemT loot, int min, int max) {
		this.loot = loot;
		this.min = min;
		this.max = max;
	}
	
	public LootStack(ItemT loot, int min, int max, ItemT secondLoot, int secondValue) {
		this.loot = loot;
		this.min = min;
		this.max = max;
		this.secondLoot = secondLoot;
		this.secondValue = secondValue;
	}
	
	public int getAmount(Random rand) {
		return (rand.nextInt(max - min) + min);
	}
	
}
