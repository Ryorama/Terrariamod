package kmerrill285.trewrite.entities.monsters;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityPossessedArmor extends ZombieEntity implements IHostile {
	   public int money;

	   public EntityPossessedArmor(EntityType type, World worldIn) {
	      super(type, worldIn);
	   }

	   public EntityPossessedArmor(World worldIn) {
		   super(worldIn);
	   }
	   
	   public SoundEvent getHurtSound() {
		   return SoundsT.HIT1;
	   }
	   
	   public SoundEvent getDeathSound() {
		   return SoundsT.KILLED2;
	   }

	   public SoundEvent getAmbientSound() {
		   return SoundsT.ZOMBIE1;
	   }
	   
	   public void dropLoot(DamageSource source, boolean b) {
	      if (Util.isChristmas() && this.rand.nextDouble() <= 0.0769D) {
	         EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PRESENT, 1, (ItemModifier)null));
	      }
	            
	      if (source.getImmediateSource() instanceof PlayerEntity) {
	         PlayerEntity player = (PlayerEntity)source.getImmediateSource();
	         if (player.getHealth() <= player.getMaxHealth() && this.rand.nextInt(12) == 0) {
	            EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
	         }
	      }

	      EntityCoin.spawnCoin(this.world, this.getPosition(), 0, this.money);
	   }

	   protected void registerAttributes() {
	      super.registerAttributes();
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)260);
	      this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue((double)28);
	      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((double)55);
	      this.money = this.rand.nextInt(20) + 50;
	   }
	}
