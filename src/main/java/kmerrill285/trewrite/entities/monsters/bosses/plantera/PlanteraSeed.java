package kmerrill285.trewrite.entities.monsters.bosses.plantera;

import kmerrill285.trewrite.entities.EntitiesT;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class PlanteraSeed extends MobEntity {

	public double motionX;
	public double motionY;
	public double motionZ;
	
	public int health = 1;
	
	public static int shootDir = 0;
	
	public boolean canBeAttacked = true;
	
	public PlayerEntity target = null;
	
	public PlanteraSeed(EntityType<PlanteraSeed> type, World worldIn) {
		super(type, worldIn);
		canBeAttacked = true;
	}

	public PlanteraSeed(World worldIn) {
	      super(EntitiesT.PLANTERA_SEED, worldIn);
	}
	
	public boolean isInvulnerable() {
		return canBeAttacked;
	}
	
	 public void onCollideWithPlayer(PlayerEntity player) {
	      player.attackEntityFrom(DamageSource.causeMobDamage(this), 44F);
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
		
		this.noClip = true;
		
		double motionX = this.motionX;
 	   	double motionY = this.motionY;
 	   	double motionZ = this.motionZ;
		
 	   	if (!world.isRemote) {
	 	   	if (shootDir == 1) {
	 	   		this.motionX = 0.4f;
	 	   	}
	 	   	if (shootDir == 2) {
		   		this.motionX = -0.4f;
		   	}
	 	   	if (shootDir == 3) {
		   		this.motionZ = 0.4f;
		   	}
	 	   	if (shootDir == 4) {
		   		this.motionZ = -0.4f;
		   	}
	 	   	if (shootDir == 5) {
		   		this.motionY = 0.4f;
		   	}
		   	if (shootDir == 6) {
		   		this.motionY = -0.4f;
		   	}
 	   	}
		this.setMotion(motionX, motionY, motionZ);
	}
	
	public boolean attackEntityFrom(DamageSource source, float amount) {
		   return false;
	}
}
