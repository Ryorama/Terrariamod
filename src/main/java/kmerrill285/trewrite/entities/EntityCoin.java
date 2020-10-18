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
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityCoin extends MobEntity implements IEntityAdditionalSpawnData {
   public static final int COPPER = 0;
   public static final int SILVER = 1;
   public static final int GOLD = 2;
   public static final int PLATINUM = 3;
   public int coin = 0;
   public int amount = 1;
   private int age = 0;
   public float hoverStart = 0.1F;
   public boolean dead = false;
   public boolean grabbed = false;

   public EntityCoin(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityCoin(World worldIn) {
      super(EntitiesT.COIN, worldIn);
   }

   public EntityCoin(World worldIn, double x, double y, double z) {
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
         List items = this.world.getEntitiesWithinAABB(EntityCoin.class, new AxisAlignedBB(this.getPositionVec().add(-5.0D, -5.0D, -5.0D), this.getPositionVec().add(5.0D, 5.0D, 5.0D)));
         this.noClip = false;

         for(int i = 0; i < items.size(); ++i) {
            EntityCoin item = (EntityCoin)items.get(i);
            if (item != this && item.ticksExisted > this.ticksExisted && item.coin == this.coin && item.amount + this.amount <= 99) {
               if (this.getPositionVec().distanceTo(item.getPositionVec()) <= 1.0D) {
                  item.amount += this.amount;
                  this.remove();
                  this.removed = true;
                  return;
               }

               this.noClip = true;
               this.posX = (double)this.lerp((float)this.posX, (float)item.posX, 0.5F);
               this.posY = (double)this.lerp((float)this.posY, (float)item.posY, 0.5F);
               this.posZ = (double)this.lerp((float)this.posZ, (float)item.posZ, 0.5F);
               this.setPosition(this.posX, this.posY, this.posZ);
            }
         }

         World world = this.world;
         List players = world.getPlayers();
         double dist = 2.147483647E9D;
         PlayerEntity closest = null;
         Iterator var8 = players.iterator();

         while(var8.hasNext()) {
            PlayerEntity player = (PlayerEntity)var8.next();
            double d = player.getPositionVector().distanceTo(this.getPositionVector());
            if (d < dist) {
               dist = d;
               closest = player;
            }
         }

         if (closest != null && dist < 2.0D && !this.dead && closest.getHealth() <= closest.getMaxHealth()) {
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
                  int value = this.amount;
                  if (this.coin == 1) {
                     value *= 100;
                  }

                  if (this.coin == 2) {
                     value *= 10000;
                  }

                  if (this.coin == 3) {
                     value *= 1000000;
                  }

                  ScoreboardEvents.getScore(player.getWorldScoreboard(), player, ScoreboardEvents.COINS).increaseScore(value);
                  this.grabbed = true;
                  this.remove();
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

   protected void registerData() {
      super.registerData();
   }

   public void read(CompoundNBT compound) {
      super.read(compound);
      this.age = compound.getInt("age");
      this.coin = compound.getInt("coin");
      this.amount = compound.getInt("amount");
   }

   public void readAdditional(CompoundNBT compound) {
      this.age = compound.getInt("age");
      this.coin = compound.getInt("coin");
      this.amount = compound.getInt("amount");
   }

   public void writeAdditional(CompoundNBT compound) {
      compound.putInt("age", this.age);
      compound.putInt("coin", this.coin);
      compound.putInt("amount", this.amount);
   }

   public void writeSpawnData(PacketBuffer buffer) {
      buffer.writeInt(this.coin);
      buffer.writeInt(this.amount);
   }

   public void readSpawnData(PacketBuffer additionalData) {
      this.coin = additionalData.readInt();
      this.amount = additionalData.readInt();
   }

   public static EntityCoin spawnCoin(World worldIn, BlockPos pos, int type, int value) {
      EntityCoin coin = (EntityCoin)EntitiesT.COIN.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
      coin.coin = type;
      coin.amount = value;
      worldIn.addEntity(coin);
      return coin;
   }

   public IPacket createSpawnPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }
}
