package kmerrill285.trewrite.entities;

import java.util.Random;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityCoinPortal extends MobEntity implements IEntityAdditionalSpawnData {
   private int coins;
   private int age = 0;
   public float hoverStart = 0.1F;
   public boolean dead = false;
   public boolean grabbed = false;

   public EntityCoinPortal(EntityType type, World worldIn) {
      super(type, worldIn);
      this.coins = (new Random()).nextInt(10) + 5;
   }

   public EntityCoinPortal(World worldIn) {
      super(EntitiesT.COIN_PORTAL, worldIn);
      this.coins = (new Random()).nextInt(10) + 5;
   }

   public EntityCoinPortal(World worldIn, double x, double y, double z) {
      super(EntitiesT.COIN_PORTAL, worldIn);
      this.setPosition(x, y, z);
      this.coins = (new Random()).nextInt(10) + 5;
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
      this.noClip = true;
      if (this.age == 0) {
         this.coins = (new Random()).nextInt(10) + 5;
      }

      ++this.age;
      if (this.age % 20 == 0 && this.coins > 0) {
         --this.coins;
         EntityCoin coin = EntityCoin.spawnCoin(this.world, this.getPosition(), 2, 1);
         coin.posX += this.rand.nextDouble();
         coin.posY += this.rand.nextDouble();
         coin.posZ += this.rand.nextDouble();
         if (this.coins <= 0) {
            this.remove();
         }
      } else if (this.coins <= 0) {
         this.remove();
      }

   }

   public float lerp(float a, float b, float f) {
      return a * (1.0F - f) + b * f;
   }

   public static EntityCoinPortal spawnCoinPortal(World worldIn, BlockPos pos) {
      EntityCoinPortal star = (EntityCoinPortal)EntitiesT.COIN_PORTAL.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
      star.coins = (new Random()).nextInt(10) + 5;
      worldIn.addEntity(star);
      return star;
   }

   public void read(CompoundNBT compound) {
      super.read(compound);
      this.age = compound.getInt("age");
   }

   public void readAdditional(CompoundNBT compound) {
      this.age = compound.getInt("age");
      this.coins = compound.getInt("coins");
   }

   public void writeAdditional(CompoundNBT compound) {
      compound.putInt("age", this.age);
      compound.putInt("coins", this.coins);
   }

   public void writeSpawnData(PacketBuffer buffer) {
      buffer.writeInt(this.coins);
   }

   public void readSpawnData(PacketBuffer additionalData) {
      this.coins = additionalData.readInt();
   }

   public IPacket createSpawnPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }

   protected void registerData() {
      super.registerData();
   }
}
