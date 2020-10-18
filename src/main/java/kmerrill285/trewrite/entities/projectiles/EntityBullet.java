package kmerrill285.trewrite.entities.projectiles;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.blocks.pots.Pot;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.items.Bullet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;

public class EntityBullet extends SnowballEntity {
   public Bullet bullet;
   public int piercing;
   public double damage;
   public double knockback;
   public boolean hitGround = false;

   public EntityBullet(World worldIn, LivingEntity shooter) {
      super(worldIn, shooter);
   }

   public EntityBullet(World worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public EntityBullet(EntityType p_i50172_1_, World p_i50172_2_) {
      super(p_i50172_1_, p_i50172_2_);
   }

   public EntityBullet(World world) {
      super(EntitiesT.BULLET, world);
   }

   public void setDamage(double damage) {
      this.damage = damage;
   }

   public void setKnockback(double knockback) {
      this.knockback = knockback;
   }

   public boolean hasNoGravity() {
      return true;
   }

   public void tick() {
      super.tick();
      if (this.world.getBlockState(this.getPosition()).getBlock() instanceof Pot) {
         this.world.setBlockState(this.getPosition(), BlocksT.AIR_BLOCK.getDefaultState());
      }

      if (this.hasNoGravity() && this.ticksExisted > 100) {
         this.remove();
      }

      if (this.bullet != null) {
         this.bullet.bulletTick(this);
         this.setNoGravity(true);
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
         if (result != null) {
            this.onImpact(result);
         }
      }

   }

   protected void onImpact(RayTraceResult result) {
      super.onImpact(result);
      if (result.getType() == Type.ENTITY && this.bullet != null) {
         Entity entity = ((EntityRayTraceResult)result).getEntity();
         if (entity instanceof LivingEntity) {
            this.bullet.onBulletHit(this, (LivingEntity)entity);
            entity.attackEntityFrom(DamageSource.GENERIC, (float)this.damage);
            ((LivingEntity)entity).knockBack(entity, (float)this.knockback * 0.01F, (double)(-((float)this.getMotion().normalize().x)), (double)(-((float)this.getMotion().normalize().z)));
            if (this.piercing > 0) {
               --this.piercing;
            } else {
               this.remove();
            }
         }
      }

   }
}
