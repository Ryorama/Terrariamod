package kmerrill285.trewrite.entities.projectiles;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.blocks.pots.Pot;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntitySummoningImpFireball extends MobEntity implements IEntityAdditionalSpawnData {
   private int age = 0;
   public float hoverStart = 0.1F;
   public boolean dead = false;
   public boolean grabbed = false;

   public EntitySummoningImpFireball(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntitySummoningImpFireball(World worldIn) {
      super(EntitiesT.COIN, worldIn);
   }

   public EntitySummoningImpFireball(World worldIn, double x, double y, double z) {
      super(EntitiesT.COIN, worldIn);
      this.setPosition(x, y, z);
   }

   @OnlyIn(Dist.CLIENT)
   public boolean isInRangeToRenderDist(double distance) {
      double d0 = 64.0D * getRenderDistanceWeight();
      return distance < d0 * d0;
   }

   public EntitySize getSize(Pose pose) {
      return EntitySize.fixed(0.5F, 0.5F);
   }

   public boolean canUpdate() {
      return true;
   }

   public void canUpdate(boolean value) {
   }

   public void collideWithEntity(Entity e) {
   }

   protected void collideWithNearbyEntities() {
   }

   public AxisAlignedBB getCollisionBoundingBox() {
      return null;
   }

   public int getAge() {
      return this.age;
   }

   public boolean canDespawn() {
      return true;
   }

   public void playHurtSound(DamageSource source) {
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return false;
   }

   public void tick() {
      if (this.world.getBlockState(this.getPosition()).getBlock() == Blocks.WATER) {
         this.remove();
      } else {
         WorldStateHolder.get(this.world).setLight(this.getPosition(), 15, this.world.getDimension().getType());
         if (this.removed || this.getHealth() <= 0.0F || this.age >= 100) {
            super.tick();
         }

         if (this.world.getBlockState(this.getPosition()).getBlock() instanceof Pot) {
            this.world.setBlockState(this.getPosition(), BlocksT.AIR_BLOCK.getDefaultState());
         }

         this.setNoGravity(true);
         ++this.age;
         if (this.age >= 100) {
            this.remove();
         } else if (this.world.getBlockState(this.getPosition()).getMaterial().blocksMovement()) {
            this.remove();
         } else if (this.world.getBlockState(this.getPosition().down()).getMaterial().blocksMovement()) {
            this.remove();
         } else {
            for(int i = 0; i < 10; ++i) {
               this.world.addParticle(ParticleTypes.FLAME, this.posX + (this.rand.nextDouble() - 0.5D) * 0.5D, this.posY + (this.rand.nextDouble() - 0.5D) * 0.5D, this.posZ + (this.rand.nextDouble() - 0.5D) * 0.5D, 0.0D, 0.0D, 0.0D);
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

            this.posX += this.getMotion().x;
            this.posY += this.getMotion().y;
            this.posZ += this.getMotion().z;
         }
      }
   }

   public boolean hasNoGravity() {
      return true;
   }

   protected void onImpact(RayTraceResult result) {
      if (result.getType() == Type.ENTITY) {
         Entity entity = ((EntityRayTraceResult)result).getEntity();
         if (entity instanceof IHostile && entity instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)entity;
            living.attackEntityFrom(DamageSource.MAGIC, 21.0F);
            if (this.rand.nextBoolean()) {
               living.setFire(5);
            }

            this.remove();
         }
      }

   }

   public float lerp(float a, float b, float f) {
      return a * (1.0F - f) + b * f;
   }

   protected void registerData() {
      super.registerData();
   }

   public void read(CompoundNBT compound) {
      super.read(compound);
      this.age = compound.getInt("age");
   }

   public void readAdditional(CompoundNBT compound) {
      this.age = compound.getInt("age");
   }

   public void writeAdditional(CompoundNBT compound) {
      compound.putInt("age", this.age);
   }

   public void readSpawnData(PacketBuffer additionalData) {
   }

   public void writeSpawnData(PacketBuffer buffer) {
   }

   public IPacket createSpawnPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }
}
