package kmerrill285.trewrite.entities.monsters.worms;

import java.util.Random;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityWormHead extends MobEntity implements IEntityAdditionalSpawnData, IHostile {
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
   public int segments;
   int dirX = 0;
   int dirY = 0;
   int dirZ = 0;

   public EntityWormHead(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityWormHead(World worldIn) {
      super(EntitiesT.WORM_HEAD, worldIn);
   }

   public void dropLoot(DamageSource source, boolean b) {
      if (Util.isChristmas() && this.rand.nextDouble() <= 0.0769D) {
         EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PRESENT, 1, (ItemModifier)null));
      }

      if (source.getImmediateSource() instanceof PlayerEntity) {
         PlayerEntity player = (PlayerEntity)source.getImmediateSource();
         if (player.getHealth() <= player.getMaxHealth() && this.rand.nextInt(12) == 0) {
            EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
         }
      }

      EntityCoin.spawnCoin(this.world, this.getPosition(), 0, this.money);
   }

   @OnlyIn(Dist.CLIENT)
   public boolean isInRangeToRenderDist(double distance) {
      double d0 = 64.0D * getRenderDistanceWeight();
      return distance < d0 * d0;
   }

   public void onCollideWithPlayer(PlayerEntity player) {
      player.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
   }

   public void tick() {
      super.tick();
      this.noClip = true;
      this.setNoGravity(true);
      if (!this.world.getBlockState(this.getPosition()).getMaterial().blocksMovement()) {
         this.isAirBorne = true;
      } else {
         this.isAirBorne = false;
      }

      if (!this.world.isRemote) {
         if (this.segments == 0) {
            this.segments = (new Random()).nextInt(3) + 6;
            this.ai[0] = 0;
         }

         if (this.ai[0] == 0) {
            EntityWormBody lastWormBody = null;

            for(int i = 0; i < this.segments; ++i) {
               EntityWormBody wormBody = EntityWormBody.spawnWormBody(this.world, this.getPosition(), this.getMaxHealth(), (MobEntity)(lastWormBody == null ? this : lastWormBody));
               lastWormBody = wormBody;
            }

            EntityWormTail.spawnWormBody(this.world, this.getPosition(), this.getMaxHealth(), (MobEntity)(lastWormBody == null ? this : lastWormBody));
            this.ai[0] = 1;
         }

         if (!this.world.isRemote) {
            boolean collision = false;
            if (this.world.getBlockState(this.getPosition()).getMaterial().blocksMovement()) {
               collision = true;
            }

            double distance = 1000.0D;

            for(int i = 0; i < this.world.getPlayers().size(); ++i) {
               double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPositionVector().distanceTo(this.getPositionVector());
               if (dist < distance) {
                  distance = dist;
                  this.target = (PlayerEntity)this.world.getPlayers().get(i);
               }
            }

            if (this.target != null) {
               float speed = 3.0F;
               float acceleration = 0.2F;
               float absVelX = Math.abs(this.velX);
               float absVelY = Math.abs(this.velY);
               float absVelZ = Math.abs(this.velZ);
               if (!collision) {
                  if (this.getPositionVector().distanceTo(this.target.getPositionVector()) <= 1.0D) {
                  }

                  if (this.velY > -speed) {
                     this.velY -= 0.05F;
                  }

                  if (absVelZ > 0.0F || this.dirZ == 0) {
                     if (this.posZ < this.target.posZ) {
                        this.dirZ = 1;
                     } else {
                        this.dirZ = -1;
                     }
                  }

                  if (absVelX > 0.0F || this.dirX == 0) {
                     if (this.posX < this.target.posX) {
                        this.dirX = 1;
                     } else {
                        this.dirX = -1;
                     }
                  }

                  if (this.dirZ == 1) {
                     if (this.velZ < speed) {
                        this.velZ += acceleration * 0.1F;
                     }
                  } else if (this.velZ > -speed) {
                     this.velZ -= acceleration * 0.1F;
                  }

                  if (this.dirX == 1) {
                     if (this.velX < speed) {
                        this.velX += acceleration * 0.1F;
                     }
                  } else if (this.velX > -speed) {
                     this.velX -= acceleration * 0.1F;
                  }
               } else {
                  if (System.currentTimeMillis() % 250L <= 25L) {
                     this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.BLOCK_WOOL_STEP, SoundCategory.HOSTILE, 1.0F, 1.0F, true);
                  }

                  if (absVelZ > 0.0F || this.dirZ == 0) {
                     if (this.posZ < this.target.posZ) {
                        this.dirZ = 1;
                     } else {
                        this.dirZ = -1;
                     }
                  }

                  if (absVelX > 0.0F || this.dirX == 0) {
                     if (this.posX < this.target.posX) {
                        this.dirX = 1;
                     } else {
                        this.dirX = -1;
                     }
                  }

                  if (absVelY > 0.0F || this.dirY == 0) {
                     if (this.posY < this.target.posY) {
                        this.dirY = 1;
                     } else {
                        this.dirY = -1;
                     }
                  }

                  if (this.dirZ == 1) {
                     if (this.velZ < speed) {
                        this.velZ += acceleration;
                     }
                  } else if (this.velZ > -speed) {
                     this.velZ -= acceleration;
                  }

                  if (this.dirX == 1) {
                     if (this.velX < speed) {
                        this.velX += acceleration;
                     }
                  } else if (this.velX > -speed) {
                     this.velX -= acceleration;
                  }

                  if (this.dirY == 1) {
                     if (this.velY < speed) {
                        this.velY += acceleration;
                     }
                  } else if (this.velY > -speed) {
                     this.velY -= acceleration;
                  }

                  if (absVelZ > speed * 0.5F && Math.abs(this.posZ - this.target.posZ) < Math.abs(this.posY - this.target.posY) && Math.abs(this.posZ + (double)this.velZ) > Math.abs(this.posZ - (double)this.velZ)) {
                     this.velZ -= acceleration * (float)this.dirZ;
                     this.velY += acceleration * (float)this.dirY;
                  }

                  if (absVelX > speed * 0.5F && Math.abs(this.posX - this.target.posX) < Math.abs(this.posY - this.target.posY) && Math.abs(this.posX + (double)this.velX) > Math.abs(this.posX - (double)this.velX)) {
                     this.velX -= acceleration * (float)this.dirX;
                     this.velY += acceleration * (float)this.dirY;
                  }

                  if (absVelY > speed * 0.5F) {
                     if (Math.abs(this.posX - this.target.posX) > Math.abs(this.posY - this.target.posY) && Math.abs(this.posY + (double)this.velY) > Math.abs(this.posY - (double)this.velY)) {
                        this.velY -= acceleration * (float)this.dirY;
                        this.velX += acceleration * (float)this.dirX;
                     }

                     if (Math.abs(this.posZ - this.target.posZ) > Math.abs(this.posY - this.target.posY) && Math.abs(this.posY + (double)this.velY) > Math.abs(this.posY - (double)this.velY)) {
                        this.velY -= acceleration * (float)this.dirY;
                        this.velZ += acceleration * (float)this.dirZ;
                     }
                  }
               }
            }

            this.rotationYaw = (float)Math.toDegrees(Math.atan2((double)this.velZ, (double)this.velX)) - 90.0F;
            this.oldVelX = this.velX + 0.0F;
            this.oldVelY = this.velY + 0.0F;
            this.oldVelZ = this.velZ + 0.0F;
            this.motionX = (double)(this.velX * 0.1F);
            this.motionY = (double)(this.velY * 0.1F);
            this.motionZ = (double)(this.velZ * 0.1F);
            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            this.move(MoverType.PISTON, new Vec3d(this.motionX, this.motionY, this.motionZ));
            this.setMotion(0.0D, 0.0D, 0.0D);
         }

      }
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return source != DamageSource.IN_WALL && source != DamageSource.FALL && source != DamageSource.CRAMMING ? super.attackEntityFrom(source, amount) : false;
   }

   public static void spawnWormHead(World worldIn, BlockPos pos, float life) {
      EntityWormHead head = (EntityWormHead)EntitiesT.WORM_HEAD.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
      head.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)life);
      head.setHealth(life);
      head.money = 40;
      head.segments = (new Random()).nextInt(3) + 6;
      worldIn.addEntity(head);
   }

   protected void registerData() {
      super.registerData();
   }

   public void read(CompoundNBT compound) {
      super.read(compound);
   }

   public void readAdditional(CompoundNBT compound) {
   }

   public void writeAdditional(CompoundNBT compound) {
   }

   public void writeSpawnData(PacketBuffer buffer) {
      buffer.writeInt(this.segments);
   }

   public void readSpawnData(PacketBuffer additionalData) {
      this.segments = additionalData.readInt();
   }

   public IPacket createSpawnPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }
}
