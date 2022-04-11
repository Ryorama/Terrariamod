package com.ryorama.terrariamod.blocks.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.items.api.ItemT;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockT extends Block {
	
	public int buy, sell;
	public float difficulty = 1;
	public boolean pick, axe, hammer;
	public boolean material;
	public boolean harvest = true;
	public boolean consumable;
	
	public int potionSickness;

	public Random random = new Random();

	public List<ItemStack> drops = new ArrayList<>();
	
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
	
	public BlockT setPick(boolean pick) {
		this.pick = pick;
		return this;
	}
	
	public BlockT setAxe(boolean axe) {
		this.axe = axe;
		return this;
	}
	
	public List<ItemStack> getDrops() {
		return this.drops;
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (state.getBlock() == BlocksT.FOREST_POT) {
			switch(random.nextInt(3)) {
				case 0:
					this.drops.add(new ItemStack(ItemsT.LESSER_HEALING_POTION, random.nextInt(3, 8)));
					break;
				case 1:
					this.drops.add(new ItemStack(Items.TORCH, random.nextInt(6, 13)));
					break;
				case 2:
					this.drops.add(new ItemStack(ItemsT.WOODEN_ARROW, random.nextInt(7, 11)));
					break;
			}
		}

		if (!drops.isEmpty()) {
			for (int i = 0; i < drops.size() - 1; i++) {
				ItemEntity itemDrop = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), drops.get(i));
				itemDrop.setPos(pos.getX(), pos.getY(), pos.getZ());

				world.spawnEntity(itemDrop);
			}
		}
	}
}
