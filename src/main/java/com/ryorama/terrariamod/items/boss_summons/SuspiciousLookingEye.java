package com.ryorama.terrariamod.items.boss_summons;

import com.ryorama.terrariamod.TAudio;
import com.ryorama.terrariamod.entity.EntitiesT;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityEyeOfCthulhu;
import com.ryorama.terrariamod.entity.hostile.bosses.EntityKingSlime;
import com.ryorama.terrariamod.items.ItemT;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SuspiciousLookingEye extends ItemT {

	public SuspiciousLookingEye(Settings settings) {
		super(settings);
	}
	
	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        
        boolean day = world.isDay();

		if (!day) {
			playerEntity.playSound(TAudio.ROAR_0, 1, 1);
	        		
			EntityEyeOfCthulhu ks = EntitiesT.EOC.create(world);
			ks.setPosition(playerEntity.getPos().x + 5, playerEntity.getPos().y + 5, playerEntity.getPos().z + 5);
			world.spawnEntity(ks);
			
			playerEntity.getInventory().getMainHandStack().decrement(1);
			
	        return TypedActionResult.success(playerEntity.getStackInHand(hand));
		}
		return new TypedActionResult(ActionResult.FAIL, playerEntity.getInventory().getMainHandStack());
    }
}
