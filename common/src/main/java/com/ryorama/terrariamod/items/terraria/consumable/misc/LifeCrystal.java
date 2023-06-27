package com.ryorama.terrariamod.items.terraria.consumable.misc;

import com.ryorama.terrariamod.buffs.BuffsT;
import com.ryorama.terrariamod.client.TAudio;
import com.ryorama.terrariamod.items.impl.ItemT;
import com.ryorama.terrariamod.buffs.CustomBuffAccessor;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LifeCrystal extends ItemT {

	public LifeCrystal(Settings settings) {
		super(settings);
	}
	
	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {

		//Test code, To be removed
		if (playerEntity instanceof CustomBuffAccessor) {
			((CustomBuffAccessor)playerEntity).AddBuff(1000, BuffsT.REGENERATION);
		}
		//

		if (playerEntity.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH) < 400) {
			playerEntity.playSound(TAudio.HEALTH_CRYSTAL, 1, 1);

			playerEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(playerEntity.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH) + 20);
			playerEntity.setHealth((float)(playerEntity.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH) + 20));
	        
			playerEntity.getInventory().getMainHandStack().decrement(1);
			
			return TypedActionResult.success(playerEntity.getStackInHand(hand));	
		}
		return TypedActionResult.success(playerEntity.getStackInHand(hand));
	}
}
