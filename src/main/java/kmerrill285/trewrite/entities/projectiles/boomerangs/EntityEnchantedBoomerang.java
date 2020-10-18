package kmerrill285.trewrite.entities.projectiles.boomerangs;

import kmerrill285.trewrite.entities.BoomerangEntity;
import kmerrill285.trewrite.entities.EntitiesT;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class EntityEnchantedBoomerang extends BoomerangEntity {
   public EntityEnchantedBoomerang(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityEnchantedBoomerang(World worldIn) {
      super(EntitiesT.ENCHANTED_BOOMERANG, worldIn);
   }

   public void hitEntity(LivingEntity entity) {
   }
}
