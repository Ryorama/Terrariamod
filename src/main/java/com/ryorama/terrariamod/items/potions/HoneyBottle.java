package com.ryorama.terrariamod.items.potions;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.items.api.ItemT;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HoneyBottle extends ItemT {

	public HoneyBottle(Settings settings) {
		super(settings.maxCount(30));
	}
	
	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		if (world.isClient()) {
			if (MinecraftClient.getInstance().player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS)) <= 0) {
				playerEntity.heal(80);
				playerEntity.getInventory().getMainHandStack().decrement(1);
				MinecraftClient.getInstance().player.getStatHandler().setStat(playerEntity, Stats.CUSTOM.getOrCreateStat(TerrariaMod.POTION_SICKNESS), 1200);

				return TypedActionResult.success(playerEntity.getStackInHand(hand));
			}
		}
		
		return TypedActionResult.fail(playerEntity.getStackInHand(hand));
	}
}
