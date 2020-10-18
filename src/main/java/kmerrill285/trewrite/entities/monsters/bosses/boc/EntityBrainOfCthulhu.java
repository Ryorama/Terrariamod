package kmerrill285.trewrite.entities.monsters.bosses.boc;

import javax.annotation.Nullable;

import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.monsters.EntityDemonEye;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityBrainOfCthulhu extends FlyingEntity implements IEntityAdditionalSpawnData, IHostile {

	public PlayerEntity target = null;
	public double motionX;
	public double motionY;
	public double motionZ;
	
	public int minions = 20;
	
	public boolean summonedMinions = false;
	
	public int maxHealth;
	public float bosshealth;
	public int defense = 0;
	
	public static boolean isBocA = false;
	
	public boolean transformed = false;
	
	public static int phase = 1;
	
	public boolean canBeAttacked = true;
	
	public EntityBrainOfCthulhu(EntityType<EntityBrainOfCthulhu> type, World worldIn) {
		super(type, worldIn);
		phase = 1;
		this.maxHealth = 3000;
	    this.bosshealth = this.maxHealth;
	    this.setHealth(this.maxHealth);
	    isBocA = true;
	    WorldStateHolder.get(worldIn).creepersDefeated = 0;
	}
	
	 public EntityBrainOfCthulhu(World world) {
		super(EntitiesT.BOC, world);
	 }
	 
	 public SoundEvent getHurtSound(DamageSource source) {
		   return SoundsT.HIT1;
	 }
	
	 public void onCollideWithPlayer(PlayerEntity player) {
		 if (player != null && (double)player.getDistance(this) <= 1.5D) {
             player.attackEntityFrom(DamageSource.causeMobDamage(this), 30);
		 }
	 }
	 
	 
	 @Nullable
	   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {

	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3000);
	   
	      this.maxHealth = 3000;
	      this.bosshealth = this.maxHealth;
	      this.setHealth(this.maxHealth);
	      return spawnDataIn;
	   }

	   public void tryDamagePlayer(PlayerEntity player) {
	         if (player != null && (double)player.getDistance(this) <= 1.5D) {
	               player.attackEntityFrom(DamageSource.causeMobDamage(this), 15.0F);
	         }

	   }

	   public boolean canRenderOnFire() {
	      return false;
	   }

	   public boolean isInvulnerable() {
		      return canBeAttacked;
		}
	   
	   public void setFire(int seconds) {
	   }
	   
	   public float getHealth() {
		   return this.bosshealth;
	   }
	   
	   public void tick() {
		   
		   double distance = 1000.0D;
		   
		   for(int i = 0; i < this.world.getPlayers().size(); ++i) {
	              double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPositionVector().distanceTo(this.getPositionVector());
	              if (dist < distance) {
	                 distance = dist;
	                 this.target = (PlayerEntity)this.world.getPlayers().get(i);
	              }
		   }
		   
		   super.tick();
		   
		   System.out.println(this.getHealth());
		   		   
		   if (summonedMinions == false) {
			   for (int i = 0; i < minions; i++) {
				   EntityCreeper eye = (EntityCreeper)EntitiesT.CREEPER.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
	               eye.setPosition(this.posX, this.posY, this.posZ);
	               eye.setHealth(100);
	               this.world.addEntity(eye);
	               eye.money = 0;
	               eye.noClip = true;
			   }
			   summonedMinions = true;
		   }
		   
		   if (transformed == false && WorldStateHolder.get(world).creepersDefeated >= 20) {
			   WorldStateHolder.get(world).creepersDefeated = 0;
			   transformed = true;
			   phase = 2;
			   world.playSound((PlayerEntity)null, this.posX, this.posY, this.posZ, SoundsT.ROAR, SoundCategory.PLAYERS, 100, 1);
			   canBeAttacked = false;
		   }
		   
		   this.setNoGravity(true);
		   this.noClip = true;
		   double motionX = this.motionX;
    	   double motionY = this.motionY;
    	   double motionZ = this.motionZ;
		   if (target != null) {
			   if (!world.isRemote) {
    			   if (!target.isAlive()) {
    				   this.remove();
    				   isBocA = false;
    				   return;
    			   }
    			   
    			   if (phase == 1) {
	    			   if (rand.nextInt(30) == 0) {
	    				   System.out.println("teleport");
	    				   this.attemptTeleport(target.posX + 10, target.posY + 10, target.posZ + 10, true);
	    			   }
    			   }
    			   
    			   if (phase == 2) {
	    			   if (rand.nextInt(10) == 0) {
	    				   System.out.println("teleport");
	    				   this.attemptTeleport(target.posX + 5, target.posY + 5, target.posZ + 5, true);
	    			   }
    			   }
	    			   
    			   if (phase == 1) {
	    			   if (this.posX < target.posX) {
	    				   this.motionX = 0.1f;
	    			   }
	    			   else {
	    				   this.motionX = -0.1f;
	    			   }
	    			   
	    			   if (this.posZ < target.posZ) {
	    				   this.motionZ = 0.1f;
	    			   }
	    			   else {
	    				   this.motionZ = -0.1f;
	    			   }
	    			  
	    			   if (this.posY < target.posY) {
	    				   this.motionY = 0.1f;
	    			   }
	    			   else {
	    				   this.motionY = -0.1f;
	    			   }
    			   }
    			   
    			   if (phase == 2) {
	    			   if (this.posX < target.posX) {
	    				   this.motionX = 0.2f;
	    			   }
	    			   else {
	    				   this.motionX = -0.2f;
	    			   }
	    			   
	    			   if (this.posZ < target.posZ) {
	    				   this.motionZ = 0.2f;
	    			   }
	    			   else {
	    				   this.motionZ = -0.2f;
	    			   }
	    			  
	    			   if (this.posY < target.posY) {
	    				   this.motionY = 0.2f;
	    			   }
	    			   else {
	    				   this.motionY = -0.2f;
	    			   }
    			   }
    		   }
			   this.setMotion(motionX, motionY, motionZ);
    	   }   
	   }
	   
	   public boolean attackEntityFrom(DamageSource source, float amount) {
		   if (canBeAttacked == false) {
			      if (source != DamageSource.IN_WALL && source != DamageSource.FALL && source != DamageSource.CRAMMING) {
			            amount -= (float)this.defense;
			            if (amount < 1.0F) {
			               amount = 1.0F;
			            }
	
			            this.bosshealth -= amount;
			            super.setHealth(this.bosshealth);
			            super.attackEntityFrom(source, 0.0F);
	
			            if (this.bosshealth <= 0) {
			            	isBocA = false;
			            	this.remove();
			            }
			            
			            return true;
			      } else {
			    	  return false;
			      }
		   }
		   else {
			   return false;
		   }
	   }


		@Override
		public void writeSpawnData(PacketBuffer buffer) {
			
		}

		@Override
		public void readSpawnData(PacketBuffer additionalData) {
			
		}
}
