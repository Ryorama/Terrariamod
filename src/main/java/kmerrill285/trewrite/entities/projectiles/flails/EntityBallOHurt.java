package kmerrill285.trewrite.entities.projectiles.flails;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityFlail;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class EntityBallOHurt extends EntityFlail {
   public EntityBallOHurt(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityBallOHurt(World worldIn) {
      super(EntitiesT.BALL_O_HURT, worldIn);
   }

   public void hitEntity(LivingEntity entity) {
   }
}
