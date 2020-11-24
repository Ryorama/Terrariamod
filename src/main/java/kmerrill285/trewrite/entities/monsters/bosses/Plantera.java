package kmerrill285.trewrite.entities.monsters.bosses;

import javax.annotation.Nullable;

import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.entities.monsters.bosses.plantera.PlanteraSeed;
import kmerrill285.trewrite.entities.monsters.bosses.plantera.Spore;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class Plantera extends MobEntity implements IHostile {

		public PlayerEntity target = null;
		public double motionX;
		public double motionY;
		public double motionZ;
	
		public static boolean isAlive = false;
		public boolean transformed = false;
		
		public static int phase = 1;
	
		public Plantera(EntityType<Plantera> type, World worldIn) {
			super(type, worldIn);
			phase = 1;
			isAlive = true;
			 if (!worldIn.isRemote()) {
		         this.world.getServer().getPlayerList().sendMessage((new StringTextComponent("Plantera has awoken!")).applyTextStyles(new TextFormatting[]{TextFormatting.BLUE, TextFormatting.BOLD}));
		    }
		}

	   public Plantera(World worldIn) {
	      super(EntitiesT.PLANTERA, worldIn);
	   }
	   
	   public void onCollideWithPlayer(PlayerEntity player) {
		      player.attackEntityFrom(DamageSource.causeMobDamage(this), 50F);
	   }
		   
		   public void dropLoot(DamageSource source, boolean b) {
			   isAlive = false;
			   if (!this.world.isRemote()) {
			         this.world.getServer().getPlayerList().sendMessage((new StringTextComponent("Plantera has been defeated!")).applyTextStyles(new TextFormatting[]{TextFormatting.BLUE, TextFormatting.BOLD})); 
			   }
		   }
		   
		   @Nullable
		   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30000);
		      this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(36);
		      this.setHealth(30000);
		      return spawnDataIn;
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
			   
			   double motionX = this.motionX;
	    	   double motionY = this.motionY;
	    	   double motionZ = this.motionZ;
	    	   
	    	   if (transformed == false && this.getHealth() <= 15000) {
				   transformed = true;
				   phase = 2;
				   world.playSound((PlayerEntity)null, this.posX, this.posY, this.posZ, SoundsT.ROAR, SoundCategory.PLAYERS, 100, 1);
			   }
	    	   
	    	   if (target == null) {
	    		   isAlive = false;
	    		   remove();
	    		   return;
	    	   }
	    	   if (target.getHealth() <= 0) {
	    		   isAlive = false;
	    		   remove();
	    		   return;
	    	   }
	    	   
	    	   if (target != null) {
		    	   if (!world.isRemote) {
		    		   if (rand.nextInt(125) == 0) {
		    			   Spore eye = (Spore)EntitiesT.SPORE.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
	    	               eye.setPosition(this.posX, this.posY, this.posZ);		    	
	    	               this.world.addEntity(eye);
		    		   }
		    	   }
	    	   }
		    	   
	    	   if (target != null) {
	    		   if (!world.isRemote) {
	    			   if (rand.nextInt(50) == 0) {
		    			   if (target.posX > this.posX && target.posZ < this.posZ && target.posY < this.posY) {
		    				   PlanteraSeed eye = (PlanteraSeed)EntitiesT.PLANTERA_SEED.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
		    	               eye.setPosition(this.posX, this.posY, this.posZ);		    	
		    	               this.world.addEntity(eye);
		    	               PlanteraSeed.shootDir = 1;
		    			   }
		    			   if (target.posX < this.posX && target.posZ > this.posZ && target.posY > this.posY) {
		    				   PlanteraSeed eye = (PlanteraSeed)EntitiesT.PLANTERA_SEED.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
		    	               eye.setPosition(this.posX, this.posY, this.posZ);		    	
		    	               this.world.addEntity(eye);
		    	               PlanteraSeed.shootDir = 2;
		    			   }
		    			   if (target.posZ > this.posZ && target.posX < this.posX && target.posY < this.posY) {
		    				   PlanteraSeed eye = (PlanteraSeed)EntitiesT.PLANTERA_SEED.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
		    	               eye.setPosition(this.posX, this.posY, this.posZ);		    	
		    	               this.world.addEntity(eye);
		    	               PlanteraSeed.shootDir = 3;
		    			   }
		    			   if (target.posZ < this.posZ && target.posX > this.posX && target.posY > this.posY) {
		    				   PlanteraSeed eye = (PlanteraSeed)EntitiesT.PLANTERA_SEED.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
		    	               eye.setPosition(this.posX, this.posY, this.posZ);		    	
		    	               this.world.addEntity(eye);
		    	               PlanteraSeed.shootDir = 4;
		    			   }
		    			   if (target.posY > this.posY  && target.posZ < this.posZ && target.posX < this.posX) {
		    				   PlanteraSeed eye = (PlanteraSeed)EntitiesT.PLANTERA_SEED.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
		    	               eye.setPosition(this.posX, this.posY, this.posZ);		    	
		    	               this.world.addEntity(eye);
		    	               PlanteraSeed.shootDir = 5;
		    			   }
		    			   if (target.posY < this.posY && target.posX > this.posX && target.posZ > this.posZ) {
		    				   PlanteraSeed eye = (PlanteraSeed)EntitiesT.PLANTERA_SEED.create(this.world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, this.getPosition(), SpawnReason.EVENT, false, false);
		    	               eye.setPosition(this.posX, this.posY, this.posZ);		    	
		    	               this.world.addEntity(eye);
		    	               PlanteraSeed.shootDir = 6;
		    			   }
	    			   }
	    		   }
	    	   }
			   
	    	   
	    	   if (target != null) {
	    		   if (!world.isRemote) {
	    			   
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
	    			  else {
	    				  if (this.posX < target.posX) {
		    				   this.motionX = 0.3f;
		    			   }
		    			   else {
		    				   this.motionX = -0.3f;
		    			   }
		    			   
		    			   if (this.posZ < target.posZ) {
		    				   this.motionZ = 0.3f;
		    			   }
		    			   else {
		    				   this.motionZ = -0.3f;
		    			   }
		    			   
		    			   if (this.posY < target.posY) {
		    				   this.motionY = 0.3f;
		    			   }
		    			   else {
		    				   this.motionY = -0.3f;
		    			   }
	    			  }
	    			   
	    		   }
	    		   this.setMotion(motionX, motionY, motionZ);
	    	   }
		   }

}
