package kmerrill285.trewrite.entities.pillars;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class StardustPillar extends MobEntity implements IHostile {

	public PlayerEntity target = null;
	
	public static boolean isAlive = false;
	
	public boolean canBeAttacked = true;
	
	public static boolean playerInRange = false;
	
	public StardustPillar(EntityType<? extends StardustPillar> type, World worldIn) {
		super(type, worldIn);
		isAlive = true;
		this.posY += 15;
	}
	
	public StardustPillar(World worldIn) {
		super(EntitiesT.PLANTERA, worldIn);
	}
	
	@Override
	public void dropLoot(DamageSource source, boolean b) {
		isAlive = false;
	}
	
	public boolean isInvulnerable() {
		return canBeAttacked;
	}
	
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}
	
	public void checkDespawn() {
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
		
		if (WorldStateHolder.get(world).stardustEnemiesDefeated >= 20) {
			canBeAttacked = false;
		}
	}

}
