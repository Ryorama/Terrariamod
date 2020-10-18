package kmerrill285.trewrite.items.terraria.broadswords;

import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.projectiles.EntityTekhairaProjectile;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.Broadsword;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Tekhaira extends Broadsword {
	private int projectileDamage = 80;
	
	public Tekhaira() {
		super();
		this.damage = 56;
		this.knockback = 6.5f;
		this.useTime = 18;
		this.velocity = 8f;
		this.sellPrice = 62700;
		this.buyPrice = Conversions.sellToBuy(sellPrice);
		this.setLocation("tekhaira");
		this.scale = 2.0f;
	}

	
	
	
	public void shoot(EntityTekhairaProjectile projectile) {
		
	}

	
	public void tick(EntityTekhairaProjectile projectile) {
		if (projectile.world.isRemote) return;
		projectile.setNoGravity(true);
		projectile.noClip = false;
	}

	
	public void hit(EntityTekhairaProjectile projectile, LivingEntity entity) {
		
	}

	
	public void onLeftClick(Entity entity, BlockPos pos, PlayerEntity player, World worldIn, Hand handIn) {
		super.onLeftClick(entity, pos, player, worldIn, handIn);
		if (player.getCooldownTracker().getCooldown(this, 0) <= 0)
		this.onItemRightClick(worldIn, player, handIn);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	    ItemStack itemstack = playerIn.getHeldItem(handIn);

		if (playerIn.getCooldownTracker().getCooldown(this, 0) > 0) {
			return new ActionResult<>(ActionResultType.FAIL, itemstack);
		}
		
		  playerIn.getCooldownTracker().setCooldown(this, 1 * 20);

		

		
	      
	      InventoryTerraria inventory = null;
	      if (!worldIn.isRemote) {
	    	  inventory = WorldEvents.getOrLoadInventory(playerIn);
	      } else 
	      {
	    	  inventory = ContainerTerrariaInventory.inventory;
	      }
	      
	      
	      InventorySlot bowSlot = inventory.hotbar[inventory.hotbarSelected];
	      float velocity = this.velocity;
	      float kb = 1.0f;
	      float speed = 1.0f;
	      float dmg = 1.0f;
	      float crit = 1.0f;
	      
	      if (bowSlot == null) {
	    	  return new ActionResult<>(ActionResultType.FAIL, itemstack);
	      }
	      
	      if (bowSlot.stack == null) {
	    	  return new ActionResult<>(ActionResultType.FAIL, itemstack);
	      }
	      if (!(bowSlot.stack.item == this)) {
	    	  return new ActionResult<>(ActionResultType.FAIL, itemstack);
	      }
	      if (ItemModifier.getModifier(bowSlot.stack.modifier) == null) {
	    	  bowSlot.stack.reforge(bowSlot.stack.item);
	    	  return new ActionResult<>(ActionResultType.FAIL, itemstack);
	      }
	      
	      
	      
	      if (bowSlot.stack != null) {
	    	  
	    	  kb = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).knockback / 100.0f);
	    	  crit = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).crit);
	    	  speed = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).speed / 100.0f);
	    	  dmg = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).damage / 100.0f);
	      }
		  	      

	      
	     
  	  if (!worldIn.isRemote) {
			double vel = (velocity) * (1.0f/6.0f);
  		 
			EntityTekhairaProjectile projectile = EntitiesT.TEKHAIRA_PROJECTILE.create(worldIn, null, null, playerIn, playerIn.getPosition().up(), SpawnReason.EVENT, false, false);
	         projectile.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float)vel, 0.0F);
	         
//	         projectile.setPosition(projectile.posX + projectile.getMotion().x, projectile.posY + projectile.getMotion().y, projectile.posZ + projectile.getMotion().z);
	         double damage = (this.projectileDamage + this.projectileDamage * dmg) * (1.0 + random.nextDouble() * 0.05f);
	         projectile.setDamage(damage);
	         if (random.nextInt(100) <= this.critChance + crit) {
	        	projectile.setDamage(this.projectileDamage + this.projectileDamage * dmg * 2);
	         }
	         Vec3d up = playerIn.getPositionVec().add(0, 1, 0);
	         projectile.setPosition(up.x, up.y, up.z);
	         projectile.setKnockback((int) (this.knockback) + (int) ((this.knockback) * kb));
	         projectile.shooter = playerIn;
	         shoot(projectile);
	         projectile.piercing = 0;
	         
	         projectile.weapon = this;
	         worldIn.addEntity(projectile);
	 	     
	      }
	      

	      return new ActionResult<>(ActionResultType.FAIL, itemstack);
	   }
}
