package com.ryorama.terrariamod.items;

import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Style;
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
	    	if (inventory.getStack(i).getItem() instanceof Arrow) {
	    		Arrow arrowItem = (Arrow)inventory.getStack(i).getItem();
	    		ArrowEntity entityArrow = new ArrowEntity(worldIn, playerIn);
	    		entityArrow.setDamage(this.damage + arrowItem.damage);
    			entityArrow.setOnFire(arrowItem.fireDamage);
    			if (arrowItem.fireDamage) {
    				entityArrow.setOnFireFor(10000);
    			}
	    		worldIn.spawnEntity(entityArrow);
	    		inventory.getStack(i).decrement(1);
		      }  
	      }
	      
	      playerIn.getItemCooldownManager().set(this, (int) ((this.useTime - this.useTime * speed) * (30.0 / 60.0)));	      
	      
	      return new TypedActionResult<>(ActionResult.SUCCESS, itemstack);
	   }
}