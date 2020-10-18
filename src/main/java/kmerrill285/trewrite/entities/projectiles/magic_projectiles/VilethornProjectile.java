package kmerrill285.trewrite.entities.projectiles.magic_projectiles;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.projectiles.EntityMagicProjectile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class VilethornProjectile extends EntityMagicProjectile {
   public VilethornProjectile(World worldIn, LivingEntity shooter) {
      super(worldIn, shooter, EntitiesT.VILETHORN);
   }

   public VilethornProjectile(EntityType p_i50172_1_, World p_i50172_2_) {
      super(p_i50172_1_, p_i50172_2_);
   }

   public VilethornProjectile(World world) {
      super(EntitiesT.VILETHORN, world);
   }
}
