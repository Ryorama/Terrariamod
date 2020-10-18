package kmerrill285.trewrite.entities.monsters.worms;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityWormBody extends MobEntity implements IHostile {
   public float velX;
   public float velY;
   public float velZ;
   public float oldVelX;
   public float oldVelY;
   public float oldVelZ;
   public int life = 60;
   public int[] ai = new int[5];
   public PlayerEntity target = null;
   public float rx;
   public float ry;
   public float rz;
   public int money;
   public double motionX;
   public double motionY;
   public double motionZ;
   public MobEntity owner;
   int dirX = 0;
   int dirY = 0;
   int dirZ = 0;

   public EntityWormBody(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityWormBody(World worldIn) {
      super(EntitiesT.WORM_BODY, worldIn);
   }

   @OnlyIn(Dist.CLIENT)
   public boolean isInRangeToRenderDist(double distance) {
      double d0 = 64.0D * getRenderDistanceWeight();
      return distance < d0 * d0;
   }

   public void onCollideWithPlayer(PlayerEntity player) {
      player.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
   }

   public void tick() {
      this.noClip = true;
      this.setNoGravity(true);
      super.tick();
      if (this.owner == null) {
         if (this.ticksExisted > 20 && !this.world.isRemote) {
            this.remove();
         }
      } else {
         if (!this.world.isRemote) {
            if (this.owner.getHealth() <= 0.0F) {
               this.remove();
            }

            if (this.owner.getHealth() < this.getHealth()) {
               this.setHealth(this.owner.getHealth());
            }

            if (this.getHealth() < this.owner.getHealth()) {
               this.owner.setHealth(this.getHealth());
            }
         }

         float dirX = (float)(this.owner.posX + 0.5D - (this.posX + 0.5D));
         float dirY = (float)(this.owner.posY + 0.5D - (this.posY + 0.5D));
         float dirZ = (float)(this.owner.posZ + 0.5D - (this.posZ + 0.5D));
         this.rotationYaw = (float)Math.toDegrees(Math.atan2((double)dirZ, (double)dirX)) - 90.0F;
         float length = (float)Math.sqrt((double)(dirX * dirX + dirY * dirY + dirZ * dirZ)) * 2.0F;
         float dist = (length - 1.0F) / length;
         float posX = dirX * dist;
         float posY = dirY * dist;
         float posZ = dirZ * dist;
         this.velX = 0.0F;
         this.velY = 0.0F;
         this.velZ = 0.0F;
         this.posX += (double)posX;
         this.posY += (double)posY;
         this.posZ += (double)posZ;
         this.setMotion((double)posX, (double)posY, (double)posZ);
      }

      this.oldVelX = this.velX + 0.0F;
      this.oldVelY = this.velY + 0.0F;
      this.oldVelZ = this.velZ + 0.0F;
      this.motionX = (double)(this.velX * 0.01F);
      this.motionY = (double)(this.velY * 0.01F);
      this.motionZ = (double)(this.velZ * 0.01F);
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return source != DamageSource.IN_WALL && source != DamageSource.FALL && source != DamageSource.CRAMMING ? super.attackEntityFrom(source, amount) : false;
   }

   public static EntityWormBody spawnWormBody(World worldIn, BlockPos pos, float life, MobEntity owner) {
      EntityWormBody head = (EntityWormBody)EntitiesT.WORM_BODY.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
      head.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)life);
      head.setHealth(life);
      head.money = 40;
      head.owner = owner;
      worldIn.addEntity(head);
      return head;
   }

   protected void registerData() {
      super.registerData();
   }

   public IPacket createSpawnPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }
}
