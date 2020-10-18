package kmerrill285.trewrite.items;


import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.projectiles.EntityThrowingT;
import kmerrill285.trewrite.events.WorldEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ThrowableItem extends ItemT {
	public double recovery = 1.0f;

	public ThrowableItem(Properties properties, String name, int damage) {
		super(properties, name);
		this.damage = damage;
		this.throwing = true;

		this.animation = ItemT.THROWING_ANIMATION;
	}
	
	public int getUseDuration(ItemStack t) {
		return 4;
	}
	
	 public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		
	      ItemStack itemstack = playerIn.getHeldItem(handIn);
	      
	      InventoryTerraria inventory = null;
	      if (!worldIn.isRemote) {
	    	  inventory = WorldEvents.getOrLoadInventory(playerIn);
	      } else 
	      {
	    	  inventory = ContainerTerrariaInventory.inventory;
	      }
	      
	      int ii = 0;
	      boolean shoot = false;
	      
	      InventorySlot bowSlot = inventory.hotbar[inventory.hotbarSelected];
	      
	      
		  playerIn.getCooldownTracker().setCooldown(this, (int) (this.useTime / 3));
		  
		  if (this.velocity <= 0) this.velocity = 9;
		  
	      if (inventory.hotbar[inventory.hotbarSelected].stack != null) {
	    	  if (inventory.hotbar[inventory.hotbarSelected].stack.item instanceof ThrowableItem) {
	    		  ThrowableItem thrown = (ThrowableItem) inventory.hotbar[inventory.hotbarSelected].stack.item;
	    	      if (!playerIn.abilities.isCreativeMode) {
	    	    	  inventory.hotbar[inventory.hotbarSelected].decrementStack(1);
	    	      }
	    	      
	    	      
	        	  if (!worldIn.isRemote) {
	        		  EntityThrowingT thrownentity = EntitiesT.THROWING.create(playerIn.world);
	        		  thrownentity.setPosition(playerIn.posX, playerIn.posY+playerIn.getEyeHeight(playerIn.getPose()), playerIn.posZ);
	     	          thrownentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (velocity) * (1.0f/6.0f), 0.0F);
	     	          thrownentity.damage = damage;
	     	          thrownentity.knockback = knockback;
	     	          thrownentity.func_213884_b(new ItemStack(this));
	     	          thrownentity.arrow = this;
	     	          thrownentity.thrower = playerIn;
	     	          setExplosiveAttribs(thrownentity);
	     	          
	     	          
	     	          worldIn.addEntity(thrownentity);
	     	      }
	    	  }
	      }
	      
	      
	      

	      return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	   }
	 
	 protected void setExplosiveAttribs(EntityThrowingT thrownentity) {
		 
	}

	public void throwingTick(EntityThrowingT entity) {
		 
	 }
	 
	 public void onImpact(RayTraceResult result, EntityThrowingT t) {
		 if (result.getType() == RayTraceResult.Type.ENTITY) {
	         Entity entity = ((EntityRayTraceResult)result).getEntity();
	         
	         entity.attackEntityFrom(DamageSource.causeThrownDamage(t, t.getThrower()), this.damage);
	         if (entity instanceof LivingEntity) {
	        	 LivingEntity l = (LivingEntity)entity;
	        	 
	        	 double d1 = l.posX - t.posX;

                 double d0;
                 for(d0 = l.posZ - t.posZ; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D) {
                    d1 = (Math.random() - Math.random()) * 0.01D;
                 }
	        	 
	        	 l.knockBack(t, knockback, d1, d0);
	         }
	      }

	      if (!t.world.isRemote) {
	         t.world.setEntityState(t, (byte)3);
	         t.remove();
	      }
	 }
}
