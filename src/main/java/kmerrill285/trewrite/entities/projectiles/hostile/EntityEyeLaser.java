package kmerrill285.trewrite.entities.projectiles.hostile;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.blocks.pots.Pot;
import kmerrill285.trewrite.entities.EntitiesT;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityEyeLaser extends MobEntity implements IEntityAdditionalSpawnData {
   public int damage = 8;
   private int age = 0;
   public float hoverStart = 0.1F;
   public boolean dead = false;
   public boolean grabbed = false;

   public EntityEyeLaser(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityEyeLaser(World worldIn) {
      super(EntitiesT.COIN, worldIn);
   }

   public EntityEyeLaser(World worldIn, double x, double y, double z) {
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
            this.world.addParticle(ParticleTypes.PORTAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
         }

         AxisAlignedBB axisalignedbb = this.getBoundingBox().expand(this.getMotion()).grow(1.0D);
         RayTraceResult raytraceresult = ProjectileHelper.func_221267_a(this, axisalignedbb, (p_213880_1_) -> {
            return !p_213880_1_.isSpectator() && p_213880_1_.canBeCollidedWith();
         }, BlockMode.OUTLINE, true);
         if (raytraceresult.getType() != Type.MISS) {
            if (raytraceresult.getType() == Type.BLOCK && this.world.getBlockState(((BlockRayTraceResult)raytraceresult).getPos()).getBlock() == Blocks.NETHER_PORTAL) {
               this.setPortal(((BlockRayTraceResult)raytraceresult).getPos());
            } else if (!ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
               this.onImpact(raytraceresult);
            }
         }

         this.posX += this.getMotion().x;
         this.posY += this.getMotion().y;
         this.posZ += this.getMotion().z;
         this.lookAt(net.minecraft.command.arguments.EntityAnchorArgument.Type.EYES, this.getPositionVec().add(this.getMotion().x, this.getMotion().y, this.getMotion().z));
      }
   }

   public boolean hasNoGravity() {
      return true;
   }

   protected void onImpact(RayTraceResult result) {
      if (result.getType() == Type.ENTITY) {
         Entity entity = ((EntityRayTraceResult)result).getEntity();
         if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)entity;
            player.attackEntityFrom(DamageSource.MAGIC, (float)this.damage);
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
