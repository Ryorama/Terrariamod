package kmerrill285.trewrite.entities.monsters.bosses;

import javax.annotation.Nullable;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.projectiles.EntityVileSpit;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityEowBody extends MobEntity implements IEntityAdditionalSpawnData, IHostile {
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
   public boolean ALREADY_SPAWNED = false;
   public boolean REMOVED = false;
   public MobEntity child;
   int dirX = 0;
   int dirY = 0;
   int dirZ = 0;
   public static boolean isEA = false;

   public EntityEowBody(EntityType type, World worldIn) {
      super(type, worldIn);
      isEA = true;
   }

   public EntityEowBody(World worldIn) {
      super(EntitiesT.EOW_BODY, worldIn);
   }
   
   public SoundEvent getHurtSound(DamageSource source) {
	   return SoundsT.HIT1;
   }

   @Nullable
   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
      this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)this.life);
      return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
   }

   public void dropLoot(DamageSource source, boolean b) {
      if (!this.REMOVED) {
         if (Util.isChristmas() && this.rand.nextDouble() <= 0.0769D) {
            EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PRESENT, 1, (ItemModifier)null));
         }
         
         if (source.getImmediateSource() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)source.getImmediateSource();
            if (player.getHealth() <= player.getMaxHealth() && this.rand.nextInt(12) == 0) {
               EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
            }
         }

         if (this.rand.nextInt(100) <= 50) {
            EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.DEMONITE_ORE, this.rand.nextInt(3) + 2, (ItemModifier)null));
            EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.SHADOW_SCALE, this.rand.nextInt(3) + 2, (ItemModifier)null));
         }

         EntityCoin.spawnCoin(this.world, this.getPosition(), 1, 3);
      }
   }

   @OnlyIn(Dist.CLIENT)
   public boolean isInRangeToRenderDist(double distance) {
      double d0 = 128.0D * getRenderDistanceWeight();
      return distance < d0 * d0;
   }

   public void onCollideWithPlayer(PlayerEntity player) {
      player.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
   }

   public boolean canDespawn(double dist) {
      return this.ALREADY_SPAWNED && this.REMOVED;
   }

   public void tick() {
      super.tick();
      this.ALREADY_SPAWNED = true;
      if (this.getHealth() <= 0) {
			return;
		}
      if (this.getHealth() > 0.0F) {
         this.preventDespawn();
         if (!this.world.isRemote) {
            boolean despawn = true;

            for(int i = 0; i < this.world.getPlayers().size(); ++i) {
               if (((PlayerEntity)this.world.getPlayers().get(i)).getHealth() > 0.0F) {
                  despawn = false;
                  break;
               }
            }

            if (despawn) {
               this.REMOVED = true;
               EntityEowHead.isEOWAlive = false;
               this.remove();
            }
            
            if (this.owner != null) {
               double distance = 1000.0D;

               for(int i = 0; i < this.world.getPlayers().size(); ++i) {
                  double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPositionVector().distanceTo(this.getPositionVector());
                  if (dist < distance) {
                     distance = dist;
                     this.target = (PlayerEntity)this.world.getPlayers().get(i);
                  }
               }
               
               if (this.target != null) {
            	   if (!target.isAlive()) {
            		   EntityEowHead.isEOWAlive = false;
            	   }
               }

               if (this.target != null && this.rand.nextInt(1000) <= 5) {
                  EntityVileSpit spit = (EntityVileSpit)EntitiesT.VILE_SPIT.create(this.world);
                  spit.setPosition(this.posX, this.posY, this.posZ);
                  Vec3d point = (new Vec3d(this.posX - this.target.posX, this.posY + 1.0D - this.target.posY, this.posZ - this.target.posZ)).normalize();
                  point = point.mul(2.0D, 2.0D, 2.0D);
                  spit.setMotion(-point.x, -point.y, -point.z);
                  this.world.addEntity(spit);
               }

               if (this.owner.getHealth() <= 0.0F && this.getHealth() > 0.0F && !this.world.isRemote) {
                  EntityEowHead wormBody = EntityEowHead.spawnWormHead(this.world, this.getPosition(), 65.0F, false);
                  wormBody.setPosition(this.posX, this.posY, this.posZ);
                  wormBody.spawnThings = false;
                  if (this.child instanceof EntityEowBody) {
                     ((EntityEowBody)this.child).owner = wormBody;
                  }

                  if (this.child instanceof EntityEowTail) {
                     ((EntityEowTail)this.child).owner = wormBody;
                  }

                  wormBody.child = this.child;
                  this.setHealth(0.0F);
               }

               if (this.child == null && !this.world.isRemote) {
                  EntityEowTail wormBody = EntityEowTail.spawnWormBody(this.world, this.getPosition(), 220.0F, this);
                  this.child = wormBody;
               }

               float dirX = (float)(this.owner.posX + 0.5D - (this.posX + 0.5D));
               float dirY = (float)(this.owner.posY + 0.5D - (this.posY + 0.5D));
               float dirZ = (float)(this.owner.posZ + 0.5D - (this.posZ + 0.5D));
               this.rotationYaw = (float)Math.toDegrees(Math.atan2((double)dirZ, (double)dirX)) - 90.0F;
               float length = (float)Math.sqrt((double)(dirX * dirX + dirY * dirY + dirZ * dirZ)) * 2.0F;
               float dist = (length - 12.0F) / length;
               float posX = dirX * dist;
               float posY = dirY * dist;
               float posZ = dirZ * dist;
               this.velX = 0.0F;
               this.velY = 0.0F;
               this.velZ = 0.0F;
               this.posX += (double)posX;
               this.posY += (double)posY;
               this.posZ += (double)posZ;
               this.setPosition(this.posX, this.posY, this.posZ);
            }

            this.oldVelX = this.velX + 0.0F;
            this.oldVelY = this.velY + 0.0F;
            this.oldVelZ = this.velZ + 0.0F;
            this.motionX = (double)(this.velX * 0.01F);
            this.motionY = (double)(this.velY * 0.01F);
            this.motionZ = (double)(this.velZ * 0.01F);
            this.setMotion(0.0D, 0.0D, 0.0D);
         }
      }
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return source != DamageSource.IN_WALL && source != DamageSource.FALL && source != DamageSource.CRAMMING ? super.attackEntityFrom(source, amount) : false;
   }

   public static EntityEowBody spawnWormBody(World worldIn, BlockPos pos, float life, MobEntity owner) {
      EntityEowBody head = (EntityEowBody)EntitiesT.EOW_BODY.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
      head.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)life);
      head.setHealth(life);
      head.money = 300;
      head.owner = owner;
      worldIn.addEntity(head);
      return head;
   }

   protected void registerData() {
      super.registerData();
   }

   public void read(CompoundNBT compound) {
      super.read(compound);
      if (compound.getBoolean("spawned")) {
         this.REMOVED = true;
         if (!this.world.isRemote()) {
            this.remove();
         }
      }

   }

   public void readAdditional(CompoundNBT compound) {
      if (compound.getBoolean("spawned")) {
         this.REMOVED = true;
         if (!this.world.isRemote()) {
            this.remove();
         }
      }

   }

   public void writeAdditional(CompoundNBT compound) {
      compound.putBoolean("spawned", this.ALREADY_SPAWNED);
   }

   public void writeSpawnData(PacketBuffer buffer) {
      buffer.writeBoolean(this.ALREADY_SPAWNED);
   }

   public void readSpawnData(PacketBuffer additionalData) {
      if (additionalData.readBoolean()) {
         this.REMOVED = true;
         if (!this.world.isRemote()) {
            this.remove();
         }
      }

   }

   @Override
   public IPacket createSpawnPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }
}
