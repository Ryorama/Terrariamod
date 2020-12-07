package kmerrill285.trewrite.entities.monsters;

import javax.annotation.Nullable;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class EntityDemon extends FlyingEntity {
   public float rx;
   public float ry;
   public float rz;
   public boolean collision;
   public boolean bounce;
   public double velX;
   public double velY;
   public double velZ;
   public double oldVelX;
   public double oldVelY;
   public double oldVelZ;
   public double speed = 2.0D;
   public double acc = 0.01D;
   public int money = 75;
   public int damage = 50;
   public float kbResist = 0.2F;
   public static final DataParameter type_data;

   public EntityDemon(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityDemon(World world) {
      super(EntitiesT.DEMON, world);
   }
   
   @Nullable
   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
      if (WorldStateHolder.get(worldIn).bloodmoon) {
         this.damage += 5;
      }

      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120);
      this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8);
      this.setHealth(120);

      return spawnDataIn;
   }

   public AxisAlignedBB getCollisionBoundingBox() {
      return super.getCollisionBoundingBox();
   }

   public void dropLoot(DamageSource source, boolean b) {
      if (this.money > 0 && Util.isChristmas() && this.rand.nextDouble() <= 0.0769D) {
         EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PRESENT, 1, (ItemModifier)null));
      }

      if (this.money > 0) {
         EntityCoin.spawnCoin(this.world, this.getPosition(), 0, this.money);
         if (source.getImmediateSource() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)source.getImmediateSource();
            if (player.getHealth() <= player.getMaxHealth() && this.rand.nextInt(12) == 0) {
               EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
            }
         }
      } else {
         EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
      }

   }

   public void onCollideWithPlayer(PlayerEntity entityIn) {
      if (entityIn != null && entityIn.getPositionVector().distanceTo(this.getPositionVector()) <= 1.5D) {
         //this.velX *= -2.0D;
         //this.velY *= -2.0D;
         //this.velZ *= -2.0D;
         this.dealDamage(entityIn);
      }

   }

   public void tick() {
      super.tick();
      this.setNoGravity(true);
      double motionY = this.getMotion().y;
      double motionX = this.getMotion().x;
      double motionZ = this.getMotion().z;
      if (!this.world.isRemote) {
         motionY = 0.0D;
         this.rotationPitch = 0.0F;
         this.rotationYaw = 0.0F;
         this.rotationYawHead = 0.0F;
         World world = this.world;
         PlayerEntity target = null;
         double distance = 1000.0D;

         for(int i = 0; i < world.getPlayers().size(); ++i) {
            if (!((PlayerEntity)world.getPlayers().get(i)).isCreative()) {
               double dist = ((PlayerEntity)world.getPlayers().get(i)).getPositionVector().distanceTo(this.getPositionVector());
               if (dist < distance) {
                  distance = dist;
                  target = (PlayerEntity)world.getPlayers().get(i);
               }
            }
         }

         if (world.getBlockState(new BlockPos(this.getPosition().getX(), this.getPosition().getY() - 1, this.getPosition().getZ())).getBlock().getDefaultState() == Blocks.WATER.getDefaultState() && this.velY < 0.0D) {
            this.velY = 3.0D;
         }

         if (!this.noClip && world.getBlockState(new BlockPos(this.posX, this.posY - 0.5D, this.posZ)).getMaterial().isSolid()) {
            this.velY = 2.0D;
         }

         if (this.bounce) {
            double absX = Math.abs(motionX);
            double absY = Math.abs(motionY);
            double absZ = Math.abs(motionZ);
            if (!this.onGround) {
               this.velX = this.oldVelX * -0.5D;
               if (this.velX > 0.0D && this.velX < 2.0D) {
                  this.velX = 2.0D;
               }

               if (this.velX < 0.0D && this.velX > -2.0D) {
                  this.velX = -2.0D;
               }

               this.velZ = this.oldVelZ * -0.5D;
               if (this.velZ > 0.0D && this.velZ < 2.0D) {
                  this.velZ = 2.0D;
               }

               if (this.velZ < 0.0D && this.velZ > -2.0D) {
                  this.velZ = -2.0D;
               }
            }

            if (this.onGround) {
               this.velY = this.oldVelY * -0.5D;
               if (this.velY > 0.0D && this.velY < 1.0D) {
                  this.velY = 1.0D;
               }

               if (this.velY < 0.0D && this.velY > -1.0D) {
                  this.velY = -1.0D;
               }
            }
         }

         if (target != null) {
            if (this.velX > -4.0D && this.posX > target.posX + (double)target.getWidth()) {
               this.velX -= 0.08D;
               if (this.velX > 4.0D) {
                  this.velX -= 0.04D;
               } else if (this.velX > 0.0D) {
                  this.velX -= 0.2D;
               }

               if (this.velX < -4.0D) {
                  this.velX = -4.0D;
               }
            } else if (this.velX < 4.0D && this.posX + 1.0D < target.posX) {
               this.velX += 0.08D;
               if (this.velX < -4.0D) {
                  this.velX += 0.04D;
               } else if (this.velX < 0.0D) {
                  this.velX += 0.2D;
               }

               if (this.velX > 4.0D) {
                  this.velX = 4.0D;
               }
            }

            if (this.velZ > -4.0D && this.posZ > target.posZ + (double)target.getWidth()) {
               this.velZ -= 0.08D;
               if (this.velZ > 4.0D) {
                  this.velZ -= 0.04D;
               } else if (this.velZ > 0.0D) {
                  this.velZ -= 0.2D;
               }

               if (this.velZ < -4.0D) {
                  this.velZ = -4.0D;
               }
            } else if (this.velZ < 4.0D && this.posZ + 1.0D < target.posZ) {
               this.velZ += 0.07999999821186066D;
               if (this.velZ < -4.0D) {
                  this.velZ += 0.04D;
               } else if (this.velZ < 0.0D) {
                  this.velZ += 0.2D;
               }

               if (this.velZ > 4.0D) {
                  this.velZ = 4.0D;
               }
            }

            if (this.velY > -2.5D && this.posY > target.posY + (double)target.getHeight()) {
               this.velY -= 0.10000000149011612D;
               if (this.velY > 2.5D) {
                  this.velY -= 0.05D;
               } else if (this.velY > 0.0D) {
                  this.velY -= 0.15D;
               }

               if (this.velY < -2.5D) {
                  this.velY = -2.5D;
               }
            } else if (this.velY < 2.5D && this.posY + 1.0D < target.posY) {
               this.velY += 0.10000000149011612D;
               if (this.velY < -2.5D) {
                  this.velY += 0.05D;
               } else if (this.velY < 0.0D) {
                  this.velY += 0.15D;
               }

               if (this.velY > 2.5D) {
                  this.velY = 2.5D;
               }
            }
         }

         this.bounce = false;
         this.oldVelX = this.velX + 0.0D;
         this.oldVelY = this.velY + 0.0D;
         this.oldVelZ = this.velZ + 0.0D;
         motionX = this.velX * 0.07500000298023224D;
         motionY = this.velY * 0.07500000298023224D;
         motionZ = this.velZ * 0.07500000298023224D;
         this.rotationYaw = (float)Math.toDegrees(Math.atan2(this.velZ, this.velX)) - 90.0F;
         if (this.hurtTime > 0 && this.getRevengeTarget() != null) {
            motionX = Math.cos(Math.toRadians((double)(this.getRevengeTarget().rotationYaw + 90.0F))) * 0.25D * Math.cos(Math.toRadians((double)(-this.getRevengeTarget().rotationPitch)));
            motionZ = Math.sin(Math.toRadians((double)(this.getRevengeTarget().rotationYaw + 90.0F))) * 0.25D * Math.cos(Math.toRadians((double)(-this.getRevengeTarget().rotationPitch)));
            motionY = Math.sin(Math.toRadians((double)(-this.getRevengeTarget().rotationPitch))) * 0.25D;
            motionX *= (double)(1.0F - this.kbResist);
            motionY *= (double)(1.0F - this.kbResist);
            motionZ *= (double)(1.0F - this.kbResist);
         }
      }

      this.setMotion(motionX, motionY, motionZ);
   }

   protected void dealDamage(LivingEntity entityIn) {
      if (this.isAlive() && this.canEntityBeSeen(entityIn) && entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength())) {
         this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         this.applyEnchantments(this, entityIn);
      }

   }

   protected int getAttackStrength() {
      return this.damage;
   }

   protected void registerData() {
      super.registerData();
      this.dataManager.register(type_data, 1);
   }

   static {
      type_data = EntityDataManager.createKey(EntityDemon.class, DataSerializers.VARINT);
   }
}
