package kmerrill285.trewrite.entities.monsters.bosses.skeletron;

import java.util.List;

import javax.annotation.Nullable;

import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.monsters.bosses.destroyer.EntityDestroyerBody;
import net.minecraft.entity.Entity;
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

public class EntitySkeletronRightArm2 extends MobEntity implements IEntityAdditionalSpawnData, IHostile {
	
	 public float velX;
	   public float velY;
	   public float velZ;
	   public float oldVelX;
	   public float oldVelY;
	   public float oldVelZ;
	   public int life = 600;
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
	   
	   public boolean upper = false;
   
	   EntitySkeletronRightArm owner;
	   static EntitySkeletronHead mainOwner;
	   
	   public boolean spawnedSegments = false;
	   
	   public EntitySkeletronRightArm2(EntityType<EntitySkeletronRightArm2> type, World worldIn) {
		      super(type, worldIn);
	   }
	   
	   public EntitySkeletronRightArm2(World worldIn) {
		      super(EntitiesT.SKELETRON_RIGHT_ARM2, worldIn);
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
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(600);
	      this.setHealth(600);
	      return spawnDataIn;
	   }

	   public void onCollideWithPlayer(PlayerEntity player) {
	      player.attackEntityFrom(DamageSource.causeMobDamage(this), 20.0F);
	   }
	   
	   public void tick() {
		   super.tick();
		      
		   this.setNoGravity(true);
		   
		   this.setRotation(0, 0);
		   
		   if (!world.isRemote) {
		        boolean collision = false;
		        if (this.world.getBlockState(this.getPosition()).getMaterial().blocksMovement()) {
		           collision = true;
		           
		        }
		   } 
	       	   
       	   if (!world.isRemote) {
       		if (owner == null) {
				 remove();
				 return;
			 }
			 if (owner.getHealth() <= 0) {
				 remove();
				 return;
			 }
			 if (mainOwner == null) {
				 remove();
				 return;
			 }
			 if (mainOwner.getHealth() <= 0) {
				 remove();
				 return;
			 }
			 if (owner.isAlive == false) {
				 remove();
				 return;
			 }
       	   }
       	 else {
			 List<Entity> entities = world.getEntitiesWithinAABB(EntitySkeletronRightArm.class, new AxisAlignedBB(getPosition().add(-100, -200, -100), getPosition().add(100, 200, 100)));
			 if (entities.size() > 0) {
				 this.owner = (EntitySkeletronRightArm) entities.get(0);
			 }
       	 	}
       	    if (!world.isRemote) {
       	    	if (owner != null) {
	       			 this.posX = owner.posX;
	       			 this.posY = owner.posY + 2;
	       			 this.posZ = owner.posZ - 2;
	       		 }
	       		 
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
