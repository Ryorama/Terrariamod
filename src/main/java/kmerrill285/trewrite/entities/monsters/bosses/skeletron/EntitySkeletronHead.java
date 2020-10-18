package kmerrill285.trewrite.entities.monsters.bosses.skeletron;

import javax.annotation.Nullable;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.monsters.bosses.wof.EntityWallOfFleshEye;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import net.minecraft.client.Minecraft;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

public class EntitySkeletronHead extends MobEntity implements IEntityAdditionalSpawnData, IHostile {
	
	 	public float velX;
	   public float velY;
	   public float velZ;
	   public float oldVelX;
	   public float oldVelY;
	   public float oldVelZ;
	   public int life = 4400;
	   public PlayerEntity target = null;
	   public float rx;
	   public float ry;
	   public float rz;
	   public int money;
	   public double motionX;
	   public double motionY;
	   public double motionZ;
	   int dirX = 0;
	   int dirY = 0;
	   int dirZ = 0;
	   public boolean spawnThings = false;
	   public static boolean isSpinning = false;
	   
	   public static boolean isAlive = false;
	   
	   public boolean spawnedSegments = false;
	   
	   public EntitySkeletronHead(EntityType<EntitySkeletronHead> type, World worldIn) {
		   	super(type, worldIn);
		   	isAlive = true;
	   }
	   
	   public EntitySkeletronHead(World worldIn) {
		   super(EntitiesT.SKELETRON_HEAD, worldIn);
	   }

	   public SoundEvent getHurtSound(DamageSource source) {
		   return SoundsT.HIT2;
	   }
	   
	   @OnlyIn(Dist.CLIENT)
	   public boolean isInRangeToRenderDist(double distance) {
	      double d0 = 64.0D * getRenderDistanceWeight();
	      return distance < d0 * d0;
	   }
	   
	   @Nullable
	   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4400);
	      this.setHealth(4400);
	      return spawnDataIn;
	   }

	   public void onCollideWithPlayer(PlayerEntity player) {
	      player.attackEntityFrom(DamageSource.causeMobDamage(this), 32.0F);
	   }
	   
	   public void dropLoot(DamageSource source, boolean b) {
		   isAlive = false;
	   }
	   
	   public void tick() {
		   super.tick();
		      
		   if (!world.isRemote) {
		        boolean collision = false;
		        if (this.world.getBlockState(this.getPosition()).getMaterial().blocksMovement()) {
		           collision = true;
		           
		        }
		   } 
		   
		   double distance = 1000.0D;

           for(int i = 0; i < this.world.getPlayers().size(); ++i) {
              double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPositionVector().distanceTo(this.getPositionVector());
              if (dist < distance) {
                 distance = dist;
                 this.target = (PlayerEntity)this.world.getPlayers().get(i);
              }
           }
           
           this.setNoGravity(true);
           this.noClip = true;
           
           if (target != null) {
        	   if (!target.isAlive()) {
        		   isAlive = false;
        		   this.remove();
        	   }
        	   
        	   if (!world.isRemote()) {
        		   if (!spawnThings) {
        			   	spawnThings = true;
       		 			EntitySkeletronLeftArm leftarm = EntitiesT.SKELETRON_LEFT_ARM.create(world, null, null, null, new BlockPos(this.getPosition()), SpawnReason.EVENT, false, false);
       		 			leftarm.posX = this.posX;
       		 			leftarm.posY = this.posY;
       		 			leftarm.posZ = this.posZ;
       		 			leftarm.owner = this;
       		 			world.addEntity(leftarm);
       		 			EntitySkeletronLeftArm2.mainOwner = this;
       		 			
       		 			EntitySkeletronRightArm rightarm = EntitiesT.SKELETRON_RIGHT_ARM.create(world, null, null, null, new BlockPos(this.getPosition()), SpawnReason.EVENT, false, false);
	   		 			rightarm.posX = this.posX;
	   		 			rightarm.posY = this.posY;
	   		 			rightarm.posZ = this.posZ;
	   		 			rightarm.owner = this;
	   		 			world.addEntity(rightarm);
       		 			EntitySkeletronRightArm2.mainOwner = this;
        		   }
        	   }
        	  
        	   double motionX = this.motionX;
        	   double motionY = this.motionY;
        	   double motionZ = this.motionZ;
        	   
        	   this.rotationPitch = 0;
        	   
        	   if (!world.isRemote) {
        		   
        		   if (isSpinning == false) {
		       			if (this.posY < target.posY + 6) {
		       				 this.motionY = 0.1F;
		       			}
		       			
		       			if (this.posY > target.posY + 8) {
		       				this.motionY = -0.1F;
		       			}
		       			
		       			if (this.posX < target.posX - 4) {
		       				this.motionX = 0.3F;
		       			}
	
		       			if (this.posX > target.posX + 4) {
		       				this.motionX = -0.3F;
		       			}
		       			
		       			if (this.posZ < target.posZ - 4) {
		       				this.motionZ = 0.3F;
		       			}
	
		       			if (this.posZ > target.posZ + 4) {
		       				this.motionZ = -0.3F;
		       			}
        		   }
        		   
        		   if (isSpinning == true) {
        			   if (this.posY > target.posY) {
        				   this.motionY = -0.3F;
        			   }
        			   
        			   if (this.posY < target.posY + 3) {
        				   this.motionY = -0.3F;
        			   }
        			   
        			   if (this.posY < target.posY) {
        				   this.motionY = 0.3F;
        			   }
        			   
        			   if (this.posX < target.posX - 4) {
		       				this.motionX = 0.5F;
		       			}
	
		       			if (this.posX > target.posX + 4) {
		       				this.motionX = -0.5F;
		       			}
		       			
		       			if (this.posZ < target.posZ - 4) {
		       				this.motionZ = 0.5F;
		       			}
	
		       			if (this.posZ > target.posZ + 4) {
		       				this.motionZ = -0.5F;
		       			}
        		   }
        		   
	       			if (rand.nextInt(250) == 0) {
	       				isSpinning = true;
	       			}
	       			
	       			if (isSpinning == true && rand.nextInt(275) == 0) {
	       				isSpinning = false;
	       			}
	       			
	       			
        	   }
        	   
        	   this.setMotion(motionX, motionY, motionZ);
        	   
           }
	   }
		   
	   
	   public boolean attackEntityFrom(DamageSource source, float amount) {
		      return source != DamageSource.IN_WALL && source != DamageSource.FALL && source != DamageSource.CRAMMING ? super.attackEntityFrom(source, amount) : false;
	   }

	@Override
	public void writeSpawnData(PacketBuffer buffer) {
		
	}

	@Override
	public void readSpawnData(PacketBuffer additionalData) {
		
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
}
