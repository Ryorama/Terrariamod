package com.ryorama.terrariamod.items.potions;

import com.ryorama.terrariamod.items.ItemT;

import net.minecraft.client.texture.StatusEffectSpriteManager;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LesserHealingPotion extends ItemT {

	public LesserHealingPotion(Settings settings) {
		super(settings.maxCount(30));
	}
	
	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
		if (!playerEntity.getItemCooldownManager().isCoolingDown(this)) {
			
			playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 4));
			playerEntity.getInventory().getMainHandStack().decrement(1);
			playerEntity.getItemCooldownManager().set(this, 600);
			
			return TypedActionResult.success(playerEntity.getStackInHand(hand));
		}
		
		return TypedActionResult.fail(playerEntity.getStackInHand(hand));
	}
}
