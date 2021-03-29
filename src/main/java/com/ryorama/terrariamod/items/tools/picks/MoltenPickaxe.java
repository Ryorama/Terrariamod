package com.ryorama.terrariamod.items.tools.picks;

import com.ryorama.terrariamod.blocks.BlocksT;
import com.ryorama.terrariamod.items.ItemsT;
import com.ryorama.terrariamod.items.api.Pickaxe;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MoltenPickaxe extends Pickaxe {

	public MoltenPickaxe() {
		super();
		this.pick = 100;
		this.damage = 12;
		this.knockback = 2;
		this.useTime = 23;
		this.speed = 18;
		this.sellPrice = 5400;
	}
}
