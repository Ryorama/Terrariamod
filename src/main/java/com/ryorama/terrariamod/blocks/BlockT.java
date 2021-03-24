package com.ryorama.terrariamod.blocks;

import java.util.ArrayList;

import com.ryorama.terrariamod.items.ItemT;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

public class BlockT extends Block {
	
	public int buy, sell;
	public float difficulty = 1;
	public boolean pick, axe, hammer;
	public boolean material;
	public boolean harvest = true;
	public boolean consumable;
	
	public int potionSickness;
	
	public int health, mana;
	
	public String tooltip = "";
		
	public String name;
	public String shape = "";
	
	protected ArrayList<String> allowed = new ArrayList<String>();

	public BlockT(FabricBlockSettings properties, float hardness, float difficulty) {
		super(properties.hardness(hardness * 0.03f));
		this.difficulty = difficulty;
	}
	
	public BlockT addAllowed(String... block) {
		for (String b : block)
				this.allowed.add(b);
		return this;
	}
	
	public float getMiningSpeed(ItemT item) {
		if (pick && item.pick >= difficulty) {
			return item.pick / difficulty;
		}
		
		if (axe && item.axe >= difficulty) {
			return item.axe / difficulty;
		}
		
		if (hammer && item.hammer >= difficulty) {
			return item.hammer / difficulty;
		}
		
		return -1;
	}	
}
