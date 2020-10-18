package kmerrill285.trewrite.entities.projectiles;

import java.util.Iterator;
import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.blocks.pots.Pot;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemT;
import kmerrill285.trewrite.items.ThrowableItem;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.Explosion.Mode;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityThrowingT extends SnowballEntity {
   public ItemT arrow;
   public float damage;
   public float knockback;
   public int lifetime;
   public boolean sticky = false;
   public boolean stuck = false;
   public boolean destroyTiles = false;
   public boolean bouncingAI = false;
   public int lightValue = 0;
   public double bounce = 0.5D;
   private boolean isExplosive = false;
   public LivingEntity thrower;
   public boolean hitGround = false;
   public double prevMotionX;
   public double prevMotionY;
   public double prevMotionZ;
   public double motionX;
   public double motionY;
   public double motionZ;

   public EntityThrowingT(World worldIn, LivingEntity shooter) {
      super(worldIn, shooter);
      this.thrower = shooter;
   }

   public LivingEntity getThrower() {
      return this.thrower;
   }

   public EntityThrowingT(World worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public EntityThrowingT(EntityType p_i50172_1_, World p_i50172_2_) {
      super(p_i50172_1_, p_i50172_2_);
   }

   public EntityThrowingT(World world) {
      super(EntitiesT.THROWING, world);
   }

   public void tick() {
      if (!this.bouncingAI) {
         super.tick();
         if (this.arrow instanceof ThrowableItem) {
            ((ThrowableItem)this.arrow).throwingTick(this);
         }
      }

      if (this.world.getBlockState(this.getPosition()).getBlock() instanceof Pot) {
         this.world.setBlockState(this.getPosition(), BlocksT.AIR_BLOCK.getDefaultState());
      }

      if (this.lightValue > 0) {
         WorldStateHolder.get(this.world).setLight(this.getPosition(), this.lightValue, this.world.getDimension().getType());
      }

      if (this.bouncingAI) {
         this.noClip = false;
         this.setNoGravity(true);
         float height = 0.1F;
         if (this.world.getBlockState(this.getPosition().add(0.0D, (double)(height - 0.1F), 0.0D)).isSolid()) {
            this.motionY = 0.0D;
            this.posY += 0.05000000074505806D;
         } else {
            this.motionY -= 0.03D;
         }

         if (this.world.getBlockState(this.getPosition().add(0.0D, (double)(height + 0.1F), 0.0D)).isSolid()) {
            this.motionY = 0.0D;
         }

         this.posY += 0.5D;
         if (this.world.getBlockState(this.getPosition().add(-0.10000000149011612D, (double)height, 0.0D)).isSolid()) {
            this.motionX = 0.0D;
         }

         if (this.world.getBlockState(this.getPosition().add(0.10000000149011612D, (double)height, 0.0D)).isSolid()) {
            this.motionX = 0.0D;
         }

         if (this.world.getBlockState(this.getPosition().add(0.0D, (double)height, -0.10000000149011612D)).isSolid()) {
            this.motionZ = 0.0D;
         }

         if (this.world.getBlockState(this.getPosition().add(0.0D, (double)height, 0.10000000149011612D)).isSolid()) {
            this.motionZ = 0.0D;
         }

         this.posY -= 0.5D;
         if (this.stuck) {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
         }

         this.motionX *= 0.9800000190734863D;
         this.motionY *= 0.9800000190734863D;
         this.motionZ *= 0.9800000190734863D;
         if (this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
         }

         if (this.stuck) {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
         }

         if (this.lifetime-- <= 0) {
            if (!this.world.isRemote) {
               this.explode();
            }
         } else {
            this.handleWaterMovement();
            this.world.addParticle(ParticleTypes.SMOKE, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
         }

         if (Math.abs(this.prevMotionY) > Math.abs(this.motionY) && this.motionY == 0.0D) {
            this.motionY = -this.prevMotionY * this.bounce;
            if (this.sticky) {
               this.stuck = true;
            }
         }

         if (Math.abs(this.prevMotionX) > Math.abs(this.motionX) && this.motionX == 0.0D) {
            this.motionX = -this.prevMotionX * this.bounce;
            if (this.sticky) {
               this.stuck = true;
            }
         }

         if (Math.abs(this.prevMotionZ) > Math.abs(this.motionZ) && this.motionZ == 0.0D) {
            this.motionZ = -this.prevMotionZ * this.bounce;
            if (this.sticky) {
               this.stuck = true;
            }
         }

         this.prevMotionX = this.motionX + 0.0D;
         this.prevMotionY = this.motionY + 0.0D;
         this.prevMotionZ = this.motionZ + 0.0D;
         if (this.stuck) {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
         }

         this.setMotion(this.motionX, this.motionY, this.motionZ);
         this.posX += this.motionX;
         this.posY += this.motionY;
         this.posZ += this.motionZ;
         Iterator var2 = this.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(this.getPosition().add(-1, -1, -1), this.getPosition().add(1, 1, 1))).iterator();

         while(var2.hasNext()) {
            LivingEntity entity = (LivingEntity)var2.next();
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
                  if (this.lifetime < 70 && !entity.isInvulnerable()) {
                     this.explode();
                  }
               }
            }
         }
      }

   }

   protected float getGravityVelocity() {
      return this.isExplosive ? 0.0F : 0.03F;
   }

   public double getYOffset() {
      return -0.0D;
   }

   public void explode() {
      if (this.isExplosive) {
         this.world.createExplosion(this, DamageSource.causeExplosionDamage(this.getThrower()), this.posX, this.posY, this.posZ, 3.0F, false, this.destroyTiles ? Mode.BREAK : Mode.NONE);
         Iterator var1 = this.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(this.getPosition().add(-3, -3, -3), this.getPosition().add(3, 3, 3))).iterator();

         while(var1.hasNext()) {
            LivingEntity entity = (LivingEntity)var1.next();
            if (entity.getPositionVec().distanceTo(this.getPositionVec()) <= 3.0D) {
               if (entity instanceof PlayerEntity) {
                  if (entity == this.getThrower()) {
                     entity.attackEntityFrom(DamageSource.causeExplosionDamage(this.getThrower()), this.damage);
                  }
               } else {
                  entity.attackEntityFrom(DamageSource.causeExplosionDamage(this.getThrower()), this.damage);
               }
            }
         }

         WorldStateHolder.get(this.world).setLight(this.getPosition(), 15, this.world.getDimension().getType());
         this.remove();
      }
   }

   public void setThrowingAttribs(int lifetime, boolean sticky, boolean destroyTiles, boolean bounce, int lightValue, boolean explosive) {
      this.lifetime = lifetime;
      this.lightValue = lightValue;
      this.bouncingAI = true;
      this.setMotion(this.getMotion().x * 0.5D, this.getMotion().y * 0.5D, this.getMotion().z * 0.5D);
      this.sticky = sticky;
      this.destroyTiles = destroyTiles;
      this.bounce = 0.699999988079071D;
      if (bounce) {
         this.bounce = 1.0D;
      }

      this.isExplosive = explosive;
      this.motionX = this.getMotion().x;
      this.motionY = this.getMotion().y;
      this.motionZ = this.getMotion().z;
      this.setMotion(0.0D, 0.0D, 0.0D);
      this.setVelocity(0.0D, 0.0D, 0.0D);
   }

   public void applyEntityCollision(Entity entityIn) {
   }

   public void remove() {
      super.remove();
      if (this.arrow != null) {
         ThrowableItem a = (ThrowableItem)this.arrow;
         if (this.rand.nextDouble() <= a.recovery) {
            EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(this.arrow, 1));
         }
      }

   }

   protected void onImpact(RayTraceResult result) {
      if (!this.isExplosive && this.arrow != null) {
         ThrowableItem a = (ThrowableItem)this.arrow;
         if (!this.isExplosive) {
            a.onImpact(result, this);
         }
      }

   }

   public IPacket createSpawnPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }
}
