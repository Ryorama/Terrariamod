package kmerrill285.trewrite.entities.monsters.bosses.boc;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.util.Util;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityCreeper extends FlyingEntity implements IHostile {

	public PlayerEntity target = null;
	public double motionX;
	public double motionY;
	public double motionZ;
	
	public int money = 75;
	
	
	public boolean reachedPlayer = false;
	
	public int damage = 18;
	
	public EntityCreeper(EntityType<EntityCreeper> type, World worldIn) {
		super(type, worldIn);
	}
	
	public EntityCreeper(World world) {
		super(EntitiesT.CREEPER, world);
	 }
	
	public SoundEvent getHurtSound(DamageSource source) {
		   return SoundsT.HIT1;
	}
	
	public void dropLoot(DamageSource source, boolean b) {
		WorldStateHolder.get(world).creepersDefeated += 1;
		EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.CRIMTANE_ORE, 5 + rand.nextInt(7), null));
		EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.TISSUE_SAMPLES, 2 + rand.nextInt(3), null));
    	if (Util.isChristmas()) {
			if (rand.nextDouble() <= 0.0769) {
				EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PRESENT, 1, null));
			}
		}
    	if (money > 0) {
			EntityCoin.spawnCoin(world, getPosition(), EntityCoin.COPPER, money);
			
			if (source.getImmediateSource() instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity)source.getImmediateSource();
				if (player.getHealth() <= player.getMaxHealth()) {
					if (rand.nextInt(12) == 0) {
						EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
					}
				}
			}
    	} else {
    		EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
    	}
    }
	
	public void tryDamagePlayer(PlayerEntity player) {
        if (player != null && (double)player.getDistance(this) <= 1.5D) {
              player.attackEntityFrom(DamageSource.causeMobDamage(this), 20);
        }

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
		
		this.setNoGravity(true);
		this.noClip = true;
		double motionX = this.motionX;
   	   	double motionY = this.motionY;
   	   	double motionZ = this.motionZ;
   	   	
   	   	if (!world.isRemote) {
   	   		if (target != null) {
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
