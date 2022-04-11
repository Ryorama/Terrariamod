package com.ryorama.terrariamod.items.potions;

import com.ryorama.terrariamod.TerrariaMod;
import com.ryorama.terrariamod.items.api.ItemT;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class IronSkinPotion extends ItemT {

	public IronSkinPotion(Settings settings) {
		super(settings.maxCount(30));
	}
	
	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		if (world.isClient()) {
			playerEntity.getInventory().getMainHandStack().decrement(1);
			MinecraftClient.getInstance().player.getStatHandler().setStat(playerEntity, Stats.CUSTOM.getOrCreateStat(TerrariaMod.IRON_SKIN), 6000);
		}

		return TypedActionResult.success(playerEntity.getStackInHand(hand));
	}
}
