package kmerrill285.trewrite.entities;

import java.util.List;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityShadowOrb extends MobEntity {
   public Entity owner;
   public long created;
   private int age = 0;
   public float hoverStart = 0.1F;
   public boolean dead = false;
   public boolean grabbed = false;

   public EntityShadowOrb(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityShadowOrb(World worldIn) {
      super(EntitiesT.SHADOW_ORB, worldIn);
   }

   public EntityShadowOrb(World worldIn, double x, double y, double z) {
      super(EntitiesT.SHADOW_ORB, worldIn);
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

   public boolean isInvulnerable() {
      return true;
   }

   public void playHurtSound(DamageSource source) {
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return false;
   }

   public void tick() {
      super.tick();
      this.noClip = true;
      this.setNoGravity(true);
      if (!this.world.isRemote) {
         if (this.owner == null) {
            this.remove();
         } else {
            if (this.owner instanceof LivingEntity) {
               LivingEntity e = (LivingEntity)this.owner;
               if (e.getHealth() <= 0.0F) {
                  this.remove();
                  return;
               }
            }

            float speed = 3.0F;
            float acceleration = 0.08F;
            double followY = 2.5D;
            if (!this.owner.onGround) {
               followY = -0.5D;
            }

            int dirX = this.posX < this.owner.posX ? 1 : -1;
            int dirY = this.posY < this.owner.posY + followY ? 1 : -1;
            int dirZ = this.posZ < this.owner.posZ ? 1 : -1;
            this.setMotion(this.getMotion().add((double)(acceleration * (float)dirX), (double)(acceleration * (float)dirY), (double)(acceleration * (float)dirZ)));
            if (Math.abs(this.getMotion().x) >= (double)speed) {
               this.getMotion().add((double)(-acceleration * (float)dirX), (double)(acceleration * (float)dirY), 0.0D);
            }

            if (Math.abs(this.getMotion().y) >= (double)speed * 0.5D) {
               this.getMotion().add((double)(acceleration * (float)dirX), (double)(-acceleration * (float)dirY) * 0.5D, (double)(acceleration * (float)dirZ));
            }

            if (Math.abs(this.getMotion().z) >= (double)speed) {
               this.getMotion().add(0.0D, (double)(acceleration * (float)dirY), (double)(-acceleration * (float)dirZ));
            }

            if (this.getPositionVec().distanceTo(this.owner.getPositionVec().add(0.0D, 2.5D, 0.0D)) > 0.0D) {
               this.posX = this.lerp(this.posX, this.owner.posX, 0.2D);
               this.posY = this.lerp(this.posY, this.owner.posY + followY, 0.2D);
               this.posZ = this.lerp(this.posZ, this.owner.posZ, 0.2D);
            }

            this.setPosition(this.posX, this.posY, this.posZ);
            WorldStateHolder.get(this.world).setLight(this.getPosition(), 15, this.world.getDimension().getType());
         }
      }
   }

   public double lerp(double a, double b, double f) {
      return a * (1.0D - f) + b * f;
   }

   public static EntityShadowOrb spawnOrb(World worldIn, BlockPos pos, Entity owner) {
      EntityShadowOrb orb = (EntityShadowOrb)EntitiesT.SHADOW_ORB.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
      orb.owner = owner;
      orb.created = System.currentTimeMillis();
      List entities = worldIn.getEntitiesWithinAABBExcludingEntity(orb, new AxisAlignedBB(orb.posX - 5.0D, orb.posY - 5.0D, orb.posZ - 5.0D, orb.posX + 5.0D, orb.posY + 5.0D, orb.posZ + 5.0D));

      for(int i = 0; i < entities.size(); ++i) {
         Entity e = (Entity)entities.get(i);
         if (e instanceof EntityShadowOrb && ((EntityShadowOrb)e).created < orb.created && ((EntityShadowOrb)e).owner == owner) {
            ((EntityShadowOrb)e).setHealth(0.0F);
            entities.remove(i);
         }
      }

      worldIn.addEntity(orb);
      return orb;
   }

   public IPacket createSpawnPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }
}
