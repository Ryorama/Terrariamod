package kmerrill285.trewrite.entities.monsters.bosses;

import javax.annotation.Nullable;
import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityEowHead extends MobEntity implements IEntityAdditionalSpawnData, IHostile {
   public float velX;
   public float velY;
   public float velZ;
   public float oldVelX;
   public float oldVelY;
   public float oldVelZ;
   public int life = 150;
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
   public boolean spawnThings = true;
   public boolean ALREADY_SPAWNED = false;
   public boolean REMOVED = false;
   public static boolean isEOWAlive = false;
   public static boolean isEOWAlive2 = false;
   public MobEntity child = null;
   int dirX = 0;
   int dirY = 0;
   int dirZ = 0;

   public EntityEowHead(EntityType<EntityEowHead> type, World worldIn) {
      super(type, worldIn);
      isEOWAlive = true;
   }

   public EntityEowHead(World worldIn) {
      super(EntitiesT.EOW_HEAD, worldIn);
   }

   public SoundEvent getHurtSound(DamageSource source) {
	   return SoundsT.HIT1;
   }
   
   @Nullable
   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
      this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)this.life);
      return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
   }

   public void dropLoot(DamageSource source, boolean b) {
      if (!this.REMOVED) {
         isEOWAlive = false;
         isEOWAlive2 = isEOWAlive;
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
      player.attackEntityFrom(DamageSource.causeMobDamage(this), 8.0F);
   }

   public boolean canDespawn(double dist) {
      return this.ALREADY_SPAWNED && this.REMOVED;
   }

   public void tick() {
      super.tick();
      isEOWAlive2 = isEOWAlive;
      this.ALREADY_SPAWNED = true;
      this.noClip = true;
      this.setNoGravity(true);
      if (!this.world.getBlockState(this.getPosition()).getMaterial().blocksMovement()) {
         this.isAirBorne = true;
      } else {
         this.isAirBorne = false;
      }

      boolean despawn = true;

      for(int i = 0; i < this.world.getPlayers().size(); ++i) {
         if (((PlayerEntity)this.world.getPlayers().get(i)).getHealth() > 0.0F) {
            despawn = false;
            break;
         }
      }
      
      if (despawn) {
         this.REMOVED = true;
         isEOWAlive = false;
         this.remove();
      } else {
         this.preventDespawn();
         if (this.segments == 0) {
            this.segments = 48;
            this.ai[0] = 0;
         }

         this.segments = 48;
         if (!world.isRemote) {
            if (this.ai[0] == 0 && this.spawnThings) {
               this.world.playSound((PlayerEntity)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.HOSTILE, 2.0F, 0.5F);
               if (!this.world.isRemote()) {
                  this.world.getServer().getPlayerList().sendMessage((new StringTextComponent("The Eater of Worlds has awoken!")).applyTextStyles(new TextFormatting[]{TextFormatting.BLUE, TextFormatting.BOLD}));
               }

               EntityEowBody lastWormBody = null;

               for(int i = 0; i < this.segments; ++i) {
                  EntityEowBody wormBody = EntityEowBody.spawnWormBody(this.world, this.getPosition(), 150.0F, (MobEntity)(lastWormBody == null ? this : lastWormBody));
                  if (lastWormBody == null) {
                     this.child = wormBody;
                  } else {
                     lastWormBody.child = wormBody;
                  }

                  lastWormBody = wormBody;
               }

               EntityEowTail wormBody = EntityEowTail.spawnWormBody(this.world, this.getPosition(), 220.0F, (MobEntity)(lastWormBody == null ? this : lastWormBody));
               wormBody.owner = lastWormBody;
               this.ai[0] = 1;
            }

            if (child == null) {
            	System.out.println("child null");
            	isEOWAlive = false;
            	remove();
            }

            if (child.getHealth() <= 0.0F) {
            	System.out.println("dead");
            	remove();
            }

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
               float speed = 10.0F;
               float acceleration = 0.4F;
               float absVelX = Math.abs(this.velX);
               float absVelY = Math.abs(this.velY);
               float absVelZ = Math.abs(this.velZ);
               
               if (!target.isAlive()) {
            	   isEOWAlive = false;
               }
                
               if (!collision) {
                  if (this.velY > speed * 2.0F) {
                     this.velY = speed * 2.0F;
                  }

                  this.velY = (float)((double)this.velY - 0.25D);
                  if (this.velY < 0.0F) {
                     --this.velY;
                  }

                  if (this.velY < -speed * 2.0F) {
                     this.velY = -speed * 2.0F;
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
                        this.velZ += acceleration * 0.5F;
                     }
                  } else if (this.velZ > -speed) {
                     this.velZ -= acceleration * 0.5F;
                  }

                  if (this.dirX == 1) {
                     if (this.velX < speed) {
                        this.velX += acceleration * 0.5F;
                     }
                  } else if (this.velX > -speed) {
                     this.velX -= acceleration * 0.5F;
                  }
               } else {
                  if (System.currentTimeMillis() % 250L <= 25L) {
                     this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.BLOCK_WOOL_STEP, SoundCategory.HOSTILE, 1.0F, 1.0F, false);
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

   public static EntityEowHead spawnWormHead(World worldIn, BlockPos pos, float life, boolean spawnThings) {
      EntityEowHead head = (EntityEowHead)EntitiesT.EOW_HEAD.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
      head.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)life);
      head.setHealth(life);
      head.money = 300;
      head.segments = 48;
      head.spawnThings = spawnThings;
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

   public IPacket createSpawnPacket() {
      return NetworkHooks.getEntitySpawningPacket(this);
   }
}
