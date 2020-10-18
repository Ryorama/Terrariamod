package kmerrill285.trewrite.items.terraria.magic_weapons;

import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.projectiles.EntityMagicProjectile;
import kmerrill285.trewrite.entities.projectiles.magic_projectiles.VilethornProjectile;
import kmerrill285.trewrite.events.ScoreboardEvents;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.MagicWeapon;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Vilethorn extends MagicWeapon {

	public Vilethorn() {
		super(new Properties().group(ItemGroup.COMBAT), "vilethorn", 10);
		this.mana = 10;
		this.knockback = 1;
		this.damage = 10;
		this.velocity = 32;
		this.useTime = 28;
		this.setBuySell(2000);
	}

	
	
	@Override
	public void shoot(EntityMagicProjectile projectile) {
		if (projectile.world.isRemote) return;
		EntityMagicProjectile last = projectile;
		last.piercing = 7;
		last.maxAge = 20;
		last.noClip = true;
		last.setNoGravity(true);
		for (int i = 0; i < 14; i++) {
			VilethornProjectile current = new VilethornProjectile(projectile.world, projectile.shooter);
			current.damage = last.damage;
			current.knockback = last.knockback;
			current.setPosition(last.posX, last.posY, last.posZ);
			float mul = 0.8f;
			current.setMotion(last.getMotion().x * mul, last.getMotion().y * mul, last.getMotion().z * mul);
			current.noClip = true;
			current.setNoGravity(true);
			current.piercing = 7;
			current.maxAge = 20;
			current.shooter = last.shooter;
			current.weapon = this;
			projectile.world.addEntity(current);
			last = current;
		}
	}

	@Override
	public void tick(EntityMagicProjectile projectile) {
		if (projectile.world.isRemote) return;
		projectile.setNoGravity(true);
		projectile.noClip = true;
		projectile.setMotion(projectile.getMotion().mul(0.7f, 0.7f, 0.7f));
	}

	@Override
	public void hit(EntityMagicProjectile projectile, LivingEntity entity) {
		
	}

	
	public void onLeftClick(Entity entity, BlockPos pos, PlayerEntity player, World worldIn, Hand handIn) {
		super.onLeftClick(entity, pos, player, worldIn, handIn);
		this.onItemRightClick(worldIn, player, handIn);
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	    ItemStack itemstack = playerIn.getHeldItem(handIn);

	      Score mana = ScoreboardEvents.getScore(worldIn.getScoreboard(), playerIn, ScoreboardEvents.MANA);
		  if (mana == null) return new ActionResult<>(ActionResultType.FAIL, itemstack);
		  
		
	      
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
	      float manaUse = this.mana;
	      
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
	    	  
	    	  if (bowSlot.stack.item instanceof MagicWeapon) {
	    		  velocity += velocity * (ItemModifier.getModifier(bowSlot.stack.modifier).velocity / 100.0);
	    	  }
	    	  kb = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).knockback / 100.0f);
	    	  crit = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).crit);
	    	  speed = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).speed / 100.0f);
	    	  dmg = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).damage / 100.0f);
	    	  manaUse += manaUse * (float) (ItemModifier.getModifier(bowSlot.stack.modifier).manaCost / 100.0f);
	      }
	      
		  if (ScoreboardEvents.getScore(playerIn.getWorldScoreboard(), playerIn, ScoreboardEvents.MAGIC_POWER).getScorePoints() > 0) {
			  dmg += 0.2;
		  }

		  
	      
	      if (mana.getScorePoints() - manaUse < 0) return new ActionResult<>(ActionResultType.FAIL, itemstack);
		  mana.setScorePoints(mana.getScorePoints() - (int)manaUse);
		  if (mana.getScorePoints() < 0) mana.setScorePoints(0);
		  ScoreboardEvents.getScore(playerIn.getWorldScoreboard(), playerIn, ScoreboardEvents.MANA_TIMER).setScorePoints(0);
	      
		  dmg -= 0.05 * ScoreboardEvents.getScore(playerIn.getWorldScoreboard(), playerIn, ScoreboardEvents.MANA_SICKNESS_EFFECT).getScorePoints();
	      
		  playerIn.getCooldownTracker().setCooldown(this, (int) ((this.useTime - this.useTime * speed) * (30.0 / 60.0)));
		  
	      
	     
  	  if (!worldIn.isRemote) {
			double vel = (velocity) * (1.0f/6.0f);
  		 
  		 	VilethornProjectile projectile = EntitiesT.VILETHORN.create(worldIn, null, null, playerIn, playerIn.getPosition().up(), SpawnReason.EVENT, false, false);
	         projectile.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float)vel, 0.0F);
	         
//	         projectile.setPosition(projectile.posX + projectile.getMotion().x, projectile.posY + projectile.getMotion().y, projectile.posZ + projectile.getMotion().z);
	         double damage = (this.damage + this.damage * dmg) * (1.0 + random.nextDouble() * 0.05f);
	         projectile.setDamage(damage);
	         if (random.nextInt(100) <= this.critChance + crit) {
	        	projectile.setDamage(this.damage + this.damage * dmg * 2);
	         }
	         projectile.setKnockback((int) (this.knockback) + (int) ((this.knockback) * kb));
	         projectile.shooter = playerIn;
	         shoot(projectile);
	         
	         projectile.weapon = this;
	         worldIn.addEntity(projectile);
	 	     
	      }
	      

	      return new ActionResult<>(ActionResultType.FAIL, itemstack);
	   }
}
