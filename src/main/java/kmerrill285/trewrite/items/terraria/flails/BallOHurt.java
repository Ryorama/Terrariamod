package kmerrill285.trewrite.items.terraria.flails;

import java.util.HashMap;

import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.projectiles.flails.EntityBallOHurt;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.Flail;
import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.items.MagicWeapon;
import kmerrill285.trewrite.items.modifiers.EnumModifierType;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;


public class BallOHurt extends ItemT implements Flail {

	private static HashMap<String, EntityBallOHurt> shotEntity = new HashMap<String, EntityBallOHurt>();

	public BallOHurt() {
		super(new Properties().group(ItemGroup.COMBAT), "ball_o_hurt");
		this.knockback = 6.5f;
		this.damage = 15;
		this.velocity = 12;
		this.useTime = 45;
		this.melee = true;
		this.setBuySell(6500);
		this.MODIFIER_TYPE = EnumModifierType.MELEE;
	}

	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	    ItemStack itemstack = playerIn.getHeldItem(handIn);
	    	
	    if (shotEntity.get(playerIn.getScoreboardName()) != null) {
	    	if (shotEntity.get(playerIn.getScoreboardName()).getAge() <= 0) {
	    		shotEntity.get(playerIn.getScoreboardName()).remove();
	    		shotEntity.put(playerIn.getScoreboardName(), null);
	    	}
	    }
	      
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
	      else
	      
	      
	      if (bowSlot.stack != null) {
	    	  
	    	  if (bowSlot.stack.item instanceof MagicWeapon) {
	    		  velocity += velocity * (ItemModifier.getModifier(bowSlot.stack.modifier).velocity / 100.0);
	    	  }
	    	  kb = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).knockback / 100.0f);
	    	  crit = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).crit);
	    	  speed = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).speed / 100.0f);
	    	  dmg = (float) (ItemModifier.getModifier(bowSlot.stack.modifier).damage / 100.0f);
	    	  velocity *= (1.0 + speed);
	      }
	      
	      if (worldIn.isRemote()) {
	    	  if (shotEntity.get(playerIn.getScoreboardName()) == null)
				worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, SoundCategory.PLAYERS, 1F, 0.75F);
			}
  	  if (!worldIn.isRemote) {
			double vel = (velocity) * (1.0f/6.0f);
			
			if (shotEntity.get(playerIn.getScoreboardName()) == null) {
				EntityBallOHurt projectile = EntitiesT.BALL_O_HURT.create(worldIn, null, null, playerIn, playerIn.getPosition().up(), SpawnReason.EVENT, false, false);
		         projectile.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, (float)vel, 0.0F);
		         
//		         projectile.setPosition(projectile.posX + projectile.getMotion().x, projectile.posY + projectile.getMotion().y, projectile.posZ + projectile.getMotion().z);
		         double damage = (this.damage + this.damage * dmg) * (1.0 + random.nextDouble() * 0.05f);
		         projectile.damage = (float)damage;
		         if (random.nextInt(100) <= this.critChance + crit) {
		        	projectile.damage = (float)(this.damage + this.damage * dmg * 2);
		         }
		         projectile.posX = playerIn.posX;
		         projectile.posZ = playerIn.posZ;
		         projectile.knockback = ((int) (this.knockback) + (int) ((this.knockback) * kb));
		         projectile.owner = playerIn;
		         projectile.ownername = playerIn.getScoreboardName();
		         shotEntity.put(playerIn.getScoreboardName(), projectile);
		         worldIn.addEntity(projectile);
			} else {
				EntityBallOHurt projectile = shotEntity.get(playerIn.getScoreboardName());
				projectile.setAge(0);
			}
  		 	
	 	     
	      }
	      

	      return new ActionResult<>(ActionResultType.FAIL, itemstack);
	   }
}

