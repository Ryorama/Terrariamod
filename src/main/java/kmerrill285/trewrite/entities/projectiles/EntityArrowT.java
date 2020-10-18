package kmerrill285.trewrite.entities.projectiles;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.blocks.pots.Pot;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.Arrow;
import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.items.ItemsT;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityArrowT extends ArrowEntity {
   public ItemT arrow;
   public int piercing;
   public boolean hitGround = false;

   public EntityArrowT(World worldIn, LivingEntity shooter) {
      super(worldIn, shooter);
   }

   public EntityArrowT(World worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public EntityArrowT(EntityType p_i50172_1_, World p_i50172_2_) {
      super(p_i50172_1_, p_i50172_2_);
   }

   public EntityArrowT(World world) {
      super(EntitiesT.ARROW, world);
   }

   public void tick() {
      super.tick();
      if (this.world.getBlockState(this.getPosition()).getBlock() instanceof Pot) {
         this.world.setBlockState(this.getPosition(), BlocksT.AIR_BLOCK.getDefaultState());
      }

      if (this.hasNoGravity() && this.ticksExisted > 100) {
         for(int i = 0; i < 10; ++i) {
            this.world.addParticle(ParticleTypes.END_ROD, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
         }

         this.remove();
      }

      if (this.timeInGround > 0) {
         this.remove();
         if (this.arrow != null) {
            ((Arrow)this.arrow).onArrowHit(this, (LivingEntity)null);
         }
      }

      if (this.arrow != null && this.arrow instanceof Arrow) {
         ((Arrow)this.arrow).arrowTick(this);
         Arrow a = (Arrow)this.arrow;
         if (!a.gravity) {
            this.setNoGravity(true);
         }
      }

      if (!this.world.isRemote()) {
         float rd = 1000.0F;
         Vec3d vec3d = this.getPositionVec();
         Vec3d vec3d1 = this.getMotion();
         Vec3d vec3d2 = vec3d.add(vec3d1.x, vec3d1.y, vec3d1.z);
         AxisAlignedBB bb = this.getBoundingBox().expand(vec3d1.scale((double)rd)).grow(1.0D, 1.0D, 1.0D);
         EntityRayTraceResult result = ProjectileHelper.func_221269_a(this.world, this, vec3d, vec3d2, bb, (p_215312_0_) -> {
            return !p_215312_0_.isSpectator() && p_215312_0_.canBeCollidedWith();
         }, (double)rd);
         if (result != null && result.getEntity() instanceof LivingEntity) {
            this.arrowHit((LivingEntity)result.getEntity());
         }
      }

   }

   public void arrowHit(LivingEntity hit) {
      if (hit.isInvulnerable()) {
         this.remove();
      } else {
         super.arrowHit(hit);
         if (this.arrow != null) {
            ((Arrow)this.arrow).onArrowHit(this, hit);
         }

      }
   }

   public void remove() {
      super.remove();
      if (this.arrow != null) {
         Arrow a = (Arrow)this.arrow;
         if (this.rand.nextDouble() <= (double)a.recovery) {
            if (this.rand.nextDouble() <= (double)a.dropRegular) {
               EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.WOODEN_ARROW, 1));
            } else {
               EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(this.arrow, 1));
            }
         }
      }

   }
}
