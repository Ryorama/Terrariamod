package com.ryorama.terrariamod.items.tools.picks;

import java.util.List;

import com.ryorama.terrariamod.items.api.Pickaxe;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CopperPickaxe extends Pickaxe {

	public CopperPickaxe() {
		super();
		this.pick = 35;
		this.damage = 4;
		this.knockback = 2f;
		this.useTime = 23;
		this.speed = 15;
		this.sellPrice = 100;
		this.range = -1;
	}
}
