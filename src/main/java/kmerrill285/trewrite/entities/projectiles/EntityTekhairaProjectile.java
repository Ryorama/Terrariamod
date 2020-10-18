package kmerrill285.trewrite.entities.projectiles;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.blocks.pots.Pot;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.items.terraria.broadswords.Tekhaira;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;

public class EntityTekhairaProjectile extends MobEntity {
   public Tekhaira weapon;
   public int piercing;
   public double damage;
   public double knockback;
   public LivingEntity shooter;
   public int maxAge = 100;
   public Vec3d VELOCITY = new Vec3d(0.0D, 0.0D, 0.0D);
   public boolean hitGround = false;

   public EntityTekhairaProjectile(World worldIn, LivingEntity shooter, EntityType type) {
      super(type, worldIn);
      this.shooter = shooter;
   }

   public EntityTekhairaProjectile(World worldIn, double x, double y, double z) {
      super(EntitiesT.TEKHAIRA_PROJECTILE, worldIn);
      this.setPosition(x, y, z);
   }

   public EntityTekhairaProjectile(EntityType p_i50172_1_, World p_i50172_2_) {
      super(p_i50172_1_, p_i50172_2_);
   }

   public EntityTekhairaProjectile(World world) {
      super(EntitiesT.TEKHAIRA_PROJECTILE, world);
   }

   public void setDamage(double damage) {
      this.damage = damage;
   }

   public void setKnockback(double knockback) {
      this.knockback = knockback;
   }

   public boolean isInvulnerable() {
      return true;
   }

   public void collideWithEntity(Entity e) {
   }

   protected void collideWithNearbyEntities() {
   }

   public AxisAlignedBB getCollisionBoundingBox() {
      return null;
   }

   public void tick() {
      super.tick();
      if (this.world.getBlockState(this.getPosition()).getBlock() instanceof Pot) {
         this.world.setBlockState(this.getPosition(), BlocksT.AIR_BLOCK.getDefaultState());
      }

      float f = MathHelper.sqrt(func_213296_b(this.getMotion()));
      this.rotationYaw = (float)(MathHelper.atan2(this.getMotion().x, this.getMotion().z) * 57.2957763671875D);
      this.rotationPitch = (float)(MathHelper.atan2(this.getMotion().y, (double)f) * 57.2957763671875D);
      this.prevRotationYaw = this.rotationYaw;
      this.prevRotationPitch = this.rotationPitch;
      if (this.ticksExisted > this.maxAge) {
         this.remove();
      } else {
         if (this.weapon != null) {
            this.weapon.tick(this);
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

         this.setMotion(this.VELOCITY);
      }
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return false;
   }

   public boolean hasNoGravity() {
      return true;
   }

   protected void onImpact(RayTraceResult result) {
      if (result.getType() == Type.ENTITY && this.weapon != null) {
         Entity entity = ((EntityRayTraceResult)result).getEntity();
         if (entity instanceof LivingEntity && entity != this.shooter && !(entity instanceof EntityTekhairaProjectile)) {
            this.weapon.hit(this, (LivingEntity)entity);
            entity.attackEntityFrom(DamageSource.GENERIC, (float)this.damage);
            ((LivingEntity)entity).knockBack(entity, (float)this.knockback, (double)((float)this.getMotion().x % 2.0F), (double)((float)this.getMotion().z % 2.0F));
            --this.piercing;
            if (this.piercing < 0) {
               this.remove();
            }
         } else {
            this.remove();
         }
      }

   }

   public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
      Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy, this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy, this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy).scale((double)velocity);
      this.setMotion(vec3d);
      float f = MathHelper.sqrt(func_213296_b(vec3d));
      this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D);
      this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * 57.2957763671875D);
      this.prevRotationYaw = this.rotationYaw;
      this.prevRotationPitch = this.rotationPitch;
   }

   public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
      float f = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
      float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * 0.017453292F);
      float f2 = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
      this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
      Vec3d vec3d = entityThrower.getMotion();
      this.setMotion(this.getMotion().add(vec3d.x, entityThrower.onGround ? 0.0D : vec3d.y, vec3d.z));
      this.VELOCITY = new Vec3d(this.getMotion().x + 0.0D, this.getMotion().y + 0.0D, this.getMotion().z + 0.0D);
   }
}
