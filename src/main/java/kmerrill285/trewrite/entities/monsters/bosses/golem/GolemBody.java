package kmerrill285.trewrite.entities.monsters.bosses.golem;

import java.util.Random;

import javax.annotation.Nullable;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class GolemBody extends MobEntity implements IHostile {

	public PlayerEntity target = null;
	public double motionX;
	public double motionY;
	public double motionZ;

	public static boolean isAlive = false;
	public boolean transformed = false;
	
	public boolean summonHead = false;
	
	Random rand;
	
	
			public GolemBody(EntityType<GolemBody> type, World worldIn) {
				super(type, worldIn);
				summonHead = false;
				isAlive = true;
			}
	
			public GolemBody(World worldIn) {
		      super(EntitiesT.GOLEM_BODY, worldIn);
			}
	   
		  	public void onCollideWithPlayer(PlayerEntity player) {
			      player.attackEntityFrom(DamageSource.causeMobDamage(this), 72F);
			}
		   
		   public void dropLoot(DamageSource source, boolean b) {
			   isAlive = false;
			   GolemHead.phase = 2;
		   }
		   
		   @Nullable
		   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(9000);
		      this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(26);
		      this.setHealth(9000);
		      return spawnDataIn;
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
			   
			   double motionX = this.motionX;
	    	   double motionY = this.motionY;
	    	   double motionZ = this.motionZ;
			  
	    	   if (this.getHealth() <= 0) {
				   GolemHead.phase = 2;
	    	   }
	    	   
	    	   
	    	   if (target != null) {
		    	   if (!world.isRemote) {
					   if (target.posX > this.posX) {
						   	this.jump();
						   	this.motionX = 0.2f;
					   }
					   
					   if (target.posX < this.posX) {
						   	this.jump();
						   	this.motionX = -0.2f;
					   }
					   
					   if (target.posZ > this.posZ) {
						   	this.jump();
						   	this.motionZ = 0.2f;
					   }
					   
					   if (target.posZ < this.posZ) {
						   	this.jump();
						   	this.motionZ = -0.2f;
					   }
		    	   }
		    	   this.setMotion(motionX, motionY, motionZ);
	    	   }
			   
			   if (summonHead == false) {
				   GolemHead eye = (GolemHead)EntitiesT.GOLEM_HEAD.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
				   eye.setPosition(this.posX, this.posY + 2, this.posZ + 1);
				   eye.owner = this;
				   this.world.addEntity(eye);
				   eye.noClip = true;
				   summonHead = true;
			   }
			   
		  }
}