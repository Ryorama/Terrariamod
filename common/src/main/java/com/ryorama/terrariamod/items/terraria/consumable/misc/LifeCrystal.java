package com.ryorama.terrariamod.items.terraria.consumable.misc;

import com.ryorama.terrariamod.client.TAudio;
import com.ryorama.terrariamod.items.impl.ItemT;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LifeCrystal extends ItemT {

	public LifeCrystal(Properties settings) {
		super(settings);
		this.isConsumable(true);
	}
	
	@Override
    public InteractionResultHolder<ItemStack> use(Level world, Player playerEntity, InteractionHand hand) {
		if (playerEntity.getAttributeValue(Attributes.MAX_HEALTH) < 400) {
			playerEntity.playSound(TAudio.HEALTH_CRYSTAL, 1, 1);
			playerEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(playerEntity.getAttributeValue(Attributes.MAX_HEALTH) + 20);
			playerEntity.setHealth((float)(playerEntity.getAttributeValue(Attributes.MAX_HEALTH) + 20));
			if (!playerEntity.isCreative()) {
				playerEntity.getItemInHand(hand).shrink(1);
			}
			
			return InteractionResultHolder.success(playerEntity.getItemInHand(hand));
		}
		return InteractionResultHolder.success(playerEntity.getItemInHand(hand));
	}
}
