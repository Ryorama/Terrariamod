package com.ryorama.terrariamod.items.api;

import com.ryorama.terrariamod.items.Arrow;
import com.ryorama.terrariamod.items.ItemT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Bow extends ItemT {

	public Bow(Settings settings, int damage) {
		super(settings);
		this.damage = damage;
	}

	@Override
	public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		
		ItemStack itemstack = playerIn.getMainHandStack();
	      
	      Inventory inventory = playerIn.getInventory();


	      boolean gotFirstArrowStack = false;
	      int arrowSlotId = 0;

	      for (int i = 0; i <= inventory.size(); i++) {
				if (inventory.getStack(i).getItem() instanceof Arrow && !gotFirstArrowStack) {
					gotFirstArrowStack = true;
					arrowSlotId = i;
				} else if (inventory.getStack(arrowSlotId).getCount() <= 0 && gotFirstArrowStack) {
					gotFirstArrowStack = false;
				}
	      }

	      if (inventory.getStack(arrowSlotId).getCount() > 0 && gotFirstArrowStack) {
			  Arrow arrowItem = (Arrow)inventory.getStack(arrowSlotId).getItem();
			  ArrowEntity entityArrow = new ArrowEntity(worldIn, playerIn);
			  entityArrow.setDamage(this.damage + arrowItem.damage);
			  entityArrow.setOnFire(arrowItem.fireDamage);
			  if (arrowItem.fireDamage) {
				  entityArrow.setOnFireFor(10000);
			  }
			  worldIn.spawnEntity(entityArrow);
			  inventory.getStack(arrowSlotId).decrement(1);
		  }
	      
	      playerIn.getItemCooldownManager().set(this, (int) ((this.useTime - this.useTime * speed) * (30.0 / 60.0)));	      
	      
	      return new TypedActionResult<>(ActionResult.SUCCESS, itemstack);
	   }
}