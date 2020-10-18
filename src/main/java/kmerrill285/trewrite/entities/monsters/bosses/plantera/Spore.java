package kmerrill285.trewrite.entities.monsters.bosses.plantera;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Spore extends MobEntity implements IHostile {

	public PlayerEntity target = null;
	public double motionX;
	public double motionY;
	public double motionZ;
	
	public Spore(EntityType<Spore> type, World worldIn) {
		super(type, worldIn);
	}
	
	public Spore(World worldIn) {
	      super(EntitiesT.SPORE, worldIn);
	}

	public void onCollideWithPlayer(PlayerEntity player) {
	      player.attackEntityFrom(DamageSource.causeMobDamage(this), 70f);
	      remove();
	      return;
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
 	   		this.setMotion(motionX, motionY, motionZ);
 	   	}
 	 }
}
