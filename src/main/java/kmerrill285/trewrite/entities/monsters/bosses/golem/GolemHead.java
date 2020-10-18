package kmerrill285.trewrite.entities.monsters.bosses.golem;

import javax.annotation.Nullable;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.projectiles.hostile.EntityEyeLaser;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class GolemHead extends MobEntity implements IHostile {

	public PlayerEntity target = null;
	public double motionX;
	public double motionY;
	public double motionZ;
	
	public static boolean isAlive = false;
	public boolean transformed = false;
	
	public int health = 16000;
	
	public int defense = 20;
	
	public boolean canBeAttacked = true;
	
	public static int phase = 1;
	
	public GolemBody owner = null;
	
	public int SHOOT_TIME = 10;
	public int timeUntilShoot = 30;
	public int lasers = 0;
	public int timeUntilNextLaser = 10;
		
	public GolemHead(EntityType<GolemHead> type, World worldIn) {
		super(type, worldIn);
	    phase = 1;
	    isAlive = true;
	    canBeAttacked = true;
	}

	  public GolemHead(World worldIn) {
	      super(EntitiesT.GOLEM_HEAD, worldIn);
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
	    			   if (owner.getHealth() > 0) {
	    				   this.posX = owner.posX;
	    				   this.posY = owner.posY + 2;
	    				   this.posZ = owner.posZ + 1;
	    			   }
	    		   }
	    	   }
	    	   
	    	   double motionX = this.motionX;
	    	   double motionY = this.motionY;
	    	   double motionZ = this.motionZ;	   
	    	   if (target != null) {
	    		   if (!world.isRemote) {
		    			   if (phase == 2) {
		    				   
		    				   this.setNoGravity(true);
		    				   this.noClip = true;
		    				   
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
		    	    			  
		    	    			   if (this.posY < target.posY + 7) {
		    	    				   this.motionY = 0.1f;
		    	    			   }
		    	    			   else {
		    	    				   this.motionY = -0.1f;
		    	    			   }
		    					   
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
	    	   			}
	    		    	this.setMotion(motionX, motionY, motionZ);
		    	  	}
  
				   if (phase == 2) {
					   canBeAttacked = false;
				   }			   			   
		  }
		   
		   public boolean attackEntityFrom(DamageSource source, float amount) {
			   if (canBeAttacked == false) {
				      if (source != DamageSource.IN_WALL && source != DamageSource.FALL && source != DamageSource.CRAMMING) {
				            amount -= (float)this.defense;
				            if (amount < 1.0F) {
				               amount = 1.0F;
				            }
		
				            this.health -= amount;
				            super.setHealth(this.health);
				            super.attackEntityFrom(source, 0.0F);
				            if (this.getHealth() <= 0) {
				            	isAlive = false;
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
		   
		   public void shootAtTarget(PlayerEntity target) {
				EntityEyeLaser spit = EntitiesT.EYE_LASER.create(world);
				spit.setPosition(posX, posY, posZ);
				
				Vec3d point = new Vec3d(posX - target.posX, (posY + 1+25) - target.posY, posZ - target.posZ).normalize();
				point = point.mul(2, 2, 2);
				
				spit.setMotion(-point.x, -point.y, -point.z);
				
				world.addEntity(spit);
		}
	
}
