package com.ryorama.terrariamod.items.weapons.summons;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.items.api.ItemT;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FinchStaff extends ItemT {

	public FinchStaff(Settings settings) {
		super(settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		
		world.playSound(user, user.getBlockPos(), TAudio.SUMMON, SoundCategory.PLAYERS, 10, 1);
		
		return TypedActionResult.success(user.getMainHandStack(), true);
	}
}
