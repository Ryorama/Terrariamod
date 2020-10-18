package kmerrill285.trewrite.entities;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.blocks.pots.Pot;
import kmerrill285.trewrite.util.Conversions;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceContext.BlockMode;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityFlail extends MobEntity implements IEntityAdditionalSpawnData {
   public Entity owner;
   public String ownername;
   public float damage;
   public float knockback;
   private int age = 0;
   public float hoverStart = 0.1F;
   public boolean dead = false;
   public boolean grabbed = false;

   public EntityFlail(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityFlail(World worldIn) {
      super(EntitiesT.FLAIL, worldIn);
   }

   public EntityFlail(World worldIn, double x, double y, double z) {
      super(EntitiesT.FLAIL, worldIn);
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

   public int getAge() {
      return this.age;
   }

   public void setAge(int i) {
      this.age = i;
   }

   public boolean canDespawn() {
      return true;
   }

   public boolean isInvulnerable() {
      return true;
   }

   public void playHurtSound(DamageSource source) {
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return false;
   }

   public void tick() {
      if (this.ownername != null && this.world.isRemote && this.owner == null) {
         for(int i = 0; i < this.world.getPlayers().size(); ++i) {
            if (((PlayerEntity)this.world.getPlayers().get(i)).getScoreboardName().contentEquals(this.ownername)) {
               this.owner = (Entity)this.world.getPlayers().get(i);
               break;
            }
         }
      }

      if (this.owner != null) {
         this.ownername = this.owner.getScoreboardName();
      }

      if (this.world.getBlockState(this.getPosition()).getBlock() instanceof Pot) {
         this.world.setBlockState(this.getPosition(), BlocksT.AIR_BLOCK.getDefaultState());
      }

      super.tick();
      if (!this.world.isRemote) {
         if (this.owner == null) {
            this.age = 0;
            this.remove();
         } else {
            ++this.age;
            if (this.age > 5) {
               if (this.owner == null) {
                  this.age = 0;
                  this.remove();
                  return;
               }

               if (this.getPositionVec().distanceTo(this.owner.getPositionVec().add(0.0D, 1.0D, 0.0D)) <= 1.0D) {
                  this.age = 0;
                  this.remove();
                  return;
               }

               this.noClip = true;
               this.posX = this.lerp(this.posX, this.owner.posX, 0.2D);
               this.posY = this.lerp(this.posY, this.owner.posY + 1.0D, 0.2D);
               this.posZ = this.lerp(this.posZ, this.owner.posZ, 0.2D);
               this.setPosition(this.posX, this.posY, this.posZ);
               this.setMotion(0.0D, 0.0D, 0.0D);
            } else {
               this.noClip = false;
               if (this.owner instanceof LivingEntity) {
                  LivingEntity e = (LivingEntity)this.owner;
                  if (e.getHealth() <= 0.0F) {
                     this.age = 0;
                     this.remove();
                     return;
                  }
               }

               float speed = 5.0F;
               float acceleration = 0.05F;
               int dirX = this.posX < this.owner.posX ? 1 : -1;
               int dirY = this.posY < this.owner.posY + 1.0D ? 1 : -1;
               int dirZ = this.posZ < this.owner.posZ ? 1 : -1;
               if (this.getPositionVec().distanceTo(this.owner.getPositionVec().add(0.0D, 2.5D, 0.0D)) > 3.0D) {
                  this.setMotion(this.getMotion().add((double)(acceleration * (float)dirX), (double)(acceleration * (float)dirY), (double)(acceleration * (float)dirZ)));
               }

               if (this.getPositionVec().distanceTo(this.owner.getPositionVec().add(0.0D, 1.0D, 0.0D)) > 5.0D) {
                  this.posX = this.lerp(this.posX, this.owner.posX, 0.2D);
                  this.posY = this.lerp(this.posY, this.owner.posY + 1.0D, 0.2D);
                  this.posZ = this.lerp(this.posZ, this.owner.posZ, 0.2D);
               }
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

            this.setRotation(0.0F, 0.0F);
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
      }
   }

   protected void hitEntity(LivingEntity entity) {
   }

   protected void onImpact(RayTraceResult result) {
      if (result.getType() == Type.ENTITY) {
         Entity entity = ((EntityRayTraceResult)result).getEntity();
         if (entity != this.owner) {
            entity.attackEntityFrom(DamageSource.GENERIC, this.damage);
            if (entity instanceof LivingEntity) {
               this.hitEntity((LivingEntity)entity);
               ((LivingEntity)entity).knockBack(entity, Conversions.feetToMeters * this.knockback, (double)Conversions.feetToMeters * -this.getMotion().x, (double)Conversions.feetToMeters * -this.getMotion().z);
            }
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

   public double lerp(double a, double b, double f) {
      return a * (1.0D - f) + b * f;
   }

   public static EntityFlail spawnOrb(World worldIn, BlockPos pos, Entity owner, float damage, float knockback, EntityType flail) {
      EntityFlail orb = (EntityFlail)flail.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
      orb.owner = owner;
      orb.damage = damage;
      orb.knockback = knockback;
      worldIn.addEntity(orb);
      return orb;
   }

   public IPacket createSpawnPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   public void writeSpawnData(PacketBuffer buffer) {
      buffer.writeString(this.ownername);
   }

   public void readSpawnData(PacketBuffer additionalData) {
      this.ownername = additionalData.readString().trim();
   }
}
