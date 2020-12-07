package kmerrill285.trewrite.entities.monsters.bosses.destroyer;

import java.util.Random;

import javax.annotation.Nullable;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.monsters.EntityDemonEye;
import kmerrill285.trewrite.entities.projectiles.hostile.EntityEyeLaser;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntityDestroyerBody extends MobEntity implements IHostile {
	   public float velX;
	   public float velY;
	   public float velZ;
	   public float oldVelX;
	   public float oldVelY;
	   public float oldVelZ;
	   public int life = 4800;
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
	   public static boolean isDBA = false;
	   public final int SHOOT_TIME = 15 * 20;
	   public int timeUntilShoot = 15 * 20;
	   public int lasers = 0;
	   public int timeUntilNextLaser = 10;

	   public EntityDestroyerBody(EntityType type, World worldIn) {
	      super(type, worldIn);
	   }

	   public EntityDestroyerBody(World worldIn) {
	      super(EntitiesT.DESTROYER_BODY, worldIn);
	      isDBA = true;
	   }

	   @OnlyIn(Dist.CLIENT)
	   public boolean isInRangeToRenderDist(double distance) {
	      double d0 = 64.0D * getRenderDistanceWeight();
	      return distance < d0 * d0;
	   }

	   public void onCollideWithPlayer(PlayerEntity player) {
	      player.attackEntityFrom(DamageSource.causeMobDamage(this), 40F);
	   }
	   
	   public void dropLoot(DamageSource source, boolean b) {
		   WorldStateHolder.get(world).mechBossesDowned += 1;
		   EntityDestroyerHead.isDesA = false;
		   isDBA = false;
		   //EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.MIGHT_SOUL, 40 + rand.nextInt(25), (ItemModifier)null));
		   //EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.HALLOW_BAR, 15 + rand.nextInt(15), (ItemModifier)null));
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
	   
	   @Nullable
	   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10000);
	      this.setHealth(10000);
	      return spawnDataIn;
	   }

	   public void tick() {
	      this.noClip = true;
	      this.setNoGravity(true);
	      super.tick();
	      
	      if (this.owner == null) {
	         if (this.ticksExisted > 20 && !this.world.isRemote) {
	  		   WorldStateHolder.get(world).mechBossesDowned += 1;
	           this.remove();
	         }
	      } else {
	         if (!this.world.isRemote) {
	            if (this.owner.getHealth() <= 0.0F) {
	     		   WorldStateHolder.get(world).mechBossesDowned += 1;
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
	      
	      if (EntityDestroyerHead.isDesA == false) {
	    	  WorldStateHolder.get(world).mechBossesDowned += 1;
			  this.remove();
		  } 
	      
	      if (target != null) {  	  
		      if (this.timeUntilShoot > 0) {
					this.timeUntilShoot--;
				} else {
					if (this.lasers < 4) {
						if (this.timeUntilNextLaser > 0) {
							this.timeUntilNextLaser--;
						} else {
							this.timeUntilNextLaser = 10;
							this.shootAtTarget(target);
							lasers++;
						}
					} else {
						lasers = 0;
						this.timeUntilShoot = this.SHOOT_TIME;
						this.timeUntilNextLaser = 10;
					}
				}       
	      }
	      
	      if (rand.nextInt(2000) == 0) {
	    	  EntityProbe eye = (EntityProbe)EntitiesT.PROBE.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
              eye.setPosition(this.posX, this.posY, this.posZ);
              eye.setHealth(60);
              eye.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5);
              this.world.addEntity(eye);
              eye.money = 0;
              eye.noClip = true;
	      }
	    
	   } 
	   
	   public void shootAtTarget(PlayerEntity target) {
			EntityEyeLaser spit = EntitiesT.EYE_LASER.create(world);
			spit.setPosition(posX, posY+25, posZ);
			
			Vec3d point = new Vec3d(posX - target.posX, (posY + 1+25) - target.posY, posZ - target.posZ).normalize();
			point = point.mul(2, 2, 2);
			
			spit.setMotion(-point.x, -point.y, -point.z);
			
			world.addEntity(spit);
		 }

	   public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSource.OUT_OF_WORLD)
			return super.attackEntityFrom(source, amount);
    	if (source == DamageSource.IN_WALL || source == DamageSource.FALL || source == DamageSource.CRAMMING || source == DamageSource.IN_FIRE || source == DamageSource.LAVA || source == DamageSource.ON_FIRE) return false;
    
    	this.performHurtAnimation();
    	super.attackEntityFrom(source, 0);
    	return false;

    }

	   public static EntityDestroyerBody spawnWormBody(World worldIn, BlockPos pos, float life, MobEntity owner) {
		  EntityDestroyerBody head = (EntityDestroyerBody)EntitiesT.DESTROYER_BODY.create(worldIn, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
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
