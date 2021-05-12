package com.ryorama.terrariamod.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
	      	    
	      for (int i = 0; i <= inventory.size(); i++) {
	    	if (inventory.getStack(i).getItem() == Items.ARROW) {
	    		ArrowItem item = (ArrowItem)inventory.getStack(i).getItem();
	    		PersistentProjectileEntity entityArrow = item.createArrow(worldIn, inventory.getStack(i), playerIn);
	    		entityArrow.setProperties(playerIn, playerIn.pitch, playerIn.yaw, 0.0F, 3.0F, 1.0F);
	    		entityArrow.setDamage(this.damage);
	    		worldIn.spawnEntity(entityArrow);
		      }  
	      }
	      
	      playerIn.getItemCooldownManager().set(this, (int) ((this.useTime - this.useTime * speed) * (30.0 / 60.0)));	      
	      
	      return new TypedActionResult<>(ActionResult.SUCCESS, itemstack);
	   }
}