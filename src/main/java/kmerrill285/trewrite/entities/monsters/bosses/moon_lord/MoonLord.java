package kmerrill285.trewrite.entities.monsters.bosses.moon_lord;

import javax.annotation.Nullable;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.monsters.bosses.golem.GolemBody;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class MoonLord extends MobEntity implements IHostile {

	public double motionX;
	public double motionY;
	public double motionZ;
	
	public static boolean isAlive = false;
	public boolean transformed = false;
	
	public PlayerEntity target = null;
	
	public int health = 16000;
	
	public int defense = 20;
	
	public boolean canBeAttacked = false;
	
	public static int phase = 1;
		
	public int SHOOT_TIME = 10;
	public int timeUntilShoot = 30;
	public int lasers = 0;
	public int timeUntilNextLaser = 10;
	
	
	public MoonLord(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		isAlive = true;
	}
	
	public MoonLord(World world) {
		super(EntitiesT.MOON_LORD, world);
	}
	
	  public void onCollideWithPlayer(PlayerEntity player) {
	      player.attackEntityFrom(DamageSource.causeMobDamage(this), 16F);
	  }
	   
	  public void dropLoot(DamageSource source, boolean b) {
		  isAlive = false;
	  }
	
	  @Nullable
	   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
	      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
	      this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(defense);
	      this.setHealth(16000);
	      return spawnDataIn;
	   }

	   public boolean isInvulnerable() {
		   return canBeAttacked;
	   }
	   
	   public void tick() {
		   double distance = 1000.0D;

	         for(int i = 0; i < this.world.getPlayers().size(); ++i) {
	            double dist = ((PlayerEntity)this.world.getPlayers().get(i)).getPositionVector().distanceTo(this.getPositionVector());
	            if (dist < distance) {
	               distance = dist;
	               target = (PlayerEntity)this.world.getPlayers().get(i);
	            }
	         }

		   super.tick();
		      
		   this.rotationYaw = 0;
		   this.rotationPitch = 0;
		   
		   if (target != null) {
			   if (!world.isRemote) {
				   
				   if (this.posX < target.posX) {
    				   this.motionX = 0.4f;
    			   }
    			   else {
    				   this.motionX = -0.4f;
    			   }
    			   
    			   if (this.posZ < target.posZ) {
    				   this.motionZ = 0.4f;
    			   }
    			   else {
    				   this.motionZ = -0.4f;
    			   }
    			  
    			   if (this.posY < target.posY) {
    				   this.motionY = 0.4f;
    			   }
    			   else {
    				   this.motionY = -0.4f;
    			   }	   
			   }
		   }	  
	   } 
	   
	   public boolean attackEntityFrom(DamageSource source, float amount) {
		   if (canBeAttacked == true) {
			   return true;
		   }
		   return false;	
	   }
}
