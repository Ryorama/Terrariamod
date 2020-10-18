package kmerrill285.trewrite.items;


import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.entities.projectiles.EntityBullet;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class Gun extends ItemT {
	public Gun(Properties properties, String name, int damage) {
		super(properties, name);
		this.damage = damage;
		this.ranged = true;
		MODIFIER_TYPE = EnumModifierType.RANGED;
		this.setMaxStack(1);
		rotX = 45;
		offsY = -scale * -0.05;
		offsZ = -scale * 0.20;
		
		this.animation = ItemT.GUN_ANIMATION;
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
	      float velocity = this.velocity;
	      float kb = 1.0f;
	      float speed = 1.0f;
	      float dmg = 1.0f;
	      float crit = 1.0f;
	      
	      if (bowSlot == null) {
	    	  return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	      }
	      
	      if (bowSlot.stack == null) {
	    	  return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	      }
	      if (!(bowSlot.stack.item == this)) {
	    	  return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	      }
	      if (ItemModifier.getModifier(bowSlot.stack.modifier) == null) {
	    	  bowSlot.stack.reforge(bowSlot.stack.item);
	    	  return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	      }
	      if (bowSlot.stack != null) {
	    	  
	    	  if (bowSlot.stack.item instanceof Gun) {
	    		  velocity += velocity * (ItemModifier.getModifier(bowSlot.stack.modifier).velocity / 100.0);
	    	  }
	    	  kb = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).knockback / 100.0f);
	    	  crit = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).crit);
	    	  speed = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).speed / 100.0f);
	    	  dmg = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).damage / 100.0f);
	      }
		  playerIn.getCooldownTracker().setCooldown(this, (int) ((this.useTime - this.useTime * speed) * (30.0 / 60.0)));
		  
	      
	      Bullet bullet = (Bullet) ItemsT.MUSKET_BALL;
	      
	      if (!playerIn.abilities.isCreativeMode) {
		      for (int i = 0; i < inventory.main.length; i++) {
		    	  if (inventory.main[i].stack != null) {
		    		  if (inventory.main[i].stack.item instanceof Bullet) {
		    		      bullet = (Bullet) inventory.main[i].stack.item;
		    			  inventory.main[i].decrementStack(1);
		    			  
		    		      worldIn.playSound((PlayerEntity)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 2F, 2);
		    		      shoot = true;
		    		      ii = i;
		    		      break;
		    		  }
		    	  }
		    	
		      }
		      if (shoot == false) {
		    	  for (int i = 0; i < inventory.hotbar.length; i++) {
			    	  if (inventory.hotbar[i].stack != null) {
			    		  if (inventory.hotbar[i].stack.item instanceof Bullet) {
			    		      bullet = (Bullet) inventory.hotbar[i].stack.item;
			    			  inventory.hotbar[i].decrementStack(1);
			    		      worldIn.playSound((PlayerEntity)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 2F, 2);
			    		      shoot = true;
			    		      ii = i;
			    		      break;
			    		  }
			    	  }
			    	
			      }
		      }
	      } else {
		      worldIn.playSound((PlayerEntity)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST, SoundCategory.PLAYERS, 2F, 2);
		      shoot = false;
		      for (int i = 0; i < inventory.main.length; i++) {
		    	  if (inventory.main[i].stack != null) {
		    		  if (inventory.main[i].stack.item instanceof Bullet) {
		    			  bullet = (Bullet) inventory.main[i].stack.item;
		    			  shoot = true;
		    			  break;
		    		  }
		    	  }
		      }
		      
		      if (shoot == false) {
		    	  for (int i = 0; i < inventory.hotbar.length; i++) {
			    	  if (inventory.hotbar[i].stack != null) {
			    		  if (inventory.hotbar[i].stack.item instanceof Bullet) {
			    		      bullet = (Bullet) inventory.hotbar[i].stack.item;
			    		      break;
			    		  }
			    	  }
			    	
			      }
		      }
		      shoot = true;
	      }
	      
	      if (shoot) {
	    	  if (!worldIn.isRemote) {
//	 	         SnowballEntity snowballentity = new SnowballEntity(worldIn, playerIn);
//	 	         snowballentity.func_213884_b(new ItemStack(bullet));
//	 	         snowballentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (velocity + bullet.velocity) * (1.0f/6.0f), 0.0F);
				int archer = 0;
					double vel = (velocity + bullet.velocity) * (1.0f/6.0f);
	    		 if (archer > 0) {
	    			 vel *= 1.25;
	    		 }
	 	         EntityBullet bulletentity = new EntityBullet(worldIn, playerIn);
	 	         bulletentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float)vel, 0.0F);
	 	         bulletentity.posX += playerIn.getForward().x;
	 	         bulletentity.posY += playerIn.getForward().y;
	 	         bulletentity.posZ += playerIn.getForward().z;

	 	         double damage = (bullet.damage + this.damage + this.damage * dmg) * (1.0 + random.nextDouble() * 0.05f);
	 	         if (archer > 0) { damage *= 1.25; }
	 	         bulletentity.setDamage(damage);
	 	         if (random.nextInt(100) <= this.critChance + crit) {
	 	        	bulletentity.setDamage(this.damage + this.damage * dmg * 2);
	 	         }
	 	         bulletentity.setKnockback((int) (this.knockback + bullet.knockback) + (int) ((this.knockback + bullet.knockback) * kb));
	 	         bulletentity.bullet = bullet;
	 	         if (bullet.piercing > 0) {
	 	        	bulletentity.piercing = bullet.piercing;
	 	         }
	 	         bulletentity.func_213884_b(new ItemStack(bullet));

	 	         bullet.onBulletShoot(bulletentity);
	 	         worldIn.addEntity(bulletentity);
	 	         
	 	         //worldIn.addEntity(snowballentity);
	 	      }
	      }
	      

	      return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	   }
}
