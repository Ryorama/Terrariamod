package kmerrill285.trewrite.entities.monsters.bosses.wof;

import java.util.List;

import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.util.Util;
import net.minecraft.command.arguments.EntityAnchorArgument.Type;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TheHungryEntity extends FlyingEntity implements IHostile {
	
	public float rx, ry, rz;
	
	public boolean collision;

	public boolean bounce;
	
	public double velX, velY, velZ;
	public double oldVelX, oldVelY, oldVelZ;
	
	public double speed = 2;
	public double acc = 0.01;
	
	public int money = 75;
	
	public int damage = 18;
	
	public float kbResist = 0.2f;
	
	public EntityWallOfFlesh owner;
	
	public Vec3d firstPos;
	
	 public TheHungryEntity(EntityType<? extends TheHungryEntity> type, World worldIn) {
		super(type, worldIn);
		init();
	}

    public TheHungryEntity(World world) {
    	super(EntitiesT.THE_HUNGRY, world);
    	init();
    }
    
    
    public void init() {
    	
    }
    
    public AxisAlignedBB getCollisionBoundingBox() {
		return super.getCollisionBoundingBox();
    	
    }
    
    
    public void dropLoot(DamageSource source, boolean b) {
		EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
    }
    
    
    public void onCollideWithPlayer(PlayerEntity entityIn)
    {
    	if (entityIn != null)
    	if (entityIn.getPositionVector().distanceTo(getPositionVector()) <= 1.5f) {
			velX *= -2f;
			velY *= -2f;
			velZ *= -2f;
            this.dealDamage(entityIn);
    	}
    }
    
    public void tick() {
    	this.ignoreFrustumCheck = true;
    	this.noClip = true;
    	this.setNoGravity(true);
    	if (firstPos == null) {
    		firstPos = new Vec3d(posX + 0, posY + 0, posZ + 0);
    	}
    	if (owner == null) {
    		List<Entity> entities = world.getEntitiesWithinAABB(EntityWallOfFlesh.class, new AxisAlignedBB(getPosition().add(-100, -200, -100), getPosition().add(100, 200, 100)));
			 if (entities.size() > 0) {
				 this.owner = (EntityWallOfFlesh) entities.get(0);
			 }
    	}
    	if (owner != null) {
    		if (owner.bossHealth <= 0) {
				 remove();
				 return;
			 }
    		if (owner.getHealth() <= 0) {
				 remove();
				 return;
			 }
    		if (owner.REMOVED) {
				 remove();
				 return;
			 }
    	}
    	if (!world.isRemote()) {
			 if (owner == null) {
				 remove();
				 return;
			 }
			 if (owner.bossHealth <= 0) {
				 remove();
				 return;
			 }
			 if (owner.getHealth() <= 0) {
				 remove();
				 return;
			 }
		 } 
		 else {
			 List<Entity> entities = world.getEntitiesWithinAABB(EntityWallOfFlesh.class, new AxisAlignedBB(getPosition().add(-100, -200, -100), getPosition().add(100, 200, 100)));
			 if (entities.size() > 0) {
				 this.owner = (EntityWallOfFlesh) entities.get(0);
			 }
		 }
    	super.tick();
    	this.setNoGravity(true);
    	double motionY = this.getMotion().y;
    	double motionX = this.getMotion().x;
    	double motionZ = this.getMotion().z;
    	
    	if (!world.isRemote) { 
			motionY = 0;
			this.rotationPitch = 0;
			this.rotationYaw = 0;
			this.rotationYawHead = 0;
			World world = this.world;
			PlayerEntity target = null;
			double distance = 1000;
			for (int i = 0; i < world.getPlayers().size(); i++) {
				double dist = world.getPlayers().get(i).getPositionVector().distanceTo(this.getPositionVector());
				if (dist < distance) {
					distance = dist;
					target = world.getPlayers().get(i);
				}
			}
			
			
			
			if (target != null) {
				int dirX, dirY, dirZ;
				double acceleration = 0.1f;
				double speed = 3.0f;
				dirX = posX > target.posX ? -1 : 1;
				dirY = posY > target.posY ? -1 : 1;
				dirZ = posZ > target.posZ ? -1 : 1;
				
				velX += acceleration * dirX;
				velY += acceleration * dirY;
				velZ += acceleration * dirZ;
				
				
				if (Math.abs(velX) > speed) {
					velX -= acceleration * dirX;
				}
				if (Math.abs(velY) > speed) {
					velY -= acceleration * dirY;
				}
				if (Math.abs(velZ) > speed) {
					velZ -= acceleration * dirZ;
				}

				if (velX > speed) velX = speed;
				if (velX < -speed) velX = -speed;
				
				if (velY > speed) velY = speed;
				if (velY < -speed) velY = -speed;
				
				if (velZ > speed) velZ = speed;
				if (velZ < -speed) velZ = -speed;
				
				Vec3d gpos = this.owner.getPositionVec().mul(1, 0, 1).add(this.firstPos.mul(1, 0, 1).subtract(this.owner.firstPos.mul(1, 0, 1)));
				double dist = getPositionVec().mul(1, 0, 1).distanceTo(gpos);
				
				if (dist > 300) {
					this.setPosition(Util.lerp(posX, gpos.x, 0.05), Util.lerp(posY, firstPos.y, 0.05), Util.lerp(posZ, gpos.z, 0.05));
				}
			}
			
			
			
			oldVelX = velX + 0;
			oldVelY = velY + 0;
			oldVelZ = velZ + 0;
			motionX = velX * 0.075f;
			motionY = velY * 0.075f;
			motionZ = velZ * 0.075f;
			
			if (target != null) {
    		this.lookAt(Type.EYES, target.getPositionVec());
    		}
		}
    	this.setMotion(motionX, motionY, motionZ);
    	if (this.hurtResistantTime > 0) {
			Vec3d gpos = this.owner.getPositionVec().mul(1, 0, 1).add(this.firstPos.mul(1, 0, 1).subtract(this.owner.firstPos.mul(1, 0, 1)));
			this.setPosition(Util.lerp(posX, gpos.x, 0.05), Util.lerp(posY, firstPos.y, 0.05), Util.lerp(posZ, gpos.z, 0.05));
    	}
//    	this.rotationYaw = 0;
//    	this.prevRotationYaw = 0;
//    	this.renderYawOffset = 0;
//    	this.prevRenderYawOffset = 0;
    	
    	
    	
    }
    
    protected void dealDamage(LivingEntity entityIn) {
        if (this.isAlive()) {
           if (this.canEntityBeSeen(entityIn) && entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength())) {
              this.applyEnchantments(this, entityIn);
           }
        }

     }
    
    protected int getAttackStrength()
    {
        return damage;
    }
    
    
    @Override
	protected void registerData() {
		super.registerData();
	}
	
    
}
