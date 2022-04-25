package com.ryorama.terrariamod.items.boss_summons;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityKingSlime;
import com.ryorama.terrariamod.items.api.ItemT;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SlimeCrown extends ItemT {

	public SlimeCrown(Settings settings) {
		super(settings);
	}
	
	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {

		world.playSound((PlayerEntity) null, playerEntity.getY(), playerEntity.getY(), playerEntity.getZ(), TAudio.ROAR_0, SoundCategory.PLAYERS, 100, 1);
        		
		EntityKingSlime ks = new EntityKingSlime(world);
		ks.setPosition(playerEntity.getPos().x + 5, playerEntity.getPos().y + 5, playerEntity.getPos().z + 5);
		world.spawnEntity(ks);
		
		playerEntity.getInventory().getMainHandStack().decrement(1);
		
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
