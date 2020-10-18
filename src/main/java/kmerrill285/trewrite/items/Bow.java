package kmerrill285.trewrite.items;


import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.entities.projectiles.EntityArrowT;
import kmerrill285.trewrite.events.ScoreboardEvents;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class Bow extends ItemT {
	public Bow(Properties properties, String name, int damage) {
		super(properties, name);
		this.damage = damage;
		this.ranged = true;
		MODIFIER_TYPE = EnumModifierType.RANGED;
		this.setMaxStack(1);
		offsY = -scale * 0.25;
		
		this.animation = ItemT.BOW_ANIMATION;
		this.scale = 1.25f;
	}
	
	public int getUseDuration(ItemStack t) {
		return 4;
	}
	
	public void onShoot(EntityArrowT arrow) {
		
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
	    	  
	    	  if (bowSlot.stack.item instanceof Bow) {
	    		  velocity += velocity * (ItemModifier.getModifier(bowSlot.stack.modifier).velocity / 100.0);
	    	  }
	    	  kb = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).knockback / 100.0f);
	    	  crit = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).crit);
	    	  speed = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).speed / 100.0f);
	    	  dmg = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).damage / 100.0f);
	      }
		  playerIn.getCooldownTracker().setCooldown(this, (int) ((this.useTime - this.useTime * speed) * (30.0 / 60.0)));
		  
	      
	      Arrow arrow = (Arrow) ItemsT.WOODEN_ARROW;
	      
	      if (!playerIn.abilities.isCreativeMode) {
		      for (int i = 0; i < inventory.main.length; i++) {
		    	  if (inventory.main[i].stack != null) {
		    		  if (inventory.main[i].stack.item instanceof Arrow) {
		    		      arrow = (Arrow) inventory.main[i].stack.item;
		    			  inventory.main[i].decrementStack(1);
		    			  
		    		      worldIn.playSound((PlayerEntity)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		    		      shoot = true;
		    		      ii = i;
		    		      break;
		    		  }
		    	  }
		    	
		      }
		      if (shoot == false) {
		    	  for (int i = 0; i < inventory.hotbar.length; i++) {
			    	  if (inventory.hotbar[i].stack != null) {
			    		  if (inventory.hotbar[i].stack.item instanceof Arrow) {
			    		      arrow = (Arrow) inventory.hotbar[i].stack.item;
			    			  inventory.hotbar[i].decrementStack(1);
			    		      worldIn.playSound((PlayerEntity)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
			    		      shoot = true;
			    		      ii = i;
			    		      break;
			    		  }
			    	  }
			    	
			      }
		      }
	      } else {
		      worldIn.playSound((PlayerEntity)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		      shoot = false;
		      for (int i = 0; i < inventory.main.length; i++) {
		    	  if (inventory.main[i].stack != null) {
		    		  if (inventory.main[i].stack.item instanceof Arrow) {
		    			  arrow = (Arrow) inventory.main[i].stack.item;
		    			  shoot = true;
		    			  break;
		    		  }
		    	  }
		      }
		      
		      if (shoot == false) {
		    	  for (int i = 0; i < inventory.hotbar.length; i++) {
			    	  if (inventory.hotbar[i].stack != null) {
			    		  if (inventory.hotbar[i].stack.item instanceof Arrow) {
			    		      arrow = (Arrow) inventory.hotbar[i].stack.item;
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
//	 	         snowballentity.func_213884_b(new ItemStack(arrow));
//	 	         snowballentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (velocity + arrow.velocity) * (1.0f/6.0f), 0.0F);
				int archer = ScoreboardEvents.getScore(playerIn.getWorldScoreboard(), playerIn, ScoreboardEvents.ARCHERY).getScorePoints();
					double vel = (velocity + arrow.velocity) * (1.0f/6.0f);
	    		 if (archer > 0) {
	    			 vel *= 1.25;
	    		 }
	 	         EntityArrowT arrowentity = new EntityArrowT(worldIn, playerIn);
	 	         arrowentity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float)vel, 0.0F);
	 	         arrowentity.posX += playerIn.getForward().x;
	 	         arrowentity.posY += playerIn.getForward().y;
	 	         arrowentity.posZ += playerIn.getForward().z;
	 	         this.onShoot(arrowentity);

	 	         double damage = (arrow.damage + this.damage + this.damage * dmg) * (1.0 + random.nextDouble() * 0.05f);
	 	         if (archer > 0) { damage *= 1.25; }
	 	         arrowentity.setDamage(damage);
	 	         if (random.nextInt(100) <= this.critChance + crit) {
	 	        	arrowentity.setDamage(this.damage + this.damage * dmg * 2);
	 	         }
	 	         arrowentity.setKnockbackStrength((int) (this.knockback + arrow.knockback) + (int) ((this.knockback + arrow.knockback) * kb));
	 	         arrowentity.arrow = arrow;
	 	         if (arrow.piercing > 0) {
	 	        	arrowentity.func_213872_b((byte)arrow.piercing);
	 	         }
	 	         arrow.onArrowShoot(arrowentity);
	 	         this.onShoot(arrowentity);
	 	         worldIn.addEntity(arrowentity);
	 	         
	 	         //worldIn.addEntity(snowballentity);
	 	      }
	      }
	      

	      return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
	   }
}
