package kmerrill285.trewrite.items.terraria.summoning;

import java.util.ArrayList;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.core.inventory.InventorySlot;
import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.core.inventory.container.ContainerTerrariaInventory;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.crafting.CraftingRecipe;
import kmerrill285.trewrite.crafting.Recipes;
import kmerrill285.trewrite.entities.summoning.EntitySummoningImp;
import kmerrill285.trewrite.events.ScoreboardEvents;
import kmerrill285.trewrite.events.WorldEvents;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.MagicWeapon;
import kmerrill285.trewrite.items.SummoningItem;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.scoreboard.Score;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ImpStaff extends SummoningItem {
	
	public ImpStaff() {
		super("imp_staff");
		this.damage = 21;
		this.knockback = 2;
		this.mana = 10;
		this.useTime = 36;
		this.velocity = 10;
		this.setTooltip("Summons an imp to fight for you");
		this.scale = 2.0f;
	}
	
	public void onLeftClick(Entity entity, BlockPos pos, PlayerEntity playerIn, World worldIn, Hand handIn) {
		super.onLeftClick(entity, pos, playerIn, worldIn, handIn);

		if (WorldEvents.summons.get(playerIn.getScoreboardName()) == null) {
			WorldEvents.summons.put(playerIn.getScoreboardName(), new ArrayList<Entity>());
		}
		
		
		if (WorldEvents.summons.get(playerIn.getScoreboardName()).size() >= SummoningItem.getMaxSummons(playerIn)) {
			return;
		}
		
	      Score mana = ScoreboardEvents.getScore(worldIn.getScoreboard(), playerIn, ScoreboardEvents.MANA);
		  if (mana == null) return;
		  
		
	      
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
	    	  return;
	      }
	      
	      if (bowSlot.stack == null) {
	    	  return;
	      }
	      if (!(bowSlot.stack.item == this)) {
	    	  return;
	      }
	      if (ItemModifier.getModifier(bowSlot.stack.modifier) == null) {
	    	  bowSlot.stack.reforge(bowSlot.stack.item);
	    	  return;
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

		  
	      
	      if (mana.getScorePoints() - manaUse < 0) return;
		  mana.setScorePoints(mana.getScorePoints() - (int)manaUse);
		  if (mana.getScorePoints() < 0) mana.setScorePoints(0);
		  ScoreboardEvents.getScore(playerIn.getWorldScoreboard(), playerIn, ScoreboardEvents.MANA_TIMER).setScorePoints(0);
	      
		  dmg -= 0.05 * ScoreboardEvents.getScore(playerIn.getWorldScoreboard(), playerIn, ScoreboardEvents.MANA_SICKNESS_EFFECT).getScorePoints();
	      
		  playerIn.getCooldownTracker().setCooldown(this, (int) ((this.useTime - this.useTime * speed) * (30.0 / 60.0)));
		  
	      
	     
 	  if (!worldIn.isRemote) {
			double vel = (velocity) * (1.0f/6.0f);
 		 
 		 	EntitySummoningImp projectile = EntitySummoningImp.spawnOrb(worldIn, playerIn.getPosition().up(2), playerIn);
	         
	         double damage = (this.damage + this.damage * dmg) * (1.0 + random.nextDouble() * 0.05f);
	         
	         worldIn.addEntity(projectile);
	 		WorldEvents.summons.get(playerIn.getScoreboardName()).add(projectile);

	      }
	      

	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		WorldEvents.summoningTargets.put(playerIn.getScoreboardName(), null);
	      
		
	      float rd = 1000.0f;
	      Vec3d vec3d = playerIn.getEyePosition(0);
	      Vec3d vec3d1 = playerIn.getLook(0);
	      Vec3d vec3d2 = vec3d.add(vec3d1.x * rd, vec3d1.y * rd, vec3d1.z * rd);
	     //   public static EntityRayTraceResult func_221269_a(World p_221269_0_, Entity p_221269_1_, Vec3d p_221269_2_, Vec3d p_221269_3_, AxisAlignedBB p_221269_4_, Predicate<Entity> p_221269_5_, double p_221269_6_) {

	      AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(rd)).grow(1.0D, 1.0D, 1.0D);
          EntityRayTraceResult result = ProjectileHelper.func_221269_a(playerIn.world, playerIn, vec3d, vec3d2, axisalignedbb, (p_215312_0_) -> {
             return !p_215312_0_.isSpectator() && p_215312_0_.canBeCollidedWith();
          }, rd);
	      
	      if (result != null && result.getHitVec() != null) {
	    	  if (worldIn.isRemote) {
	    		  for (int i = 0; i < vec3d.distanceTo(result.getHitVec()); i++) {
	    			  worldIn.addParticle(ParticleTypes.EFFECT, vec3d.x + vec3d1.x*i, vec3d.y + vec3d1.y*i, vec3d.z + vec3d1.z*i, 0, 0, 0);
	    		  }
    			  Vec3d vec = result.getHitVec();
    			  worldIn.addParticle(ParticleTypes.FLASH, vec.x, vec.y, vec.z, 0, 0, 0);
	    	  } else {
	    		  WorldEvents.summoningTargets.put(playerIn.getScoreboardName(), result.getEntity());
	    	  }
	      }
		return new ActionResult<>(ActionResultType.FAIL, itemstack);
	}
	
	public void setCraftingRecipes() { 
		Recipes.addRecipe(new CraftingRecipe(new ItemStackT(ItemsT.IMP_STAFF, 1), BlocksT.IRON_ANVIL, new ItemStackT(ItemsT.HELLSTONE_BAR, 17)));
	}

}
