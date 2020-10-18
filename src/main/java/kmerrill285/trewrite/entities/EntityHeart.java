package kmerrill285.trewrite.entities;

import java.util.Iterator;
import java.util.List;
import kmerrill285.trewrite.events.ScoreboardEvents;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityHeart extends MobEntity {
   private int age = 0;
   public float hoverStart = 0.1F;
   public boolean dead = false;
   public boolean grabbed = false;

   public EntityHeart(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityHeart(World worldIn) {
      super(EntitiesT.HEART, worldIn);
   }

   public EntityHeart(World worldIn, double x, double y, double z) {
      super(EntitiesT.HEART, worldIn);
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
      this.setNoGravity(false);
      ++this.age;
      boolean moving = false;
      this.entityCollisionReduction = 1.0F;
      if (this.age > 6000 || this.dead) {
         this.remove();
      }

      if (this.age > 6000) {
         this.dead = true;
      }

      if (this.dead) {
         this.remove();
         this.removed = true;
      } else {
         World world = this.world;
         List players = world.getPlayers();
         double dist = 2.147483647E9D;
         PlayerEntity closest = null;
         Iterator var7 = players.iterator();

         while(var7.hasNext()) {
            PlayerEntity player = (PlayerEntity)var7.next();
            double d = player.getPositionVector().distanceTo(this.getPositionVector());
            if (d < dist) {
               dist = d;
               closest = player;
            }
         }

         if (closest != null && dist < 8.0D) {
            int heartreach = ScoreboardEvents.getScore(closest.getWorldScoreboard(), closest, ScoreboardEvents.HEARTREACH).getScorePoints();
            if ((heartreach > 0 && dist < 8.0D || dist <= 2.0D) && !this.dead && closest.getHealth() <= closest.getMaxHealth()) {
               float newX = this.lerp((float)this.posX, (float)closest.posX, 0.35F);
               float newY = this.lerp((float)this.posY, (float)closest.posY, 0.35F);
               float newZ = this.lerp((float)this.posZ, (float)closest.posZ, 0.35F);
               this.posX = (double)newX;
               this.posY = (double)newY;
               this.posZ = (double)newZ;
               moving = true;
               if (dist < 1.0D && !world.isRemote) {
                  ServerPlayerEntity player = (ServerPlayerEntity)closest;
                  if (!this.grabbed) {
                     player.heal(20.0F);
                     this.grabbed = true;
                     this.remove();
                  }
               }
            }
         }

         if (!moving) {
            this.move(MoverType.SELF, new Vec3d(0.0D, -0.5D, 0.0D));
         }

      }
   }

   public float lerp(float a, float b, float f) {
      return a * (1.0F - f) + b * f;
   }

   public static EntityHeart spawnHeart(World worldIn, BlockPos pos) {
      EntityHeart heart = (EntityHeart)EntitiesT.HEART.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
      worldIn.addEntity(heart);
      return heart;
   }

   public IPacket createSpawnPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   protected void registerData() {
      super.registerData();
   }
}
