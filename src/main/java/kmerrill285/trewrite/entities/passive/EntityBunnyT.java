package kmerrill285.trewrite.entities.passive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityBunnyT extends RabbitEntity {
   public EntityBunnyT(EntityType p_i50247_1_, World p_i50247_2_) {
      super(p_i50247_1_, p_i50247_2_);
   }

   public EntityBunnyT(World p_i50247_2_) {
      super(EntityType.RABBIT, p_i50247_2_);
   }

   protected void registerAttributes() {
      super.registerAttributes();
      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
   }

   public void collideWithEntity(Entity entityIn) {
      if (entityIn instanceof MonsterEntity) {
         this.attackEntityFrom(DamageSource.causeMobDamage((MonsterEntity)entityIn), (float)((LivingEntity)entityIn).getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue());
      }

   }

   public void dropLoot(DamageSource source, boolean b) {
   }
}
